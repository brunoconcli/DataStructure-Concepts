package linkedLists.ordered;
import linkedLists.bases.BaseLinkedListCircular;

public class LinkedListCircularOrdered<X extends Comparable<X>> extends BaseLinkedListCircular<X> {
	public void add(X info) throws Exception {
		if (info == null) throw new Exception ("Information passed must not be null");

		if (this.first == null) {
			this.first = this.last = new Node<X>(info, this.first);
			
			this.size++;
			return;
		}
		
		if (this.first.getInfo().compareTo(info) > 0) {
			this.first = new Node<X>(info, this.first);
			if (this.size == 1)
				this.last = this.first.getNext();
				
			this.last.setNext(this.first);
			this.size++;
			return;
		}

		if (this.size == 1) {
			this.last = new Node<X>(info, this.first);
			this.first.setNext(this.last);

			this.size++;
			return;
		}

		Node<X> current = this.first.getNext(), previous = this.first;
		for (int i = 0; i < this.size; i++) {
			if (current.getInfo().compareTo(info) > 0) {
				previous.setNext(new Node<X>(info, current));
				this.size++;
				return;
			}
			previous = current;
			current = current.getNext();	
		}
		this.last.setNext(new Node<X>(info, this.first));
		this.last = this.last.getNext();
		this.size++;
	}	
}
