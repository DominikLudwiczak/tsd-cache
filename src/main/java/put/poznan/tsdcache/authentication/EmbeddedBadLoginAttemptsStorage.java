package put.poznan.tsdcache.authentication;

import java.util.HashMap;
import java.util.Map;

public class EmbeddedBadLoginAttemptsStorage implements BadLoginAttemptsStorage {

    // TODO 1.2 - Create and initialize a field of HashMap type to store data
    private final Map<String, Integer> storage = new HashMap<>();

    // TODO 1.2 - Finish this method
    @Override
    public Integer get(String key) {
        if (!storage.containsKey(key)) {
            return 0;
        }
        return storage.get(key);
    }

    // TODO 1.2 - Finish this method
    @Override
    public void put(String key, Integer value) {
        storage.put(key, value);
    }

    // TODO 1.2 - Finish this method
    @Override
    public void increment(String key) {
        if (storage.containsKey(key)) {
            storage.put(key, storage.get(key) + 1);
        } else {
            storage.put(key, 1);
        }
    }

    // TODO 1.2 - Finish this method
    @Override
    public void remove(String key) {
        storage.remove(key);
    }
}
