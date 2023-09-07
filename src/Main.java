import linkedLists.ordered.LinkedListOrdered;

public class Main  {
	public static void main(String[] args) {
		try {
			LinkedListOrdered<String> uno = new LinkedListOrdered<String>();
			
			uno.add("C");
			System.out.println(uno.toString());

			uno.add("A");
			System.out.println(uno.toString());

			uno.add("B");
			System.out.println(uno.toString());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
