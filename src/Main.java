import linkedLists.*;
import linkedLists.circular.LinkedListCircular;
import linkedLists.circular.LinkedListDoublyCircular;
import linkedLists.doubly.LinkedListDoubly;

public class Main  {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			LinkedListUnordered<String> uno = new LinkedListUnordered<>();
			LinkedListCircular<String> dos = new LinkedListCircular<>();
			LinkedListDoubly<String> tres = new LinkedListDoubly<>();
			LinkedListOrdered<Integer> cuatro = new LinkedListOrdered<>();
		
			LinkedListDoublyCircular<String> cinqo = new LinkedListDoublyCircular<>();
			LinkedListDoublyCircular<String> cinqoCopy = new LinkedListDoublyCircular<>();			

			cinqo.addFirst("B");
			cinqo.addFirst("A");
			cinqo.addLast("C");

			cinqoCopy.addLast("A");
			cinqoCopy.addLast("B");
			cinqoCopy.addLast("C");
			
			System.out.println(
				"cinqo: " + cinqo.toString() + 
				"\ncinqoCopy: " + cinqoCopy.toString() +
				"\nequals: " + cinqo.equals(cinqoCopy) +
				"\nelement at 1:" + cinqo.getElementAt(1)
			);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}