package Password.MyADT.pair;

public class NodePair<T, E> {
    public Pair<T, E> data;
    public NodePair<T, E> next;

    public NodePair(Pair<T, E> pair) {
        data = pair;
        next = null;
    }
}
