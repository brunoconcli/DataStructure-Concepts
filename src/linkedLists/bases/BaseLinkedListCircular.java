package linkedLists.bases;

import linkedLists.ILinkedList;

public class BaseLinkedListCircular<X> implements ILinkedList<X>, Cloneable {
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
    public BaseLinkedListCircular() {}
    public BaseLinkedListCircular(BaseLinkedListCircular<X> model) throws Exception {
        if (model == null)
            throw new Exception ("Model passed cannot be null");
        this.first = model.first;
        this.last = model.last;
        this.size = model.size;
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
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (this.getClass() != obj.getClass());
        BaseLinkedListCircular<X> data = (BaseLinkedListCircular<X>) obj;

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
        BaseLinkedListCircular<X> ret = null;
        try {
            ret = new BaseLinkedListCircular<>(this);
        }
        catch(Exception ignore) {}
        return ret;
    }

}

