package cache.system.service;

import cache.system.exception.NotFoundException;
import cache.system.exception.StorageFullException;
import cache.system.utils.evictionPolicy.LRUEvictionPolicy;
import cache.system.utils.storage.Storage;

public class Cache {
    private int cacheSize;
    private Storage storage;

    private LRUEvictionPolicy evictionPolicy;

    public Cache(int size, Storage storage) {
        this.cacheSize = size;
        this.storage = storage;
        evictionPolicy = new LRUEvictionPolicy();
    }

    public void put(String key, String value) {
        try {
            storage.add(key, value);
            evictionPolicy.keyAccessed(key);

        } catch(StorageFullException e) {
            System.out.println("Cache is full, need to free the space before inserting new value");
            evict();
            System.out.println("Storage space is available to insert the new key value pair");
            storage.add(key, value);
        }

    }

    public String get(String key) {
        try {
            String value =  storage.get(key);
            evictionPolicy.keyAccessed(key);

            return value;
        } catch (NotFoundException e) {
            System.out.println("Key is not present in storage");

            return null;
        }
    }

    public String evict() {
        return evictionPolicy.evictKey();
    }
}
