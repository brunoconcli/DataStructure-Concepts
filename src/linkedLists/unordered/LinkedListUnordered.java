package linkedLists.unordered;
import linkedLists.bases.BaseLinkedList;
import linkedLists.ordered.LinkedListOrdered;

public class LinkedListUnordered<X extends Comparable<X>> extends BaseLinkedList<X> {
    public LinkedListOrdered<X> getOrdered() {
        LinkedListOrdered<X> orderedList = new LinkedListOrdered<>();
        
        try {
            Node current = this.first;
            while(current != null) {
                orderedList.add(current.getInfo());
                current = current.getNext();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderedList;
    }
    
    public void addFirst(X info) throws Exception {
        if (info == null)
            throw new Exception("Information passed must not be null");

        this.first = new Node (info, this.first);

        if (this.last==null)
            this.last = this.first;
        
        this.size++;
    }

    public void addLast(X info) {
        if (this.last == null) {
            this.last = new Node(info);
            this.first = this.last;
        }
        else {
            this.last.setNext(new Node(info));
            this.last = this.last.getNext();
        }

        this.size++;
    }

    public void addAfter(int index, X info) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (" + this.getSize() + ")");
        Node current = this.first;
        for (int i = 0; i < index; i ++) {
            current = current.getNext();
        }
        current.setNext(new Node(info, current.getNext()));
        this.size++;
    }
}
