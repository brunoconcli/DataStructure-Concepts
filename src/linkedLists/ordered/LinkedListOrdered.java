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
		this.size++;
            	return;
        }
	Node<X> current = this.first, previous = null;
	// coninue for and remember -> if current is not greater than the node compared to, add it behind it 
    }
}
