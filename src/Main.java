import binaryTree.BinarySearchTree;
public class Main  {
	public static void main(String[] args) {
		try {
			int[] numberSet = {10, 5, 6, 2, 1, 3, 12, 15, 17};
			BinarySearchTree<Integer> uno = new BinarySearchTree<>(); 
			BinarySearchTree<Integer> dos = new BinarySearchTree<>(); 
			
			double begin = System.currentTimeMillis();
			for (int i = 0; i < numberSet.length; i++) {
				uno.add(numberSet[i]);
				dos.add(numberSet[i]);
			}
			
			dos.add(7);
			double end = System.currentTimeMillis();
			System.out.println(uno.equals(dos));
			System.out.println((end - begin)/1000);

			System.out.println(uno.toString());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
