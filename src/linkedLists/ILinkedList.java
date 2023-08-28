package linkedLists;

public interface ILinkedList<X> {
    public boolean isEmpty();
    public void removeInto(int index) throws Exception;
    public void removeAllElements();
}

// Three types of add: addFirst(X info), addLast(X info), addAfter(int index, X info)