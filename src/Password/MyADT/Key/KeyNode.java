package Password.MyADT.Key;

/**
 * Create KeyNode class to handle the KeySet of the HashMap.
 */
public class KeyNode {
    public String data;
    public KeyNode next;

    public KeyNode(String str){
        data = str;
        next = null;
    }
}
