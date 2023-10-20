import binaryTree.BinarySearchTree;
import linkedLists.ordered.LinkedListOrdered;
public class Main  {
	public static void main(String[] args) {
		try {
			BinarySearchTree<Integer> uno = new BinarySearchTree<>(); 

			uno.add(10);
			uno.add(5);
			uno.add(6);
			uno.add(2);
			uno.add(1);
			uno.add(3);
			uno.add(12);
			uno.add(15);
			uno.add(17);
			BinarySearchTree<Integer> dos = null; 
			

			System.out.println(uno.equals(dos));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
