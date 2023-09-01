package linkedLists.ordered;
import linkedLists.extended.BaseLinkedList;
public class LinkedListOrdered<X extends Comparable<X>> extends BaseLinkedList<X> {

    private Node<X> first, last;
    private int size = 0;

    public LinkedListOrdered() {}

    protected LinkedListOrdered(LinkedListOrdered<X> model) throws Exception {
        if (model == null) throw new Exception ("Model object passed must not be null");
        this.first = model.first;
        this.last = model.last;
        this.size = model.size;

    }

    public void add(X info) throws Exception {
        if (info == null) throw new Exception("Info passed must not be null");

        if (this.first == null) {
            this.first = new Node<X>(info, null);
            this.last = this.first;
            return;
        }

        // compareTo() < 0 --> this < obj
        // compareTo() > 0 --> this > obj 
        
        // for (Node<X> current = this.first; current != null; current = current.getNext()) {
        //     if (current.getInfo().compareTo(info) )
        // }
    }

}
