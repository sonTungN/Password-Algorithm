package Password;

import Password.MyADT.CustomHashMap;

public class SecretKeyGuesser {
    private final String HINT = "MOCHA";
    private final int LENGTH = 12;

    private boolean STOP;

    private CustomHashMap candidates;

    private int[] counters;
    private String token;

    public void start(){
        SecretKey key = new SecretKey();
        candidates = new CustomHashMap(20);
        counters = new int[HINT.length()];
        STOP = false;   

        init(key);
        token = String.valueOf(setup());

        int matched = 0;
        while(true){
            System.out.println("Guessing....." + token);
            matched = key.guess(token);

            if(matched == LENGTH){
                break;
            }
            candidates.put(token, matched);

            token = next();
        }
        System.out.println("I found the secret key. It is " + token);
    }

    private String next(){
        char[] tokens = token.toCharArray();
        char[] stored = new char[tokens.length];
        boolean[] used = new boolean[tokens.length];

        permute(tokens, stored, used, 0);

        STOP = false;
        return token;
    }

    private void permute(char[] tokens, char[] stored, boolean[] used, int curr_index){
        if(STOP) return;

        if(curr_index == tokens.length){
            process(String.valueOf(stored));
            return;
        }

        boolean[] duplicated = new boolean[HINT.length()];
        for(int i = 0; i < tokens.length; i++){
            if(used[i] || duplicated[valueOf(tokens[i])]) continue;

            duplicated[valueOf(tokens[i])] = true;

            stored[curr_index] = tokens[i];
            used[i] = true;

            permute(tokens, stored, used, curr_index + 1);
            used[i] = false;
        }
    }

    private void process(String stored){
        for(String key : candidates.keySet()){
            if(compare(stored, key) != candidates.get(key)) return;
        }
        STOP = true;
        token = stored;
    }

    private int compare(String s1, String s2){
        if(s1.length() != s2.length())
            throw new IllegalArgumentException("Two strings should have the same length");

        int match = 0;
        for(int i = 0; i < s1.length(); i++){
            char c = s1.charAt(i);

            if(valueOf(c) == -1)
                throw new IllegalArgumentException("Unexpected char: " + c);

            if(c == s2.charAt(i)){
                match++;
            }
        }
        return match;
    }

    private void init(SecretKey key){
        int count = 0;
        String chars = "MOCHA";
        for (int i = 0; i < HINT.length() - 1; i++) {
            if (count == LENGTH) return;
            String token = Character.toString(chars.charAt(i)).repeat(LENGTH);

            System.out.println("Guessing....." + token);
            counters[i] = key.guess(token);

            if(counters[i] == LENGTH){
                System.out.println("I found the secret key. It is " + token);
                System.exit(0);
            }

            count += counters[i];
        }
        counters[4] = LENGTH - count;
    }

    private StringBuilder setup(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < counters.length; i++){
            str.append(Character.toString(toChar(i)).repeat(counters[i]));
        }
        return str;
    }

    private int valueOf(char c) {
        return switch (c) {
            case 'M' -> 0;
            case 'O' -> 1;
            case 'C' -> 2;
            case 'H' -> 3;
            case 'A' -> 4;
            default -> -1;
        };
    }

    private char toChar(int value){
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
