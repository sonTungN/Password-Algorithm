package Password.MyADT.myset;

public class MySet {
    public ListString[] hashtable;
    public int size;
    public int N;

    public MySet(int size){
        N = size;
        this.size = size;
        hashtable = new ListString[size];
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

    public boolean put(String str){
        int index = hashString(str);
        if(hashtable[index] == null){
            hashtable[index] = new ListString();
        }
        return hashtable[index].insert(str);
    }

    public boolean remove(String str){
        int index = hashString(str);
        if(hashtable[index] == null){
            return false;
        }
        return hashtable[index].remove(str);
    }

    public boolean contains(String str){
        int index = hashString(str);
        if(hashtable[index] == null){
            return false;
        }
        return hashtable[index].contains(str);
    }

    public String[] getKeys(){
        int totalKeys = 0;
        for(ListString list : hashtable){
            if(list != null){
                totalKeys += list.size;
            }
        }
        String[] keys = new String[totalKeys];
        int index = 0;
        for(ListString list : hashtable){
            if(list != null){
                NodeString tmp = list.head;
                while(tmp != null){
                    keys[index++] = tmp.data;
                    tmp = tmp.next;
                }
            }
        }
        return keys;
    }
}

