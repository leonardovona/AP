
import java.util.HashMap;
import java.util.Map;

/**
 * Implements a registry for the encryption algorithms and the relative keys.
 *
 * @author Leonardo Vona 545042
 */
public class KeyRegistry {

    private final Map<Class, String> registry;

    public KeyRegistry() {
        registry = new HashMap<>();
    }

    /**
     * Adds an algorithm to the registry.
     *
     * @param c Class of the encryption algorithm to add.
     * @param key Key relative to the encryption algorithm.
     */
    public void add(Class c, String key) {
        registry.put(c, key);
    }

    /**
     * Retrieves the key associated to the encryption algorithm c.
     *
     * @param c Class of the encryption algorithm to return the associated key.
     * @return The key associated to the encryption algorithm passed as
     * argument.
     */
    public String get(Class c) {
        return registry.get(c);
    }
}
