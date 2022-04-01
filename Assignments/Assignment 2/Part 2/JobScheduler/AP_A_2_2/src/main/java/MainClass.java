/**
 * Entry point. Instantiates the strategy and the context for counting the 
 * anagrams, and then executes it.
 * 
 * @author Leonardo Vona 545042
 */
public class MainClass {

    public static void main(String[] args) {
        JobSchedulerStrategy<String, String> jbs = new CountingAnagramsScheduler();
        JobSchedulerContext<String, String> jbc = new JobSchedulerContext(jbs);
        jbc.main();


    }
}
