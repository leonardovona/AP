
import java.util.List;
import java.util.stream.Stream;

/**
 * Interface representing the strategy to be implemented for the job scheduler
 * framework. Includes the hot spots of the framework.
 *
 * @author Leonardo Vona 545042
 * @param <K> Key type of the pair
 * @param <V> Value type of the pair
 */
public interface JobSchedulerStrategy<K, V> {

    /**
     * Generates a Stream of AJob.
     *
     * @return Stream of AJob
     */
    public Stream<AJob<K, V>> emit();
    
    /**
     * Prints the result of collect in a convenient way.
     * 
     * @param pairStream Stream of Pair coming from collect phase.
     */
    public void output(Stream<Pair<K, List<V>>> pairStream);

}
