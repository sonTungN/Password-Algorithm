package Password.MyADT;

import Password.MyADT.CustomSet.KeySet;
import Password.MyADT.CustomElementList.ElementList;
import Password.MyADT.CustomElementList.ElementPair;

public class CustomHashMap {
    public int N;
    public ElementList[] hashtable;
    KeySet keys;

    public CustomHashMap(int size){
        N = size;
        hashtable = new ElementList[size];
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

    public boolean put(ElementPair<String, Integer> element){
        int index = hashString(element.key);
        if(hashtable[index] == null){
            hashtable[index] = new ElementList();
        }
        boolean isValid = hashtable[index].insert(element);
        if(isValid){
            keys.put(element.key);
        }
        return isValid;
    }

    public boolean put(String key, Integer value){
        return put(new ElementPair<>(key, value));
    }

    public Integer get(String key){
        int index = hashString(key);
        if(hashtable[index] == null){
            return null;
        }
        ElementPair<String, Integer> element = hashtable[index].get(key);
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
