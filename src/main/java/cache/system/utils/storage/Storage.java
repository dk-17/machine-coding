package cache.system.utils.storage;

public interface Storage {
    void add(String key, String value);
    String remove(String key);
    String get(String key);
}
