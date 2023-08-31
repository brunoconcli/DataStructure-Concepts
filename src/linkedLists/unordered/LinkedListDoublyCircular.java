package linkedLists.circular;

import linkedLists.ILinkedList;
import linkedLists.Node;

public class LinkedListDoublyCircular<X extends Comparable<X>> implements ILinkedList, Cloneable {
    
	private Node<X> first, last;
	private int size;
	public LinkedListDoublyCircular() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}

	public LinkedListDoublyCircular(LinkedListDoublyCircular<X> model) throws Exception {
		if (model == null) 
			throw new Exception ("Model object passed must not be null");
		this.first = model.first;
		this.last = model.last;
		this.size = model.size;
	}
	
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

	@Override	
	public boolean isEmpty() {
		return this.first == null;
	}

	@Override 
	public int getSize() {
		return this.size;
	}

	public X getElementAt(int index) throws Exception {
		if (index < 0 || index >= this.size)
			throw new Exception("The index passed must be between 0 and the list's length (" + this.getSize() + ")");

		Node<X> current = this.first;
		for (int i = 0; i < index; i++) 
			current = current.getNext();
		
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
			Node<X> current = this.first;
			for(; current != this.last; current = current.getNext()) {
				message.append(current.getInfo());
				message.append(", ");
			}
			if (this.size != 0)
				message.append(current.getInfo());
		}
		catch (Exception ignored) {}

		return message.toString();
	}

	@Override
	public Object clone() {
		LinkedListDoublyCircular<X> ret = null;
		try {
	    	ret = new LinkedListDoublyCircular<X>(this);
		}
		catch (Exception ignored) {}

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
			if (this.size != data.size) return false;
			if (!this.first.getInfo().equals(data.first.getInfo())) return false;
			if (!this.last.getInfo().equals(data.last.getInfo())) return false;

			Node<X> currentThis = this.first.getNext(), currentData = data.first.getNext();
			for (int i = 1; i < this.size -1; i++) {
				if (!currentThis.getInfo().equals(currentData.getInfo())) return false;
				currentThis = currentThis.getNext();
				currentData = currentData.getNext();
			}
		}
		catch(Exception ignored) {}

		return true;
	}

	@Override
	public int hashCode() {
		int hash = 2;
		if (this.first != null) {
			Node<X> current = this.first;
			do {
				hash = 3*hash + current.getInfo().hashCode();
				current = current.getNext();
			}
			while(current != this.first);
		}

		hash = 3*hash + Integer.valueOf(this.size).hashCode();
		
		if (hash < 0) hash = -hash;
		return hash;
    }
}
