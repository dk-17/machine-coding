package cache.system.utils.algorithms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoublyLinkedListNode {
    String val ;
    DoublyLinkedListNode prev;
    DoublyLinkedListNode next;

    public DoublyLinkedListNode() {
        this.prev = null;
        this.next = null;
    }
}
