package linkedLists.unordered;
import linkedLists.extended.BaseLinkedList;

public class LinkedListUnordered<X> extends BaseLinkedList<X> {
    public void addFirst(X info) throws Exception {
        if (info == null)
            throw new Exception("Information passed must not be null");

        this.first = new Node <X> (info, this.first);

        if (this.last==null)
            this.last = this.first;
        
        this.size++;
    }

    public void addLast(X info) {
        if (this.last == null) {
            this.last = new Node<X>(info);
            this.first = this.last;
        }
        else {
            this.last.setNext(new Node<X>(info));
            this.last = this.last.getNext();
        }

        this.size++;
    }

    public void addAfter(int index, X info) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (" + this.getSize() + ")");
        Node<X> current = this.first;
        for (int i = 0; i < index; i ++) {
            current = current.getNext();
        }
        current.setNext(new Node<X>(info, current.getNext()));
        this.size++;
    }
}
