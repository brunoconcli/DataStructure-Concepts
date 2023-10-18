import linkedLists.ordered.LinkedListOrdered;
import linkedLists.unordered.LinkedListUnordered;
import stackAndQueue.Stack;
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
				if (current == null) break;
				previous = current;
				current = current.getLeft();
			}
			else {
				if (current == null) break;
				previous = current;
				current = current.getRight();
			}

			comparison = info.compareTo(current.getInfo());
		}
		if (previous.getLeft() == null && previous.getRight() == null) {
			this.height ++;
		}
		current = new Node(info);
		this.size ++;
	}

	public LinkedListOrdered<X> preorderTest(Node node) {

	}
	// public LinkedListOrdered<X> getOrderedArray() {
	// 	// add nodes in the increasing order
	// 	try {
	// 		LinkedListOrdered<X> orderedArray = new LinkedListOrdered<>();
	// 		Stack<X> stack = new Stack<>();
	// 		Node current = this.root, previous = null;

	// 		while (true) {
	// 			if (current != null) {
	// 				stack.push(current.getInfo());
	// 				current = current.getLeft();
	// 			}
	// 		}
	// 	}
	// 	catch (Exception e) {
	// 		System.out.println(e.getMessage());
	// 	}
	// }

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

	@Override
	public String toString() {
		LinkedListOrdered<X> arrayRepresentation = new LinkedListOrdered<>();

		return "";
	}
}
