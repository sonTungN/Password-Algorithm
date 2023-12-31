package Password.MyADT.Key;

public class KeySet {
    public KeyList[] hashtable;
    public int N;

    public KeySet(int size){
        N = size;
        hashtable = new KeyList[size];
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

    public boolean put(String key){
        int index = hashString(key);
        if(hashtable[index] == null){
            hashtable[index] = new KeyList();
        }
        return hashtable[index].insert(key);
    }

    public boolean remove(String key){
        int index = hashString(key);
        if(hashtable[index] == null){
            return false;
        }
        return hashtable[index].remove(key);
    }

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

