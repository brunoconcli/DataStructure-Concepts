package linkedLists.ordered;
import linkedLists.extended.BaseLinkedListDoubly;

public class LinkedListDoublyOrdered<X extends Comparable<X>> extends BaseLinkedListDoubly<X> {
	// Search different types of exception and when to use 'em	
	public void add(X info) throws Exception {
		if (info == null) throw new Exception ("Information passed must not be null");

		if (this.first == null) {
			this.first = this.last = new Node<X>(info, null);
			this.size ++;
			return;
		}
		if (this.first.getInfo().compareTo(info) > 0) {
			this.first = new Node<X>(info, this.first);
			this.first.getNext().setPrev(this.first);

			if (this.size == 1)
				this.last = this.first.getNext();
			this.size++;
			return;
		}
		if (this.size == 1) {
			this.last = new Node<X>(this.first, info, null);
			this.first.setNext(this.last);
			
			this.size++;
			return;
		}

		Node<X> current = this.first.getNext(), previous = this.first;
		for (int i = 1; i < this.size; i++) {
			if (current.getInfo().compareTo(info) > 0) {
				previous.setNext(new Node<X>(previous, info, current));
				this.size++;
				return;
			}
			previous = current;
			current = current.getNext();
		}
		this.last.setNext(new Node<X>(this.last, info, null));
		this.last = this.last.getNext();
		this.size++;
	}    		
}
