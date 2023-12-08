package Password.MyADT.CustomElementList;

public class ElementNode<T, E> {
    public ElementPair<T, E> data;
    public ElementNode<T, E> next;

    public ElementNode(ElementPair<T, E> pair) {
        data = pair;
        next = null;
    }
}
