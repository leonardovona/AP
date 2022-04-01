
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implements the strategy, then the hot spots, of the counting anagrams
 * scheduler.
 * 
 * @author Leonardo Vona 545042
 */
public class CountingAnagramsScheduler implements JobSchedulerStrategy<String, String> {

    /**
     * Asks the user for the absolute path of a directory where documents are
     * stored. It visits the directory and creates a new job for each file
     * ending with .txt in that directory.
     * 
     * @return A Stream of CiaoJob to be executed.
     */
    @Override
    public Stream<AJob<String, String>> emit() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.println("Directory: ");
            // Absolute path of the input directory
            String directory = reader.readLine();
            
            // Produces a stream of files contained into the input directory
            return Files.list(Paths.get(directory))
                    // Filters out the directories and the files not ending
                    // with .txt
                    .filter(f -> !Files.isDirectory(f) && f.toString().endsWith(".txt"))
                    // For each valid file creates a new CiaoJob
                    .map(f -> new CiaoJob(f.toString()));

        } catch (IOException ex) {
            System.err.println("Error while listing files in CountingAnagramsScheduler.emit(): " + ex);
        }
        return null;
    }

    /**
     * Writes the list of ciao keys and the number of words associated with each
     * key, one per line, in file "count_anagrams.txt".
     * 
     * @param pairStream Stream of Pair coming from the collect phase.
     */
    @Override
    public void output(Stream<Pair<String, List<String>>> pairStream) {
        String output = pairStream
                // For each pair a string containing the key and the number of
                // associated words is created
                .map(p -> p.getKey() + " " + p.getValue().size())
                // The strings are joined into a single string, divided one per
                // line
                .collect(Collectors.joining("\n"));
        
        // The resulting string is written to the output file
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("count_anagrams.txt"))) {
            writer.write(output);
        } catch (IOException ex) {
            System.err.println("Error while writing file in CountingAnagramsScheduler.output(): " + ex);
        }
    }

}
