package cache.system.utils.evictionPolicy;

public interface EvictionPolicy {
    void keyAccessed(String key);
    /*
     * Evict key from eviction policy and return it.
     */
    String evictKey();
}
