package linkedLists;
public class Node <N>{
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

