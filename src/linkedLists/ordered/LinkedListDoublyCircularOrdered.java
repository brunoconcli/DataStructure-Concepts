package linkedLists.ordered;
import linkedLists.extended.BaseLinkedListDoublyCircular;

public class LinkedListDoublyCircularOrdered<X extends Comparable<X>> extends BaseLinkedListDoublyCircular<X> {
	public void add(X info) throws Exception {
		if (info == null) throw new Exception ("Information passed must not be null");
		
		if (this.first == null) {
			this.first = this.last = new Node<X>(info, this.first);
			
			this.size++;
			return;
		}
	
		if (this.first.getInfo().compareTo(info) > 0) {
			this.first = new Node<X>(info, this.first);
			this.first.getNext().setPrev(this.first);
			if (this.size == 1)
				this.last = this.first;
			
			this.last.setNext(this.first);
			this.first.setPrev(this.last);
			
			this.size++;
			return;	
		}
		
		if (this.size == 1) {
			this.first.setNext(new Node<X>(this.first, info, this.first));
			this.last = this.first.getNext();
			this.first.setPrev(this.last);

			this.size++;
			return;
		}

		Node<X> current = this.first.getNext(), previous = this.first;
		for (int i = 1; i < this.size; i++) {
			if (current.getInfo().compareTo(info) > 0) {
				previous.setNext(new Node<X>(previous, info, current));
				current.setPrev(previous.getNext());	
				this.size++;
				return;
			}
			previous = current;
			current = current.getNext();
		}

		this.last.setNext(new Node<X>(this.last, info, this.first));
		this.last = this.last.getNext();
		this.first.setPrev(this.last);

		this.size++;
	}
}
 
