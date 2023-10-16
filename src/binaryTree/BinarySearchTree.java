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
	private int size = 0;

	public void add(X info) throws Exception {
		if (info == null) throw new Exception("Info passed must not be null");
		if (this.root == null) {
			this.root = new Node(info);
			return;
		}
		Node current = this.root;
		int comparison = info.compareTo(current.getInfo());
		
		for (;;) {
			if (comparison == 0) throw new Exception("Info passed already exists");
			if (comparison < 0) {
				if (current == null) break;
				current = current.getLeft();
			}
			else {
				if (current == null) break;
				current = current.getRight();
			}

			comparison = info.compareTo(current.getInfo());
		}
		current = new Node(info);
		this.size ++;
	}

	public boolean alreadyExists(X info) {
		return false;
	}
}
