package linkedLists.extended;

import linkedLists.ILinkedList;

public class BaseLinkedListDoubly<X> implements ILinkedList<X>, Cloneable {
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
    protected int size = 0;

    public BaseLinkedListDoubly() {}

    public BaseLinkedListDoubly(BaseLinkedListDoubly<X> model) throws Exception {
        if (model == null) 
            throw new Exception("The model object passed cannot be null");
        this.first = model.first;
        this.last = model.last;
        this.size = model.size;
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
        BaseLinkedListDoubly<X> ret = null;
        try {
            ret = new BaseLinkedListDoubly<>(this);
        }
        catch(Exception ignored) {}

        return ret;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (this.getClass() != obj.getClass()) return false;

        try {
            BaseLinkedListDoubly<X> data = (BaseLinkedListDoubly<X>) obj;
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
