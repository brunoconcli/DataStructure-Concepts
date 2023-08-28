import linkedLists.LinkedListUnordered;
import linkedLists.LinkedListDoubly;
import linkedLists.LinkedListCircular;

public class Main  {
    public static void main(String[] args) {
        try {
            LinkedListUnordered<String> uno = new LinkedListUnordered<>();
            LinkedListCircular<String> dos = new LinkedListCircular<>();
            LinkedListDoubly<String> tres = new LinkedListDoubly<>();

            uno.addFirst("A");
            uno.addLast("B");
            uno.addLast("C");
            System.out.println(uno.toString());
            dos.addFirst("A");
            dos.addLast("B");
            dos.addLast("C");

            tres.addFirst("X");
            tres.addLast("Z");
            tres.addAfter(0, "Y");
            tres.addFirst("W");
            tres.addAfter(3, "A");
            System.out.println(tres.toString());

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}