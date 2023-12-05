package cache.system.utils.storage;

import cache.system.exception.NotFoundException;
import cache.system.exception.StorageFullException;
import cache.system.utils.evictionPolicy.LRUEvictionPolicy;
import cache.system.utils.storage.Storage;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage implements Storage {
    private int cacheSize;

    Map<String, String> storage; // Map is interface

    private LRUEvictionPolicy lruEvictionPolicy;

    public HashMapBasedStorage(int size) {
        this.cacheSize = size;
        storage = new HashMap<>();
    }
    @Override
    public void add(String key, String value) {
        if(isStorageFull()) throw new StorageFullException("Storage size if full!");
        storage.put(key, value);
    }

    private boolean isStorageFull() {
        return storage.size() == cacheSize;
    }

    @Override
    public String remove(String key) {
        System.out.println("storage size before eviction- "+ storage.size());
        if(!storage.containsKey(key)) throw  new NotFoundException("Key not found in storage to remove.");
        storage.remove(key);
        lruEvictionPolicy.evictKey();
        System.out.println("storage size before eviction- "+ storage.size());
        return null;
    }

    @Override
    public String get(String key) {
        if(!storage.containsKey(key)) throw new NotFoundException("Key not found in storage");
        return storage.get(key);
    }
}
