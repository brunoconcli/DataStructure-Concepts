package linkedLists.unordered;
import linkedLists.extended.BaseLinkedListDoubly;

public class LinkedListDoublyUnordered<X> extends BaseLinkedListDoubly<X> {
    
    public void addFirst(X info) throws Exception {
        if (info == null) throw new Exception("Information passed must not be null");
        
        this.first = new Node<X>(info, this.first);
        
        if (this.getSize() != 0)
            this.first.getNext().setPrev(this.first);
        
        if (this.last == null)
            this.last = this.first;

        this.size++;
    }

    public void addLast(X info) throws Exception {
        if (info == null) throw new Exception("Information passed must not be null");
        
        if (this.first == null) {
            this.addFirst(info);
            return;
        }
        else {
            this.last.setNext(new Node<X>(this.last, info));
            this.last = this.last.getNext();
        }
    
        this.size++;
    }

    public void addAfter(int index, X info) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (" + this.getSize() + ")");
        
        if (index == this.getSize() - 1) {
            this.addLast(info);
            return;
        }
        Node<X> current = this.first, added = null;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setNext(new Node<X>(current, info, current.getNext()));
        added = current.getNext();
        added.getNext().setPrev(added);

        this.size++;
    }
}
