public class Main  {
	public static void main(String[] args) {
		try {
			BinarySearchTree<Integer> tree = new BinarySearchTree<>(); 

			tree.add(10);
			tree.add(5);
			tree.add(6);
			tree.add(2);
			tree.add(1);
			tree.add(3);
			tree.add(12);
			tree.add(15);
			tree.add(17);

			System.out.println(tree.getOrderedArray());
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
