package Password.MyADT.Element;

public class Chain<T, E> {
    public Node<String, Integer> head;
    private int size;

    public Chain(){
        head = null;
        size = 0;
    }

    public boolean insert(Pair<String, Integer> element){
        if(size == 0){
            head = new Node<>(element);
            size  = 1;
            return true;
        }

        Node<String, Integer> parent = null;
        Node<String, Integer> tmp = head;
        while(tmp != null){
            if(element.key.equals(tmp.data.key)){
                return false;
            }
            parent = tmp;
            tmp = tmp.next;
        }

        assert parent != null;
        parent.next = new Node<>(element);
        size++;
        return true;
    }

    public Pair<String, Integer> get(String key){
        Node<String, Integer> tmp = head;
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

        Node<String, Integer> parent = null;
        Node<String, Integer> tmp = head;
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

