package Password.MyADT;

import Password.MyADT.Key.KeySet;
import Password.MyADT.Element.Chain;
import Password.MyADT.Element.Pair;

public class CustomHashMap {
    public int N;
    public Chain[] hashtable;
    KeySet keys;

    public CustomHashMap(int size){
        N = size;
        hashtable = new Chain[size];
        keys = new KeySet(size);
    }

    private int hashCharacter(char c){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return str.indexOf(c);
    }

    private int hashString(String str){
        int sum = 0;
        for(Character c : str.toCharArray()){
            sum += hashCharacter(c);
        }
        return sum % N;
    }

    public String[] keySet(){
        return keys.getKeys();
    }

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

    public boolean put(String key, Integer value){
        return put(new Pair<>(key, value));
    }

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
}
