import linkedList.LinkedListCircular;
import linkedList.LinkedListUnordered;

public class Main  {
    public static void main(String[] args) {
        try {
            LinkedListCircular<String> uno = new LinkedListCircular<>();
            LinkedListCircular<String> dos = new LinkedListCircular<>();

            uno.addFirst("A");
            uno.addLast("B");
            uno.addLast("C");
            uno.addLast("D");

            dos.addFirst("A");
            dos.addLast("B");
            dos.addLast("C");

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}