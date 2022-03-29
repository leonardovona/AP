
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class MainClass {

    private static class Subscriber {

        private Integer id;
        private String firstname;
        private String surname;
        private Optional title;
        private Optional address;
        private Optional town;
        private Optional country;
        private Optional postcode;
        private Boolean subscriptionPaid;
        private Optional gender;
        private LocalDate dateOfBirth;

        public Subscriber(String line) {
            String token;
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            id = Integer.parseInt(tokenizer.nextToken().trim());
            firstname = tokenizer.nextToken().trim();
            surname = tokenizer.nextToken().trim();
            token = tokenizer.nextToken().trim();
            title = token.equals("-") ? Optional.empty() : Optional.of(token);
            token = tokenizer.nextToken().trim();
            address = token.equals("-") ? Optional.empty() : Optional.of(token);
            token = tokenizer.nextToken().trim();
            town = token.equals("-") ? Optional.empty() : Optional.of(token);
            token = tokenizer.nextToken().trim();
            country = token.equals("-") ? Optional.empty() : Optional.of(token);
            token = tokenizer.nextToken().trim();
            postcode = token.equals("-") ? Optional.empty() : Optional.of(token);
            subscriptionPaid = Boolean.parseBoolean(tokenizer.nextToken().trim());
            token = tokenizer.nextToken().trim();
            gender = token.equals("-") ? Optional.empty() : Optional.of(token);
            dateOfBirth = LocalDate.parse(tokenizer.nextToken().trim());
        }

        @Override
        public String toString() {
            return "Subscriber{"
                    + "id=" + id
                    + ", firstname=" + firstname
                    + ", surname=" + surname
                    + ", title=" + (title.isPresent() ? title.get() : "-")
                    + ", address=" + (address.isPresent() ? address.get() : "-")
                    + ", town=" + (town.isPresent() ? town.get() : "-")
                    + ", country=" + (country.isPresent() ? country.get() : "-")
                    + ", postcode=" + (postcode.isPresent() ? postcode.get() : "-")
                    + ", subscriptionPaid="
                    + subscriptionPaid
                    + ", gender=" + (gender.isPresent() ? gender.get() : "-")
                    + ", dateOfBirth=" + dateOfBirth + '}';
        }
    }

    public static void main(String[] args) throws IOException {
        paymentFromGB(loadDatabase("people.csv"));
    }

    private static List<Subscriber> loadDatabase(String path) throws IOException {
        return Files.lines(Paths.get(path))
                .skip(1)
                .map(x -> new Subscriber(x))
                .collect(Collectors.toList());
    }

    private static void paymentFromGB(List<Subscriber> subscribers) {
        subscribers.stream()
                .filter(subscriber -> {
                    Optional country = subscriber.country;
                    if (country.isEmpty()) {
                        return false;
                    }
                    return country.get().equals("gb") && subscriber.subscriptionPaid;
                })
                .forEach(System.out::println);
    }
}
