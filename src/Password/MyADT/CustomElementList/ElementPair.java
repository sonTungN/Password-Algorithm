package Password.MyADT.CustomElementList;

public class ElementPair<T, E> {
    public T key;
    public E value;

    public ElementPair(T key, E value) {
        this.key = key;
        this.value = value;
    }
}
