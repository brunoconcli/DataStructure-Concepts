package linkedLists.unordered;
import linkedLists.bases.BaseLinkedListDoublyCircular;

public class LinkedListDoublyCircularUnordered<X> extends BaseLinkedListDoublyCircular<X> {
    public void addFirst(X info) throws Exception {
		if (info == null) throw new Exception("The information passed must not be null");
	
		this.first = new Node<X>(this.last, info, this.first);
	
		if (this.size != 0) {
			this.first.getNext().setPrev(this.first);
			this.last.setNext(this.first);
		}
			
		else {
			this.last = this.first;
		}	
	
		this.size++;
	}

	public void addLast(X info) throws Exception {
		if (info == null) 
			throw new Exception("The information passed must not be null");
		
		if (this.size != 0) {
			this.last.setNext(new Node<X>(this.last, info, this.first));
			this.last = this.last.getNext();
			this.first.setPrev(this.last);
		}
		else
			this.last = this.first = new Node<X>(info, this.first);
		
		this.size++;
	}
	
	public void addAfter(int index, X info) throws Exception {
		if (index < 0 || index >= this.size)
			throw new Exception ("Index passed must be between 0 and the list's length (" + this.size + ")");
		if (info == null) 
			throw new Exception ("Information passed must not be null");
		
		if (index == 0) {
			this.addFirst(info);
			return;
		}
		
		if (index == this.size-1) {
			this.addLast(info);
			return;
		} 
		
		Node<X> current = this.first, next = null;
		for (int i = 0; i < index; i++)
			current = current.getNext();
		
		next = current.getNext();

		Node<X> toInsert = new Node<X>(current, info, next);
		
		current.setNext(toInsert);
		next.setPrev(toInsert);

		this.size++;
	}
}