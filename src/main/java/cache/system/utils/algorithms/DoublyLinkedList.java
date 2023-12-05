package cache.system.utils.algorithms;

import java.util.DuplicateFormatFlagsException;
import java.util.function.DoubleBinaryOperator;

public class DoublyLinkedList {
    DoublyLinkedListNode dummyHead;
    DoublyLinkedListNode dummyTail;

    public DoublyLinkedList() {
        dummyHead = new DoublyLinkedListNode();
        dummyTail = new DoublyLinkedListNode();

        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public void detachNode(DoublyLinkedListNode node) {
        if(node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

        }
    }

    public void addNodeAtLast(DoublyLinkedListNode node) {
        DoublyLinkedListNode tailPre = dummyTail.prev;
        tailPre.next = node;
        node.prev = tailPre;
        node.next = dummyTail;
        dummyTail.prev = node;
    }

    public DoublyLinkedListNode addElementAtLast(String key) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode();
        newNode.val = key;
        addNodeAtLast(newNode);
        return newNode;
    }

    public DoublyLinkedListNode getFirstNode() {

        if(!isNodePresent()) return null;
        return dummyHead.next;
    }

    public DoublyLinkedListNode getLastNode() {
        if(!isNodePresent()) return null;
        return dummyTail.prev;
    }

    private boolean isNodePresent() {
        return dummyHead.next != dummyTail;
    }

}
