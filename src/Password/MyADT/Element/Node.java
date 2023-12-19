package Password.MyADT.Element;

public class Node<T, E> {
    public Pair<T, E> data;
    public Node<T, E> next;
    public Node(Pair<T, E> pair) {
        data = pair;
        next = null;
    }
}
