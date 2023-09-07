package linkedLists;

public interface ILinkedList<X> {
    public X getElementAt(int index) throws Exception;
    
    public boolean isEmpty();
    
    public int getSize();

    public void removeInto(int index) throws Exception;

    public void removeAllElements();
}

// Three types of add: addFirst(X info), addLast(X info), addAfter(int index, X info)