package linkedLists.doubly;

import linkedLists.ILinkedList;
import linkedLists.Node;

public class LinkedListDoubly<X extends Comparable<X>> implements ILinkedList<X>, Cloneable {

    private Node<X> first, last;
    private int size = 0;

    public LinkedListDoubly() {}

    public LinkedListDoubly(LinkedListDoubly<X> model) throws Exception {
        if (model == null) 
            throw new Exception("The model object passed cannot be null");
        this.first = model.first;
        this.last = model.last;
        this.size = model.size;
    }

    public void addFirst(X info) throws Exception {
        if (info == null) throw new Exception("Information passed must not be null");
        
        this.first = new Node<X>(info, this.first);
        
        if (this.getSize() != 0)
            this.first.getNext().setPrev(this.first);
        
        if (this.last == null)
            this.last = this.first;

        this.size++;
    }

    public void addLast(X info) throws Exception {
        if (info == null) throw new Exception("Information passed must not be null");
        
        if (this.first == null) {
            this.addFirst(info);
            return;
        }
        else {
            this.last.setNext(new Node<X>(this.last, info));
            this.last = this.last.getNext();
        }
    
        this.size++;
    }

    public void addAfter(int index, X info) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (" + this.getSize() + ")");
        
        if (index == this.getSize() - 1) {
            this.addLast(info);
            return;
        }
        Node<X> current = this.first, added = null;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setNext(new Node<X>(current, info, current.getNext()));
        added = current.getNext();
        added.getNext().setPrev(added);

        this.size++;
    }

    public int getSize() {
        return this.size;
    }

    public X getElementAt(int index) throws Exception {
        if (index < 0 || index >= this.getSize())
            throw new Exception ("The index passed must be between 0 and the list's length (" + this.getSize() + ")");
        Node<X> current = this.first;
        for (int i = 0;i < index; i++) {
            current = current.getNext();
        }

        return current.getInfo();
    }

    @Override
    public boolean isEmpty() {
        return (this.getSize() == 0);
    }

    public void removeFirst() throws Exception {
        this.removeInto(0);
    }

    public void removeLast() throws Exception {
        this.removeInto(this.getSize() -1);
    }

    @Override
    public void removeInto(int index) throws Exception {
        if (index < 0 || index >= this.getSize()) throw new Exception("Index passed must be between 0 and the list's length (" + this.getSize() + ")");
        
        if (index == 0) {
            this.first = this.first.getNext();
            this.first.setPrev(null);
            return;
        }
        else if (index == this.getSize() - 1) {
            this.last = this.last.getPrev();
            this.last.setNext(null);
            return;
        }
      
        Node<X> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
        current = null;

        this.size--;
    }

    @Override
    public void removeAllElements() {
        this.first = this.last = null;
        this.size = 0;
    }

    public void removeFirstElementWithInfo(X info) {

    }

    public void removeAllElementsWithInfo(X info) {

    }

    @Override
    public String toString() {
        if (this.isEmpty())
            return "List is empty";
        StringBuilder message = new StringBuilder();
        try {
            for (Node<X> current = this.first; current != null; current = current.getNext()) {
                message.append(current.getInfo().toString());
                if (current.getNext() != null) message.append(", ");
            }
        }
        catch (Exception ignored) {}

        return message.toString();
    }

    @Override
    public Object clone() {
        LinkedListDoubly<X> ret = null;
        try {
            ret = new LinkedListDoubly<>(this);
        }
        catch(Exception ignored) {}

        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (this.getClass() != obj.getClass()) return false;

        try {
            LinkedListDoubly<X> data = (LinkedListDoubly<X>) obj;
            if (!this.first.getInfo().equals(data.first.getInfo())) return false; // should it be !equals() or !=?
            if (!this.last.getInfo().equals(data.last.getInfo())) return false;
            if (this.size != data.size) return false;

            Node<X> currentThis = this.first, currentData = data.first;
            for (int i = 0; i< this.getSize(); i++) {
                if (!currentThis.getInfo().equals(currentData.getInfo())) return false;
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
        
        hash = 3*hash + this.first.hashCode();
        hash = 3*hash + this.last.hashCode();
        hash = 3*hash + Integer.valueOf(this.size).hashCode();

        if (hash < 0) hash = -hash;
        return hash;
    }
}
