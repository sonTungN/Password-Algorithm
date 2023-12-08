package Password.MyADT;

import Password.MyADT.CustomSet.KeySet;
import Password.MyADT.CustomElementList.ElementList;
import Password.MyADT.CustomElementList.ElementPair;

public class MyHashMap {
    public int N;
    public ElementList<String, Integer> hashtable;
    KeySet keys;

    public MyHashMap(int size){
        N = size;
        hashtable = new ElementList<>();
        keys = new KeySet(size);
    }

//    private int hashCharacter(char c){
//
//    }
//
//    private int hashString(String str){
//
//    }

    public String[] keySet(){
        return keys.getKeys();
    }

    public boolean put(ElementPair<String, Integer> p){
//        int index = hashString(p.key);
        return hashtable.insert(p);
    }

    public boolean put(String key, Integer value){
        return put(new ElementPair<>(key, value));
    }

    public int get(String key){
//        int index = hashString(key);
        ElementPair<String, Integer> pair = hashtable.get(key);
        return (pair != null) ? pair.value : -1;
    }

    public boolean remove(String key){
        return hashtable.remove(key);
    }
}
