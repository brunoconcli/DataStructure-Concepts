package linkedLists.extended;

import linkedLists.ILinkedList;

public class BaseLinkedListDoublyCircular<X> implements ILinkedList<X>, Cloneable {
	protected class Node <N>{
		private N info;
		private Node <N> next, prev;
		public Node (N info) {
			this.info = info;
			this.next = null;
			this.prev = null;
		}
		public Node (Node<N> prev, N info) {
			this.prev = prev;
			this.info = info;
			this.next = null;
		}
		public Node (N info, Node <N> next) {
			this.prev = null;
			this.info = info;
			this.next = next;
		}
		public Node (Node<N> prev, N info, Node<N> next) {
			this.prev = prev;
			this.info = info;
			this.next = next;
		}
		public N getInfo() throws Exception {
			if (this.info == null)
				throw new Exception("Node is empty");
			return this.info;
		}
		public Node<N> getNext() {
			return this.next;
		}
		public Node<N> getPrev() {
			return this.prev;
		}
		public void setInfo(N info) {
			this.info = info;
		}
		public void setNext(Node <N> next) {
			this.next = next;
		}
		public void setPrev(Node<N> prev) {
			this.prev = prev;
		}
		@Override
		public String toString() {
			String message = "";
			try {
				message += getInfo() + " -> " + null;
				if (getNext() != null)
					message = getInfo() + " -> " + getNext().getInfo();
				if (getPrev() != null)
					message = getPrev().getInfo() + " -> " + message;
			}
			catch (Exception e) { System.out.println(e.getMessage()); }


			return message;
		}
		@Override
		@SuppressWarnings("unchecked")
		public boolean equals(Object obj) {
			if (obj == null) return false;
			if (this == obj) return true;

			if (this.getClass() != obj.getClass()) return false;

			Node<N> data = (Node<N>)obj;
			if (this.prev != data.prev) return false;
			if (this.info != data.info) return false;
			if (this.next != data.next) return false;

			return true;
		}
	}



	protected Node<X> first, last;
	protected int size;
	public BaseLinkedListDoublyCircular() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}

	public BaseLinkedListDoublyCircular(BaseLinkedListDoublyCircular<X> model) throws Exception {
		if (model == null) 
			throw new Exception ("Model object passed must not be null");
		this.first = model.first;
		this.last = model.last;
		this.size = model.size;
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
		BaseLinkedListDoublyCircular<X> ret = null;
		try {
	    	ret = new BaseLinkedListDoublyCircular<X>(this);
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
			BaseLinkedListDoublyCircular<X> data = (BaseLinkedListDoublyCircular<X>) obj;
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
		try {
			if (this.first != null) {
				Node<X> current = this.first;
				do {
					hash = 3*hash + current.getInfo().hashCode();
					current = current.getNext();
				}
				while(current != this.first);
			}
		}
		catch (Exception ignored) {}

		hash = 3*hash + Integer.valueOf(this.size).hashCode();
		
		if (hash < 0) hash = -hash;
		return hash;
    }
}
