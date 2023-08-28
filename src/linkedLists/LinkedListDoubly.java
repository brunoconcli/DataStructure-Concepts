package linkedLists;

public class LinkedListDoubly<X extends Comparable<X>> implements ILinkedList, Cloneable {

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
        this.last.setNext(new Node<X>(this.last, info));
        this.last = this.last.getNext();
    

        if (this.first == null)
            this.first = this.last;

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
    @Override
    public boolean isEmpty() {
        return (this.getSize() == 0);
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
        
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 2;
        
        return hash;
    }
}
