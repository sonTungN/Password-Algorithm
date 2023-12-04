package Password.MyADT.myset;

public class ListString {
    public NodeString head;
    public int size;

    public ListString(){
        head = null;
        size = 0;
    }

    public boolean insert(String str){
        if(size == 0){
            head = new NodeString(str);
            size = 1;
            return true;
        }

        NodeString parent = null;
        NodeString tmp = head;
        while(tmp != null){
            if(str.equals(tmp.data)){
                return false;
            }

            parent = tmp;
            tmp = tmp.next;
        }
        assert parent != null;
        parent.next = new NodeString(str);
        size++;
        return true;
    }

    public boolean remove(String str){
        if(size == 0){
            return false;
        }

        NodeString parent = null;
        NodeString tmp = head;
        while(tmp != null){
            if(str.equals(tmp.data)){
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

    public boolean contains(String str){
        if(size == 0){
            return false;
        }

        NodeString tmp = head;
        while(tmp != null){
            if(str.equals(tmp.data)){
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }
}
