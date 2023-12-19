package Password.MyADT;

import Password.MyADT.Key.KeySet;
import Password.MyADT.Element.Chain;
import Password.MyADT.Element.Pair;

/**
 * Create CustomHashMap to store the guessing string (permutations) of the SecretKeyGuesser as Key and the matched value
 * with the correctKey as the element Value.
 */
public class CustomHashMap {
    public int N;
    public Chain[] hashtable;
    KeySet keys;

    /**
     * Initialize the size of the hashmap with the given size.
     * @param size The size of the hashmap.
     */
    public CustomHashMap(int size){
        N = size;
        hashtable = new Chain[size];
        keys = new KeySet(size);
    }

    /**
     * Hash the char into int.
     *
     * @param c The character wanted to hash.
     * @return The hash value of the char.
     */
    private int hashCharacter(char c){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return str.indexOf(c);
    }

    /**
     * Hash the whole string to store into the Hashmap.
     *
     * @param str The string wanted to hash.
     * @return The hash value of the string.
     */
    private int hashString(String str){
        int sum = 0;
        for(Character c : str.toCharArray()){
            sum += hashCharacter(c);
        }
        return sum % N;
    }

    /**
     * Get all the element's keys and stored into a String array.
     * @return String[]
     */
    public String[] keySet(){
        return keys.getKeys();
    }

    /**
     * Add a new element into the HashMap.
     *
     * @param element The element with the correspond Key and Value.
     * @return boolean Return true if no duplicated is found.
     */
    public boolean put(Pair<String, Integer> element){
        int index = hashString(element.key);
        if(hashtable[index] == null){
            hashtable[index] = new Chain();
        }
        boolean isValid = hashtable[index].insert(element);
        if(isValid){
            keys.put(element.key);
        }
        return isValid;
    }

    /**
     * Add a pair of String key and Int Value as a new element.
     *
     * @param key The Key of the element
     * @param value The Value of the Element
     * @return boolean Return true if no duplicate is found.
     */
    public boolean put(String key, Integer value){
        return put(new Pair<>(key, value));
    }

    /**
     * Get the value of the element by searching with its Key.
     *
     * @param key The Key of the searched element.
     * @return Integer The Value of the Element with correspond Key.
     */
    public Integer get(String key){
        int index = hashString(key);
        if(hashtable[index] == null){
            return null;
        }
        Pair<String, Integer> element = hashtable[index].get(key);
        if(element == null){
            return null;
        }
        return element.value;
    }

    public boolean remove(String key){
        int index = hashString(key);
        if(hashtable[index] == null){
            return false;
        }
        boolean isValid = hashtable[index].remove(key);
        if(isValid){
            keys.remove(key);
        }
        return isValid;
    }
}
