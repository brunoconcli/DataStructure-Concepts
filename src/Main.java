import linkedLists.LinkedListUnordered;
import linkedLists.LinkedListDoubly;
import linkedLists.LinkedListOrdered;
import linkedLists.LinkedListCircular;

public class Main  {
    public static void main(String[] args) {
        try {
            LinkedListUnordered<String> uno = new LinkedListUnordered<>();
            LinkedListCircular<String> dos = new LinkedListCircular<>();
            LinkedListDoubly<String> tres = new LinkedListDoubly<>();
            LinkedListOrdered<Integer> cuatro = new LinkedListOrdered<>();

            cuatro.add(1);
            cuatro.add(2);
            System.out.println(cuatro.toString());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}