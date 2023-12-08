package Password.MyADT.CustomElementList;

public class ElementList<T, E> {
    public ElementNode<String, Integer> head;
    private int size;

    public ElementList(){
        head = null;
        size = 0;
    }

    public boolean insert(ElementPair<String, Integer> node){
        if(size == 0){
            head = new ElementNode<>(node);
            size  = 1;
            return true;
        }

        ElementNode<String, Integer> parent = null;
        ElementNode<String, Integer> tmp = head;
        while(tmp != null){
            if(node.key.equals(tmp.data.key)){
                return false;
            }
            parent = tmp;
            tmp = tmp.next;
        }

        assert parent != null;
        parent.next = new ElementNode<>(node);
        size++;
        return true;
    }

    public ElementPair<String, Integer> get(String key){
        ElementNode<String, Integer> tmp = head;
        while(tmp != null){
            if(tmp.data.key.equals(key)){
                return tmp.data;
            }
            tmp = tmp.next;
        }
        return null;
    }

    public boolean remove(String key){
        if(size == 0){
            return false;
        }

        ElementNode<String, Integer> parent = null;
        ElementNode<String, Integer> tmp = head;
        while(tmp != null){
            if(tmp.data.key.equals(key)){
                if(tmp == head){
                    head = head.next;
                    size--;
                    return true;
                }
                parent.next = tmp.next;
                size--;
                return true;
            }
            parent = tmp;
            tmp = tmp.next;
        }
        return false;
    }

    public int size(){
        return size;
    }
}

