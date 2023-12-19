package Password.MyADT.Element;

public class Pair<T, E> {
    public T key;
    public E value;

    public Pair(T key, E value) {
        this.key = key;
        this.value = value;
    }
}
