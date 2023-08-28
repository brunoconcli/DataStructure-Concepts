package linkedLists;

public class LinkedListCircular<X extends Comparable<X>> implements ILinkedList, Cloneable {
    public Node<X> first, last;
    int size = 0;
    public LinkedListCircular() {}
    public LinkedListCircular(LinkedListCircular<X> model) throws Exception {
        if (model == null)
            throw new Exception ("Model passed cannot be null");
        this.first = model.first;
        this.last = model.last;
        this.size = model.size;
    }

    public void addFirst(X info) throws Exception {
        if (info == null)
            throw new Exception ("Information passed must not be null");

        this.first = new Node<X>(info, this.first);

        if (this.last == null)
            this.last = this.first;
        else
            this.last.setNext(this.first);

        this.size ++;
    }
    public void addLast(X info) throws Exception {
        if (info == null) throw new Exception ("Information passed must not be null");
        Node<X> toBeInserted = new Node<X>(info, this.first);
        this.last.setNext(toBeInserted);
        this.last = toBeInserted;

        this.size ++;
    }
    public void addAfter(int index, X info) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (\" + this.getSize() + \")");

        if (index == this.getSize() - 1) {
            this.addLast(info);
            return;
        }
        
        Node<X> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setNext(new Node<X>(info, current.getNext()));

        this.size++;
    }
    public X getElementAt(int index) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (" + this.getSize() + ")");

        Node<X> current = this.first;
        for (int i = 0; i < index; i++) 
            current = current.getNext();
        
        return current.getInfo();
    }
    public int getSize() {
        return this.size;
    }
    @Override
    public boolean isEmpty() {
        return this.first == null;
    }
    public void removeFirst() throws Exception {
        this.removeInto(0);
    }
    public void removeLast() throws Exception {
        this.removeInto(getSize() - 1);
    }
    public void removeFirstElementWithInfo(X info) {

    }
    public void removeAllElementsWithInfo(X info) {
        
    }
    @Override
    public void removeInto(int index) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (\" + this.getSize() + \")");

        Node<X> current = this.first, previous = null;
        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.getNext();
        }
        if (current.getNext() == this.first) {
            this.last = previous;
        }
        else if (current == this.first) {
            this.first = this.first.getNext();
            this.last.setNext(this.first);
        }
        if (previous != null)
            previous.setNext(current.getNext());
        this.size--;
    }
    @Override
    public void removeAllElements() {
        this.last = this.first = null;
        size = 0;
    }
    @Override 
    public String toString() {
        StringBuilder message = new StringBuilder();
        Node<X> current = this.first;
        try {
            while (current != this.last) {
                message.append(current.getInfo().toString()).append(", ");
                current = current.getNext();
            }
            message.append(current.getInfo().toString());
        }
        catch (Exception ignored) {}
        return message.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (this.getClass() != obj.getClass());
        LinkedListCircular<X> data = (LinkedListCircular<X>) obj;

        try {
            if (!this.first.getInfo().equals(data.first.getInfo())) return false;
            if (!this.last.getInfo().equals(data.last.getInfo())) return false;
            if (this.size != data.size) return false;

            Node<X> currentThis = this.first, currentData = data.first;
            for (int i = 0; i < this.getSize(); i ++) {
                if (!currentThis.getInfo().equals(currentData.getInfo())) return false;
                currentThis = currentThis.getNext();
                currentData = currentData.getNext();
            }
        }
        catch (Exception ignored) {}

        return true;
    }
    @Override
    public int hashCode() {
        int hash = 2;
        hash = 3*hash + this.first.hashCode();
        hash = 3*hash + this.last.hashCode();
        hash = 3*hash + Integer.valueOf(this.size).hashCode();

        if (hash < 0) hash *= -1;
        return hash;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        // Object clone = super.clone();
        LinkedListCircular<X> ret = null;
        try {
            ret = new LinkedListCircular<>(this);
        }
        catch(Exception ignore) {}
        return ret;
    }

}

