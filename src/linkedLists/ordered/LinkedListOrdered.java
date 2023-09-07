package linkedLists.ordered;
import linkedLists.bases.BaseLinkedList;
public class LinkedListOrdered<X extends Comparable<X>> extends BaseLinkedList<X> {
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
		this.first = this.last = new Node<X>(info, null);
       		
		this.size++;
            	return;
        }

	if (this.first.getInfo().compareTo(info) > 0) {
		this.first = new Node<X>(info, this.first);
		if (this.size == 1) this.last = this.first.getNext();
		
		this.size++;
		return;
	}

	if (this.size == 1) {
		this.last = new Node<X>(info, null);
		this.first.setNext(this.last);
	
		this.size++;
		return;
	}

	Node<X> current = this.first.getNext(), previous = this.first;
	for (int i = 1; i < this.size; i++) { 
		if (current.getInfo().compareTo(info) > 0) {
			previous.setNext(new Node<X>(info, current));
			this.size++;
			return;
		}
		previous = current;
		current = current.getNext();
	}
	
	this.last.setNext(new Node<X>(info, null));
	this.last = this.last.getNext();

	this.size++;
    }
}
