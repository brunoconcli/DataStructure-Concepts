## Annotations before acctually doing all to tests

### LinkedListUnordered
>

### LinkedLisDoubly
> addFirst(): working
> addLast(): working
> addAfter(): working, all scenarios (2, middle and last position)
> removeInto(): working
> removeAllElements(): working


## previous Binary Search Tree `getOrderedArray()` 
LinkedListOrdered<X> orderedArray = new LinkedListOrdered<>();
	
private void getOrderedArray(Node node) throws Exception {
    if (node == null) return;
    orderedArray.add(node.getInfo());
    getOrderedArray(node.getLeft());
    getOrderedArray(node.getRight());
}

public String getOrderedArray() throws Exception {
    orderedArray.removeAllElements();
    getOrderedArray(this.root);
    return this.orderedArray.toString();
}
