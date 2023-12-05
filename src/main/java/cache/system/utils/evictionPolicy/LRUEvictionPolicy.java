package cache.system.utils.evictionPolicy;

import cache.system.utils.algorithms.DoublyLinkedList;
import cache.system.utils.algorithms.DoublyLinkedListNode;
import cache.system.utils.evictionPolicy.EvictionPolicy;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy implements EvictionPolicy {

    private DoublyLinkedList dll;
    private Map<String, DoublyLinkedListNode> mapper;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList();
        this.mapper = new HashMap<>();
    }
    @Override
    public void keyAccessed(String key) {
        if(mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            DoublyLinkedListNode newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public String evictKey() {
        DoublyLinkedListNode firstNode = dll.getFirstNode();
        if (firstNode == null) {
            return null;
        }

        dll.detachNode(firstNode);
        mapper.remove(firstNode.getVal());
        return firstNode.getVal();
    }
}
