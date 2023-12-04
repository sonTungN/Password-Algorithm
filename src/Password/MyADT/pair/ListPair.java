package Password.MyADT.pair;

public class ListPair<T, E> {
    public NodePair<String, Integer> head;
    private int size;

    public ListPair(){
        head = null;
        size = 0;
    }

    public boolean insert(Pair<String, Integer> node){
        if(size == 0){
            head = new NodePair<>(node);
            size  = 1;
            return true;
        }

        NodePair<String, Integer> parent = null;
        NodePair<String, Integer> tmp = head;
        while(tmp != null){
            if(node.key.equals(tmp.data.key)){
                return false;
            }
            parent = tmp;
            tmp = tmp.next;
        }

        assert parent != null;
        parent.next = new NodePair<>(node);
        size++;
        return true;
    }

    public Pair<String, Integer> get(String key){
        NodePair<String, Integer> tmp = head;
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

        NodePair<String, Integer> parent = null;
        NodePair<String, Integer> tmp = head;
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

