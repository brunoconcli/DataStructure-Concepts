package linkedList;

public class LinkedListDoubly<X extends Comparable<X>> implements ILinkedList, Cloneable {

    private Node<X> first, last;
    private int size = 0;

    public void addFirst(X info) throws Exception {
        if (info == null) throw new Exception("Information passed must not be null");
        this.first = new Node<X>(info, this.first);

        if (this.last == null)
            this.last = this.first;

        this.size++;
    }
//    public void addLast(X info) throws Exception {
//        if (info == null) throw new Exception("Information passed must not be null");
//        this.last = new Node<X>(this.last, info);
//
//        if (this.first == null)
//            this.last = this.first();
//    }
    public int getSize() {
        return this.size;
    }
    @Override
    public boolean isEmpty() {
        return (this.getSize() == 0);
    }
    @Override
    public void removeInto(int index) throws Exception {

    }
    @Override
    public void removeAllElements() {

    }
}
