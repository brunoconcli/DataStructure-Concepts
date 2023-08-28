package linkedLists;
public class LinkedListUnordered<X extends Comparable<X>> implements ILinkedList, Cloneable {
    private Node<X> first, last;
    private int size = 0;

    public LinkedListUnordered() {}
    public LinkedListUnordered(LinkedListUnordered<X> model) throws Exception {
        if (model == null)
            throw new Exception("Model list in copy constructor cannot be null");
        this.first = model.first;
        this.last = model.last;
        this.size = model.size;
    }

    public void addFirst(X info) throws Exception {
        if (info == null)
            throw new Exception("Information passed must not be null");

        this.first = new Node <X> (info, this.first);

        if (this.last==null)
            this.last = this.first;
        
        this.size++;
    }
    public void addLast(X info) {
        if (this.last == null) {
            this.last = new Node<X>(info);
            this.first = this.last;
        }
        else {
            this.last.setNext(new Node<X>(info));
            this.last = this.last.getNext();
        }

        this.size++;
    }
    public void addAfter(int index, X info) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (" + this.getSize() + ")");
        Node<X> current = this.first;
        for (int i = 0; i < index; i ++) {
            current = current.getNext();
        }
        current.setNext(new Node<X>(info, current.getNext()));
        this.size++;
    }
    public int getSize() {
        return this.size;
    }
    public X getElementAt(int index) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (" + this.getSize() + ")");
        Node<X> current = this.first;
        for (int i = 0; i < index; i ++) 
            current = current.getNext();
        
        return current.getInfo();
    }
    @Override
    public boolean isEmpty() {
        return this.first == null;
    }
    public void removeFirst() throws Exception {
        this.removeInto(0);
    }
    public void removeLast() throws Exception {
        this.removeInto(this.getSize() - 1);
    }
    public void removeFirstElementWithInfo(X info) throws Exception {
        Node<X> current = this.first, previous = null;
        while (current.getInfo() != info) {
            previous = current;
            current = current.getNext();
        }
        this.last = previous;
        if (previous != null)
            previous.setNext(current.getNext());
        current = null;
        this.size--;
    }
    public void removeAllElementsWithInfo(X info) throws Exception {
        Node<X> current = this.first, previous = null;
        for (int i = 0; i < this.getSize(); i++) {
            if (current.getInfo() == info) {
                if (current == this.first) {
                    this.first = current.getNext();
                }
                else if (current == this.last) {
                    this.last = previous;
                    if (previous != null)
                        previous.setNext(current.getNext());
                }
                else {
                    if (previous != null)
                        previous.setNext(current.getNext());
                }
                this.size--;
            }
            else {
                previous = current;
            }
            current = current.getNext();
        }
    }
    @Override
    public void removeInto(int index) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (" + this.getSize() + ")");

        Node<X> current = this.first, previous = null;

        if (index != 0) {
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.getNext();
            }
            if (current != null) {
                previous.setNext(current.getNext());
                if (previous.getNext() == null)
                    this.last = previous;
            }
            else {
                previous.setNext(null);
            }
        }
        else {
            this.first = this.first.getNext();
        }
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
        for (Node<X> current = this.first; current != null; current = current.getNext()) {
            try {
                message.append(current.getInfo().toString());
                if (current.getNext() != null) message.append(", ");

            } catch (Exception ignored) {}
        }

        return message.toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();

        LinkedListUnordered<X> ret = null;
        try {
            ret = new LinkedListUnordered<>(this);
        }
        catch (Exception ignored) {}
        return ret;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (this.getClass() != obj.getClass()) return false;

        LinkedListUnordered<X> data = (LinkedListUnordered<X>)obj;

        try {
            if (!this.first.getInfo().equals(data.first.getInfo())) return false;
            if (!this.last.getInfo().equals(data.last.getInfo())) return false;
            if (this.size != data.size) return false;
            Node<X> currentThis = this.first, currentData = data.first;

            for (int i = 0; i < this.getSize(); i++) {
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

        hash = 3 * hash + this.first.hashCode();
        hash = 3 * hash + this.last.hashCode();
        hash = 3 * hash + Integer.valueOf(this.size).hashCode();

        if (hash < 0) hash *= -1;

        return hash;
    }
}
