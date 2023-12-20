package Password.MyADT.Element;

/**
 * Create Node class using to handle the HashMap collisions with Separate Chaining Hashing.
 */
public class Node<T, E> {
    public Pair<T, E> data;
    public Node<T, E> next;

    public Node(Pair<T, E> pair) {
        data = pair;
        next = null;
    }
}
