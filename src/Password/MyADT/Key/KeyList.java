package Password.MyADT.Key;

/**
 * Create KeyList to handle the collisions using Separate Chaining Hashing when using the KeySet.
 */
public class KeyList {
    public KeyNode head;
    public int size;

    public KeyList(){
        head = null;
        size = 0;
    }

    /**
     * Insert the key into the KeySet as the corresponding element is inserted.
     *
     * @param key The String key of the element
     * @return boolean Return true if there is no duplicate, and false if a duplicate is found.
     */
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

    /***
     * Remove the key from the KeySet as the corresponding element is removed.
     *
     * @param key The key of the removed element.
     * @return boolean Return true if removed successfully, and false when size == 0 or failed.
     */
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

    /**
     * To check if the KeySet contains that key.
     * @param key The search key.
     * @return boolean Return true if key is found, false if no key is found.
     */
    public boolean contains(String key){
        if(size == 0){
            return false;
        }

        KeyNode tmp = head;
        while(tmp != null){
            if(key.equals(tmp.data)){
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }
}
