package linkedLists.circular;

import linkedLists.ILinkedList;
import linkedLists.Node;

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
	
	public void addAfter(int index, X info) throws Exception {
		if (index < 0 || index >= this.getSize())
			throw new Exception ("Index passed must be between 0 and the list's length (" + this.getSize() + ")");
		if (info == null) 
			throw new Exception ("Information passed must not be null");
		
		if (index == 0) 
			this.addFirst(info);
		
		if (index == this.getSize()-1) 
			this.addLast(info);
		
		Node<X> toInsert = new Node<X>(info), current = this.first, next = null;
		for (int i = 0; i < index; i++)
			current = current.getNext();
		
		next = current.getNext();

		toInsert.setPrev(current);
		toInsert.setNext(next);
		
		current.setNext(toInsert);
		next.setPrev(toInsert);

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

	public X getElementAt(int index) throws Exception {
		if (index < 0 || index >= this.getSize())
			throw new Exception("The index passed must be between 0 and the list's length (" + this.getSize() + ")");

		Node<X> current = this.first;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getInfo();
	}

	public void removeFirst() throws Exception {
		this.removeInto(0);
	}

	public void removeLast() throws Exception {
		this.removeInto(this.getSize()-1);
	}
	@Override
	public void removeInto(int index) throws Exception {
		if (index < 0 || index >= this.getSize())
			throw new Exception("Index passed must be between 0 and the list's length (" + this.getSize() + ")");
		
		if (index == 0) {
			this.first = this.first.getNext();
			this.first.setPrev(this.last);
			this.last.setNext(this.first);
			
			this.size--;
			return;
		}
		if (index == this.getSize() - 1) {
			this.last = this.last.getPrev();
			this.last.setNext(this.first);
			this.first.setPrev(this.last);

			this.size--;
			return;
		}
		
		Node<X> current = this.first, previous = null, next = null;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		previous = current.getPrev();
		next = current.getNext();
		
		previous.setNext(next);
		next.setPrev(previous);
		
		current = null;
		
		this.size--;
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
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (this.getClass() != obj.getClass()) return false;

		try {

			LinkedListDoublyCircular<X> data = (LinkedListDoublyCircular<X>) obj;
			if (this.first.getInfo() != data.first.getInfo()) return false;
			if (this.last.getInfo() != data.last.getInfo()) return false;
			if (this.size != data.size) return false;

			Node<X> currentThis = this.first, currentData = data.first;
			for (int i = 0; i < this.getSize(); i++) {
				if (currentThis.getInfo() != currentData.getInfo()) return false;
				currentThis = currentThis.getNext();
				currentData = currentData.getNext();
			}
		}
		catch (Exception e) {
			e.getMessage();
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 2;
		
		if (this.first != null) hash = 3*hash + this.first.hashCode();
		if (this.last != null) hash = 3*hash + this.last.hashCode();
		hash = 3*hash + Integer.valueOf(this.size).hashCode();
		
		if (hash < 0) hash = -hash;
		return hash;
    	}
}
