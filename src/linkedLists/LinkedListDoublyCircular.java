package linkedLists;

public class LinkedListDoublyCircular<X extends Comparable<X>> implements ILinkedList, Cloneable {
    
	Node<X> first, last;
	int size = 0;
	public LinkedListDoublyCircular() {}

	public LinkedListDoublyCircular(LinkedListDoublyCircular<X> model) throws Exception {
		if (model == null) 
			throw new Exception ("Model object passed must not be null");
		this.first = model.first;
		this.last = model.last;
		this.size = model.size;
	}
	
	public void addFirst(X info) throws Exception {
		if (info == null) throw new Exception("The information passed must not be null");
	
		this.first = new Node<X>(info, this.first);
	
		if (this.getSize() != 0) {
			this.first.getNext().setPrev(this.first);
			this.last.setNext(this.first);
			this.first.setPrev(this.last);
		}
			
		else {
			this.last = this.first;
		}	
	
		this.size++;
	}

	public void addLast(X info) throws Exception {
		if (info == null) 
			throw new Exception("The information passed must not be null");
		
		if (this.getSize() != 0) {
			this.last.setNext(new Node<X>(this.last, info, this.first));
			this.last = this.last.getNext();
			this.first.setPrev(this.last);
		}
		else if (this.last == this.first) {
			this.first = new Node<X>(info, this.first);
			this.last = this.first;
		}
		
		this.size++;
	}

	@Override	
	public boolean isEmpty() {
		return this.first == null;
	}

	@Override 
	public int getSize() {
		return this.size;
	}

	@Override
	public void removeInto(int index) {
	}

	@Override
	public void removeAllElements() {
		this.first = this.last = null;
		this.size = 0;
	}

	@Override
	public String toString() {
    		StringBuilder message = new StringBuilder();
		try {
			Node<X> current;
			for(current = this.first; current != this.last; current = current.getNext()) {
				message.append(current.getInfo());
				message.append(", ");
			}
			message.append(current.getInfo());
		}
		catch (Exception e) {
			e.getMessage();
		}

		return message.toString();
	}

	@Override
	public Object clone() {
		LinkedListDoublyCircular<X> ret = null;
		try {
	    		ret = new LinkedListDoublyCircular<X>(this);
		}
		catch (Exception e) {
	    		e.getMessage();
		}
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) {
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 2;

		if (hash < 0) hash *= (-1);
		return hash;
    	}
}
