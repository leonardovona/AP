
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Context of the job scheduler implementing the frozen spots of the framework.
 *
 * @author Leonardo Vona 545042
 * @param <K> Key type of the pair
 * @param <V> Value type of the pair
 */
public class JobSchedulerContext<K, V> {

    // Strategy implementing the hot spots
    private JobSchedulerStrategy jbs;

    /**
     * Constructor which takes as parameter the concrete strategy.
     *
     * @param jbs Strategy implementation
     */
    public JobSchedulerContext(JobSchedulerStrategy jbs) {
        this.jbs = jbs;
    }

    /**
     * Starting point of the job scheduler framework.
     */
    public void main() {
        jbs.output(collect(compute(jbs.emit())));

    }

    /**
     * Executes the jobs received from emit by invoking execute on them, and
     * returns a single stream of key/value pairs obtained by concatenating the
     * output of the jobs.
     *
     * @param jobStream Stream of jobs to execute received from emit.
     * @return Stream of key/value pairs obtained by concatenating the output of
     * the jobs.
     */
    public Stream<Pair<K, V>> compute(Stream<AJob<K, V>> jobStream) {
        return jobStream.flatMap(AJob::execute);
    }

    /**
     * Takes as input the output of compute and groups all the pairs with the
     * same keys in a single pair, having the same key and the list of all
     * values as value.
     *
     * @param pairStream Stream coming from the compute phase. Each element is a
     * key/value pair
     * @return Stream of pairs obtained grouping all the pairs with the same
     * keys in a single pair, having the same key and the list of all values as
     * value
     */
    public Stream<Pair<K, List<V>>> collect(Stream<Pair<K, V>> pairStream) {
        return pairStream
                // Groups the values of the pairs by key into a Map
                .collect(Collectors.groupingBy(
                        Pair::getKey,
                        // The values sharing the same key are collected into a
                        // single list
                        Collectors.mapping(
                                Pair::getValue,
                                Collectors.toList())))
                // The map is converted to a Stream of map Entry
                .entrySet().stream()
                // The Stream of map Entry is converted to a Stream of Pair
                .map(entry -> new Pair(entry.getKey(), entry.getValue()));
    }
}
