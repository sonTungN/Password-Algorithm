package Password.MyADT.Element;

/**
 * LinkedList to handle the collisions in HashMap with Separate Chaining Hashing.
 */
public class Chain<T, E> {
    public Node<String, Integer> head;
    private int size;

    public Chain(){
        head = null;
        size = 0;
    }

    /**
     * Inserts a new node into the HashMap if no node with the same key exists.
     *
     * @param element The pair of String and Integer, representing key and value respectively.
     * @return boolean Returns true if the node is inserted, false if a node with the same key exists.
     */
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

    /**
     * Get the data (value) of the element with respective key. If no key found, return null.
     *
     * @param key The key of the returned element.
     * @return Pair<String, Integer> Return the element corresponding to the key, null if no respective key is found.
     */
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

    /**
     * Remove the Node of the element from the list with the corresponding key.
     *
     * @param key The key of the removed element.
     * @return boolean Return true if the element is found and deleted successfully.
     */
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

