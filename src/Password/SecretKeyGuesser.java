package Password;

import Password.MyADT.CustomHashMap;

public class SecretKeyGuesser {
    final int LENGTH = 12;
    CustomHashMap candidates;
    int[] counters;
    String current;
    boolean SUCCESS;

    public void start(){
        SecretKey key = new SecretKey();
        candidates = new CustomHashMap(20);
        counters = new int[5];
        SUCCESS = false;
        setup(key);

        String token = String.valueOf(form());
        current = token;

        int matched = 0;
        while(matched != LENGTH){
            matched = key.guess(token);
            candidates.put(token, matched);

            System.out.println("Guessing....." + token);

            token = next(token);
        }
        System.out.println("I found the secret key. It is " + token);
    }

    public String next(String str){
        char[] tokens = str.toCharArray();
        char[] stored = new char[tokens.length];
        boolean[] used = new boolean[stored.length];
        permute(tokens, stored, used, 0);

        SUCCESS = false;
        return current;
    }

    public void permute(char[] tokens, char[] stored, boolean[] used, int curr_index){
        if(SUCCESS){
            return;
        }

        if(curr_index == tokens.length){
            process(String.valueOf(stored));
            return;
        }

        boolean[] duplicate = new boolean[5];
        for(int i = 0; i < tokens.length; i++){
            if(used[i] || duplicate[valueOf(tokens[i])]) continue;

            duplicate[valueOf(tokens[i])] = true;

            stored[curr_index] = tokens[i];
            used[i] = true;

            permute(tokens, stored, used, curr_index + 1);

            used[i] = false;
        }
    }

    public void process(String stored){
        for(String s : candidates.keySet()){
            if(compare(stored, s) != candidates.get(s)){
                return;
            }
        }
        SUCCESS = true;
        current = stored;
    }

    public int compare(String str, String another){
        if(str.length() != another.length()){
            return -1;
        }

        int match = 0;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if (c != 'M' && c != 'O' && c != 'C' && c != 'H' && c != 'A') {
                return -1;
            }
            if(c == another.charAt(i)){
                match++;
            }
        }
        return match;
    }

    public StringBuilder form(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < counters.length; i++){
            str.append(Character.toString(toChar(i)).repeat(counters[i]));
        }
        return str;
    }

    public void setup(SecretKey key){
        int count = 0;
        String chars = "MOCHA";
        for (int i = 0; i < 4; i++) {
            if (count == LENGTH) return;

            String token = Character.toString(chars.charAt(i)).repeat(LENGTH);
            counters[i] = key.guess(token);
            count += counters[i];

            System.out.println("Guessing....." + token);
        }
        counters[4] = LENGTH - count;
    }

    public int valueOf(char c) {
        return switch (c) {
            case 'M' -> 0;
            case 'O' -> 1;
            case 'C' -> 2;
            case 'H' -> 3;
            case 'A' -> 4;
            default -> -1;
        };
    }

    public char toChar(int value){
        return switch (value){
            case 0 -> 'M';
            case 1 -> 'O';
            case 2 -> 'C';
            case 3 -> 'H';
            case 4 -> 'A';
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }
}
