package Password.MyADT.Key;

public class KeyList {
    public KeyNode head;
    public int size;

    public KeyList(){
        head = null;
        size = 0;
    }

    public boolean insert(String key){
        if(size == 0){
            head = new KeyNode(key);
            size = 1;
            return true;
        }

        KeyNode parent = null;
        KeyNode tmp = head;
        while(tmp != null){
            if(key.equals(tmp.data)){
                return false;
            }

            parent = tmp;
            tmp = tmp.next;
        }
        assert parent != null;
        parent.next = new KeyNode(key);
        size++;
        return true;
    }

    public boolean remove(String key){
        if(size == 0){
            return false;
        }

        KeyNode parent = null;
        KeyNode tmp = head;
        while(tmp != null){
            if(key.equals(tmp.data)){
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
}
