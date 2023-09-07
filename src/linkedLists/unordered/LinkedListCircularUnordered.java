package linkedLists.unordered;
import linkedLists.bases.BaseLinkedListCircular;

public class LinkedListCircularUnordered<X> extends BaseLinkedListCircular<X> {
    
    public void addFirst(X info) throws Exception {
        if (info == null)
            throw new Exception ("Information passed must not be null");

        this.first = new Node<X>(info, this.first);

        if (this.last == null)
            this.last = this.first;
        else
            this.last.setNext(this.first);

        this.size ++;
    }
    
    public void addLast(X info) throws Exception {
        if (info == null) throw new Exception ("Information passed must not be null");
        Node<X> toBeInserted = new Node<X>(info, this.first);
        
        if (this.first == null) {
            this.addFirst(info);
            return;
        }
        this.last.setNext(toBeInserted);
        this.last = toBeInserted;

        this.size ++;
    }

    public void addAfter(int index, X info) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (\" + this.getSize() + \")");

        if (index == this.getSize() - 1) {
            this.addLast(info);
            return;
        }
        
        Node<X> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setNext(new Node<X>(info, current.getNext()));

        this.size++;
    }
}
