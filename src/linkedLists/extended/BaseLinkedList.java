package linkedLists.extended;
import linkedLists.*; // put class Node inside each class
public class BaseLinkedList<X> implements ILinkedList<X>, Cloneable {
    protected class Node <N>{
        private N info;
        private Node <N> next;
        public Node (N info) {
            this.info = info;
            this.next = null;
        }
        public Node (N info, Node <N> next) {
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
        public void setInfo(N info) {
            this.info = info;
        }
        public void setNext(Node <N> next) {
            this.next = next;
        }
        @Override
        public String toString() {
            String message = "";
            try {
                message += getInfo() + " -> " + null;
                if (getNext() != null)
                    message = getInfo() + " -> " + getNext().getInfo();
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
            if (this.info != data.info) return false;
            if (this.next != data.next) return false;

            return true;
        }
    }

    protected Node<X> first, last;
    protected int size = 0;

    public BaseLinkedList() {}
    
    public BaseLinkedList(X[] list) throws Exception {
        if (list == null) throw new Exception ("List passed must not be null");
        if (list.length == 0)  throw new Exception ("List's lenght must be greater than zero");

        this.size = list.length;

        if (list.length == 1) {
            this.first = this.last = new Node<X>(list[0], null);
            return;
        }
        this.first = new Node<X>(list[0], null);
        this.last = new Node<X>(list[list.length - 1], null);

        Node<X> current = this.first;
        for(int i = 1; i < list.length - 1; i++) {
            current.setNext(new Node<X>(list[i], null));
            current = current.getNext();
        }
        current.setNext(this.last);
    }
    
    protected BaseLinkedList(BaseLinkedList<X> model) throws Exception {
        if (model == null)
            throw new Exception("Model list in copy constructor cannot be null");
        this.first = model.first;
        this.last = model.last;
        this.size = model.size;
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
    public Object clone() throws CloneNotSupportedException {
        BaseLinkedList<X> ret = null;
        try {
            ret = new BaseLinkedList<>(this);
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

        BaseLinkedList<X> data = (BaseLinkedList<X>)obj;

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
