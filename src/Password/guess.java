package Password;

import Password.MyADT.CustomHashMap;
// import java.util.HashMap;

public class guess {
    final static int LENGTH = 12;
    CustomHashMap candidates = new CustomHashMap(LENGTH);
    static boolean isFound = false;
    static int[] nums = new int[5];
    public String chosen;

    public void start(){
        SecretKey key = new SecretKey();
        setup(key);
        String str = form();

        int result = 0;
        while(result != LENGTH){
            System.out.println("Guessing... " + str);
            result = key.guess(str);
            candidates.put(str, result);
            str = next(str);
        }
        System.out.println("I found the secret key. It is " + str);
    }

    public String next(String current){
        char[] c = current.toCharArray();
        char[] tmp = new char[c.length];
        boolean[] taken = new boolean[c.length];
        permutated(c, taken, tmp, 0);
        isFound = false;
        return chosen;
    }

    public void process(String candidate){
        for(String s : candidates.keySet()){
            if(isMatched(s, candidate) != candidates.get(s)){
                return;
            }
        }
        chosen = candidate;
        isFound = true;
    }

    public String form(){
        String result = "";
        result += "M".repeat(nums[0]);
        result += "O".repeat(nums[1]);
        result += "C".repeat(nums[2]);
        result += "H".repeat(nums[3]);
        result += "A".repeat(nums[4]);
        return result;
    }

    public void permutated(char[] input, boolean[] taken, char[] current, int index){
        if(isFound){
            return;
        }

        if(index == input.length){
            process(String.valueOf(current));
            return;
        }

        boolean[] duplicate = new boolean[5];
        for(int i = 0; i < input.length; i++){
//            if (taken[i] || (i > 0 && input[i] == input[i - 1] && !taken[i - 1])) {
//                continue;
//            }
            if(taken[i]){
                continue;
            }
            if(duplicate[order(input[i])]){
                continue;
            }
            duplicate[order(input[i])] = true;
            current[index] = input[i];
            taken[i] = true;

            permutated(input, taken, current, index + 1);
            taken[i] = false;
        }
    }

    public int isMatched(String str, String another){
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

    public void setup(SecretKey key){
        int count = 0;
        String chars = "MOCHA";
        for (int i = 0; i < 4; ++i) { // loop through n - 1 times
            String str = Character.toString(chars.charAt(i)).repeat(LENGTH);
            nums[i] = key.guess(str);
            System.out.println("Guessing... " + str);
            count += nums[i];
            if (count == LENGTH) return; 
        }
        nums[4] = LENGTH - count;
    }

    public int order(char c){
        switch (c) {
            case 'M': return 0;
            case 'O': return 1;
            case 'C': return 2;
            case 'H': return 3;
            default: return 4;
        }
    }
}
