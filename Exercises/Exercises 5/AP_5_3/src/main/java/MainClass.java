
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {

    public static void main(String[] args) {
        Object[] xs = {"ciao", 5, 3.5};
        Object[] n_xs = repl2(xs, 4);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(n_xs[4 * i + j]);
            }
        }
    }

    public static Object[] repl1(Object[] xs, int n) {
        int xs_size = xs.length;
        Object[] n_xs = new Object[xs_size * n];
        for (int i = 0; i < xs_size; i++) {
            for (int j = 0; j < n; j++) {
                n_xs[n * i + j] = xs[i];
            }
        }
        return n_xs;
    }

    public static Object[] repl2(Object[] xs, int n) {
        return Arrays.stream(xs)
                .flatMap(x -> Stream.generate(() -> x)
                        .limit(n))
                .toArray(Object[]::new);
    }
}
