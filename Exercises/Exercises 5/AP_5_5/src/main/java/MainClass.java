
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {

    public static void main(String[] args) {
        Integer[] xs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(filterOdd2(xs));
    }

    public static <T> Stream<ImmutablePair<T, Integer>> zipWithIndex(T[] xs) {
        ImmutablePair<T, Integer>[] pairs = new ImmutablePair[xs.length];
        for (int i = 0; i < xs.length; i++) {
            pairs[i] = new ImmutablePair<>(xs[i], i);
        }
        return Arrays.stream(pairs);
    }

    public static <T> List<T> filterOdd1(T[] xs) {
        List<T> xs_new = new ArrayList<>();
        for (int i = 0; i < xs.length; i += 2) {
            xs_new.add(xs[i]);
        }
        return xs_new;
    }

    public static <T> List<T> filterOdd2(T[] xs) {
        return (List<T>) zipWithIndex(xs)
                .filter(x -> x.getSecond() % 2 == 0)
                .map(x -> x.getFirst())
                .collect(Collectors.toList());
        
    }

}
