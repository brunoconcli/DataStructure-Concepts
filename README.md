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

## Old equals using 
// equals using linkedListOrered's equals()
@Override
@SuppressWarnings("unchecked")
public boolean equals(Object obj) {
	try {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
			
		BinarySearchTree<X> model = (BinarySearchTree<X>) obj;
			
		if (this.root.getInfo().compareTo(model.root.getInfo()) != 0) return false;
		if (this.size != model.size) return false;
		if (this.height != model.height) return false;

		return (this.getOrderedArray(this.root).equals(model.getOrderedArray(model.root)));
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return false;
}