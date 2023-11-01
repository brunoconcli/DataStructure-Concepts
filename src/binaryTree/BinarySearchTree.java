package binaryTree;
import linkedLists.ordered.LinkedListOrdered;
import linkedLists.unordered.LinkedListUnordered;

public class BinarySearchTree<X extends Comparable<X>> implements Cloneable {
	
	private class Node implements Cloneable, Comparable<X> {
		private Node left;
		private Node right;
		private X info;

		public Node(Node left, X info, Node right) throws Exception {
			if (info == null) throw new Exception ("Info must not be null");
			this.left = left;
			this.info = info;
			this.right = right;
		}

		public Node(X info) throws Exception {
			this(null, info, null);
		}

		public X getInfo() {
			return this.info;
		}

		public Node getLeft() {
			return this.left;
		}

		public Node getRight() {
			return this.right;
		}

		public void setInfo(X info) {
			this.info = info;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		@Override
		public String toString() {
			return (
				this.left.getInfo() + 
				", " + 
				this.info + 
				", " + 
				this.right.getInfo());
		}
		
		@Override
		public int compareTo(X info) {
			return 1;
		}
	}

	private Node root;
	private int height = 0, size = 0;

	public void add(X info) throws Exception {
		if (info == null) throw new Exception("Info passed must not be null");
		if (this.root == null) {
			this.root = new Node(info);
			return;
		}
		Node current = this.root, previous = null;
		int comparison = info.compareTo(current.getInfo());
		
		for (;;) {
			if (comparison == 0) throw new Exception("Info passed already exists");
			if (comparison < 0) {
				previous = current;
				current = current.getLeft();
			}
			else {
				previous = current;
				current = current.getRight();
			}

			if (current == null) break;
			comparison = info.compareTo(current.getInfo());
		}
		if (previous.getLeft() == null && previous.getRight() == null) {
			this.height ++;
		}
		current = new Node(info);
		if (previous.getInfo().compareTo(current.getInfo()) < 0) previous.setRight(current);
		else previous.setLeft(current);
		this.size ++;
	}
	// three cases for removing:
		// node is a leaf - tree not affected
		// node has one child - its antecedent's pointer is redirected to the node's child 
		// node has two children - last node in the node.getRight()'s left replaces the node 
	public void remove(X info) throws Exception {
		if (info == null) throw new Exception ("Information passed must not be null");

		Node current = this.root, previous = null;
		int compare = info.compareTo(current.getInfo());
		for (;;) {
			if (compare == 0) {
				if (current.getLeft() == null && current.getRight() == null) {
					if (previous != null) { 
						if (previous.getLeft().getInfo().compareTo(current.getInfo()) == 0) 
						previous.setLeft(null);
						else
						previous.setRight(null);
					}
					if (this.root == current) 
						this.root = null;
					current = null;
					this.size -= 1;
					return;
				}
				
				if (current.getLeft() != null && current.getRight() == null) {
					if (previous != null) { 
						if (previous.getLeft().getInfo().compareTo(current.getInfo()) == 0) 
							previous.setLeft(current.getLeft());
						else
							previous.setRight(current.getLeft());
					}
					current = null;
					this.size -= 1;
					return;
				}
				
				if (current.getLeft() == null && current.getRight() != null) {
					if (previous != null) { 
						if (previous.getLeft().getInfo().compareTo(current.getInfo()) == 0) 
							previous.setLeft(current.getRight());
						else
							previous.setRight(current.getRight());
					}
					current = null;
					this.size -= 1;
					return;
				}
				if (current.getLeft() != null & current.getRight() != null) {
					Node currentSearch = current.getRight();
					while (currentSearch.getLeft() != null) {
						currentSearch = currentSearch.getLeft();
					}
					this.remove(currentSearch.getInfo());
					current.setInfo(currentSearch.getInfo());
					return;
				}
			}
			previous = current;
			if (compare < 0) {
				current = current.getLeft();
			}
			else {
				current = current.getRight();
			}
			if (current == null) throw new Exception ("Information passed has not been found");
			compare = info.compareTo(current.getInfo());
		}
	}

	private LinkedListOrdered<X> getOrderedLinkedList(Node root) throws Exception {
		LinkedListOrdered<X> list = new LinkedListOrdered<>();
		if (root == null) return new LinkedListOrdered<>();
		
		list.add(root.getInfo());
		return list.addList(getOrderedLinkedList(root.getLeft())).addList(getOrderedLinkedList(root.getRight()));
	}

	public String getOrderedLinkedList() throws Exception {
		return this.getOrderedLinkedList(this.root).toString();
	}

	private int getSize(Node root) {
		if (root == null) return 0;
		return 1 + (this.getSize(root.getLeft()) + this.getSize(root.getRight()));
	}

	public int getSize() {
		return this.getSize(this.root);
	}

	public LinkedListUnordered<X> getPreorderedArray() {
		// add nodes as they're visited for the first time
		return new LinkedListUnordered<>();
	}

	public LinkedListUnordered<X> getPostOrderedArray() {
		// add nodes as they're visited for the third time
		return new LinkedListUnordered<>();
	}

	public LinkedListUnordered<X> getInOrderedArray() {
		// add nodes as they're visited for the second time
		return new LinkedListUnordered<>();	
	}
	
	public boolean alreadyExists(X info) {
		if (info == null) return false;
		if (this.root == null) return false;

		Node current = this.root;
		int comparison;
		while (current != null) {
			comparison = current.compareTo(info);
			if (comparison == 0) 
				return true;
			if (comparison < 0)
				current = current.getLeft();
			else 
				current = current.getRight();
		}
		return false;
	}

	private String toString(Node root) {
		String left = "";
		String right = "";
		if (root.getLeft() != null)
			left = "("+this.toString(root.getLeft())+")";
		if (root.getRight() != null)
			right = "("+this.toString(root.getRight())+")";	

		return (left+"(" + root.getInfo() + ")"+right);
	}

	@Override
	public String toString() {
		return this.toString(this.root);
	}

	private boolean equals(Node thisRoot, Node modelRoot) {
		if (thisRoot == modelRoot && thisRoot == null) return true;
		if (thisRoot.getInfo().compareTo(modelRoot.getInfo()) != 0) return false;

		return this.equals(thisRoot.getLeft(), modelRoot.getLeft()) && this.equals(thisRoot.getRight(), modelRoot.getRight());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;

		if (this.getClass() != obj.getClass()) return false;
		BinarySearchTree<X> tree = (BinarySearchTree) obj;
		if (this.size != tree.size) return false;
		if (this.height != tree.height) return false;

		return this.equals(this.root, tree.root);
	}
}
