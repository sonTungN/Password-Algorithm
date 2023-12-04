package Password.MyADT;

import Password.MyADT.myset.MySet;
import Password.MyADT.pair.ListPair;
import Password.MyADT.pair.Pair;

public class MyHashMap {
    public int N;
    public ListPair<String, Integer> hashtable;
    MySet keys;

    public MyHashMap(int size){
        N = size;
        hashtable = new ListPair<>();
        keys = new MySet(size);
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

    public boolean put(Pair<String, Integer> p){
//        int index = hashString(p.key);
        return hashtable.insert(p);
    }

    public boolean put(String key, Integer value){
        return put(new Pair<>(key, value));
    }

    public int get(String key){
//        int index = hashString(key);
        Pair<String, Integer> pair = hashtable.get(key);
        return (pair != null) ? pair.value : -1;
    }

    public boolean remove(String key){
        return hashtable.remove(key);
    }
}
