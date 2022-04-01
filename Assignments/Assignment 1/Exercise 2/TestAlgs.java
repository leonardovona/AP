
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Class that testes encryption algorithms given as .class files.
 *
 * @author Leonardo Vona 545042
 */
public class TestAlgs {

    private static String absolute_path; // Absolute path to crypto folder
    private static KeyRegistry registry; // Registry of encryption algorithms
    private static URLClassLoader ucl; // Class loader for encryption algorithms' .class files
    private static List<String> secrets; // List of secrets from crypto/secret.list

    public static void main(String[] args) {
        secrets = new ArrayList<>();

        registry = new KeyRegistry();

        if (args.length == 0) { // If a path is not specified, the current folder is taken as default
            System.out.println("No path specified, using current location");
            absolute_path = new File("./").getAbsolutePath() + "/";
        } else {
            absolute_path = new File(args[0]).getAbsolutePath() + "/";
        }

        try {
            URL url = new URL("file://" + absolute_path);
            ucl = new URLClassLoader(new URL[]{url});   // Initializes the class loader
        } catch (MalformedURLException ex) {
            System.err.println("ERROR: Unvalid path");
            return;
        }

        if (!read_from_file("keys")) { // Scans crypto/keys.list and populate the registry
            return;
        }

        if (!read_from_file("secret")) { // Scans crypto/secret.list and populate the secrets' list
            return;
        }

        for (File f : (new File("crypto/algos")).listFiles()) { // Loop through the .class files in the folder crypto/algos
            if (f.getName().endsWith(".class")) {
                String algorithm_name = f.getName().substring(0, f.getName().length() - 6);
                System.out.println("\n" + algorithm_name);
                try {
                    Class alg = ucl.loadClass("crypto.algos." + algorithm_name);

                    if (!exists_constructor(alg)) { // Check if exists a constructor with a single String parameter
                        System.out.println("Constructor not found");
                    }

                    Method encryption_method = retrieve_method(alg, "enc");  // Retrieves the encryption method
                    Method decryption_method = retrieve_method(alg, "dec");  // Retrieves the decryption method

                    // Verifies for each secret if the algorithm works properly
                    verify_algorithm(encryption_method, decryption_method, alg);
                } catch (NoSuchMethodException ex) {
                    System.out.println(ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    System.err.println("ERROR: Class " + ex.getMessage() + " not found during method verification");
                }

            }
        }
    }

    /**
     * Checks if the class alg has a public constructor with a single String
     * parameter.
     *
     * @param alg Class to check.
     * @return true if the class alg has a public constructor with a single.
     * String parameter, false otherwise.
     */
    private static boolean exists_constructor(Class alg) {
        try {
            alg.getConstructor(String.class);
        } catch (NoSuchMethodException ex) {
            return false;
        }
        return true;
    }

    /**
     * Given the Class associated to an algorithm checks the existence and
     * retrieves the encryption / decryption method with a single String
     * parameter.
     *
     * @param alg Class of the algorithm to retrieve the encryption / decryption
     * method.
     * @param method Can be "enc" or "dec", identifies the method to retrieve.
     * @return The encryption / decryption method, depending on the parameter
     * method.
     * @throws NoSuchMethodException if the encryption / decryption method has
     * not been found.
     */
    private static Method retrieve_method(Class alg, String method) throws NoSuchMethodException {
        for (Method m : alg.getDeclaredMethods()) {
            if (m.getName().startsWith(method) // The method has the correct name
                    && m.getParameterTypes().length == 1 // The method has the correct number of parameters
                    && m.getParameterTypes()[0] == String.class) {  // The method has the correct parameter type
                return m;
            }
        }
        throw new NoSuchMethodException("Enc/Dec methods not found");
    }

    /**
     * Scans the crypto/keys.list file populating the registry or scans the
     * crypto/secret.list populating the secrets' list.
     *
     * @param file Either "keys" or "secret".
     * @return false if an error occurred, true otherwise.
     */
    private static boolean read_from_file(String file) {
        try ( BufferedReader br = new BufferedReader(new FileReader(absolute_path + "/crypto/" + file + ".list"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (file.equals("keys")) {  // Reading from keys.list
                    StringTokenizer st = new StringTokenizer(line, " ");    // Pattern: algorithm-name key
                    registry.add(ucl.loadClass(st.nextToken()), st.nextToken());
                } else {    // Reading from secret.list
                    secrets.add(line);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.err.println("ERROR: File " + file + ".list not found");
            return false;
        } catch (IOException ex) {
            System.err.println("ERROR: while reading from " + file + "keys.list");
            return false;
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR: Class " + ex.getMessage() + " not found during registry loading");
            return false;
        }
        return true;
    }

    /**
     * For each secret in secrets' list creates an instance of encryption
     * algorithm alg and verifies the correct behavior of encryption_method and
     * decryption_method.
     *
     * @param encryption_method Encryption method of the algorithm.
     * @param decryption_method Decryption method of the algorithm.
     * @param alg Class relative to the algorithm.
     * @throws NoSuchMethodException if encryption_method or decryption_method
     * are not found.
     */
    private static void verify_algorithm(Method encryption_method, Method decryption_method, Class alg) throws NoSuchMethodException {
        try {
            for (String wrd : secrets) {
                Object alg_instance = alg.getDeclaredConstructor(String.class).newInstance(registry.get(alg)); // Creates an instance of alg

                String encwrd = (String) encryption_method.invoke(alg_instance, wrd);   // Invokes the encryption algorithm
                String decwrd = (String) decryption_method.invoke(alg_instance, encwrd); // Invokes the decryption algorithm 

                if (!decwrd.startsWith(wrd)) {  // The output of encryption / decryption is not correct
                    System.out.println("KO: " + wrd + " -> " + encwrd + " -> " + decwrd);
                }
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.err.println("ERROR: Error during encryption / decryption");
        }
    }
}
