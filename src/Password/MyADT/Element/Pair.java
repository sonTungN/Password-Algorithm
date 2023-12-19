package Password.MyADT.Element;

/**
 * Create Pair<T, E> class using as the HashMap element.
 */
public class Pair<T, E> {
    public T key;
    public E value;

    public Pair(T key, E value) {
        this.key = key;
        this.value = value;
    }
}
