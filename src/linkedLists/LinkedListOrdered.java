package linkedLists;

public class LinkedListOrdered<X extends Comparable<X>> implements ILinkedList<X>, Cloneable {

    private Node<X> first, last;
    private int size = 0;

    public void add(X info) throws Exception {
        if (info == null) throw new Exception("Info passed must not be null");

        if (this.first == null) {
            this.first = new Node<X>(info, null);
            this.last = this.first;
            return;
        }

        // compareTo() < 0 --> this < obj
        // compareTo() > 0 --> this > obj 
        
        // for (Node<X> current = this.first; current != null; current = current.getNext()) {
        //     if (current.getInfo().compareTo(info) )
        // }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void removeInto(int index) {

    }

    @Override
    public void removeAllElements() {

    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();

        try {
            for (Node<X> current = this.first; current != null; current = current.getNext()) {
                message.append(current.getInfo());
                if (current.getNext() != null) message.append(", ");
            }
        }
        catch (Exception ignored) {}

        return message.toString();
    }
}
