
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Subclass of AJob representing a job that reads a file and returns a stream of
 * the form (ciao(w), w), where w is a word, for all the words of the file that:
 *  - have at least four characters
 *  - contains only alphabetic characters
 * The words are normalized to contain only lowercase letters.
 * 
 * @author Leonardo Vona 545042
 */
public class CiaoJob extends AJob<String, String> {

    private String file; // Path to the input file
    
    /**
     * Constructor taking the input file as parameter.
     * 
     * @param file Path to the input file
     */
    public CiaoJob(String file) {
        this.file = file;
    }
    
    /**
     * Returns the ciao of the string taken as parameter.
     * 
     * @param word String to compute the ciao
     * @return The ciao of the input string
     */
    private String ciao(String word) {
        char tempArray[] = word.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    /**
     * Executes the assigned job, creating a pair (ciao(w), w) for each valid
     * word of the input file.
     * 
     * @return A stream of Pair(ciao(w), w) obtained by parsing the input file
     */
    @Override
    public Stream<Pair<String, String>> execute() {
        try {
            // Reads the file one line at the time
            return Files.lines(Paths.get(file))
                    // Each line is split into words using the pattern "\\W+"
                    .flatMap(Pattern.compile("\\W+")::splitAsStream)
                    // The words of the file composed by less than 4 characters
                    // or containing non-alphabetic characters are filtered out
                    .filter(w -> w.length() > 3 && w.matches("[a-zA-Z]+"))
                    // Each word is normalized to lower case
                    .map(String::toLowerCase)
                    // For each word w a Pair(ciao(w), w) is created
                    .map(w -> new Pair(ciao(w), w));
        } catch (IOException ex) {
            System.err.println("Error while reading file in"
                    + " CiaoJob.execute(): " + ex);
        }
        return null;
    }

}
