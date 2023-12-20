package Password.MyADT.Key;

/**
 * Create KeySet class to store the key value of each element.
 */
public class KeySet {
    public KeyList[] hashtable;
    public int N;

    /**
     * Initialize the KeySet with given size.
     *
     * @param size The size of the KeySet.
     */
    public KeySet(int size){
        N = size;
        hashtable = new KeyList[size];
    }

    /**
     * Hash method applied for each char.
     *
     * @return int Return the hash value of the char.
     */
    private int hashCharacter(char c){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return str.indexOf(c);
    }

    /**
     * Hash method using for string.
     *
     * @return int Return the hash value of whole string.
     */
    private int hashString(String str){
        int sum = 0;
        for(Character c : str.toCharArray()){
            sum += hashCharacter(c);
        }
        return sum % N;
    }

    /**
     * Add a new key into the KeySet.
     *
     * @param key The key wanted to insert.
     * @return boolean Return true of insert successfully, false if failed.
     */
    public boolean put(String key){
        int index = hashString(key);
        if(hashtable[index] == null){
            hashtable[index] = new KeyList();
        }
        return hashtable[index].insert(key);
    }

    /**
     * Remove a key from the KeySet.
     *
     * @param key The key wanted to remove from Set.
     * @return boolean Return true if removed successfully, false if failed.
     */
    public boolean remove(String key){
        int index = hashString(key);
        if(hashtable[index] == null){
            return false;
        }
        return hashtable[index].remove(key);
    }

    /**
     * Get all the keys in the KeySet and stored into a String array.
     *
     * @return String[] Return the String array of all keys in KeySet.
     */
    public String[] getKeys(){
        int totalKeys = 0;
        for(KeyList list : hashtable){
            if(list != null){
                totalKeys += list.size;
            }
        }
        String[] keys = new String[totalKeys];
        int index = 0;
        for(KeyList list : hashtable){
            if(list != null){
                KeyNode tmp = list.head;
                while(tmp != null){
                    keys[index++] = tmp.data;
                    tmp = tmp.next;
                }
            }
        }
        return keys;
    }
}

