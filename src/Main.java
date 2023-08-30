import linkedLists.*;

public class Main  {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			LinkedListUnordered<String> uno = new LinkedListUnordered<>();
			LinkedListCircular<String> dos = new LinkedListCircular<>();
			LinkedListDoubly<String> tres = new LinkedListDoubly<>();
			LinkedListOrdered<Integer> cuatro = new LinkedListOrdered<>();
		
			LinkedListDoublyCircular<String> cinqo = new LinkedListDoublyCircular<>();
			cinqo.addFirst("C");
			System.out.println(cinqo.toString());

			cinqo.addFirst("B");
			System.out.println(cinqo.toString());

			cinqo.addLast("D");
			System.out.println(cinqo.toString());

			cinqo.addFirst("A");
			System.out.println(cinqo.toString());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}