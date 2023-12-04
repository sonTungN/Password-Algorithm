package Password;

import Password.MyADT.MyHashMap;

import java.util.HashMap;

public class guess {
    public HashMap<String, Integer> candidates;
    public boolean isFound;
    public int[] nums;
    public String chosen;

    public void start(){
        SecretKey key = new SecretKey();
        isFound = false;
        candidates = new HashMap<>(16);
        nums = new int[5];
        chosen = "";
        setup(key);
        String str = form();
        chosen = str;

        int result = 0;
        while(result != 12){
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
        String str = "MMMMMMMMMMMM";
        nums[0] = key.guess(str);
        str = "OOOOOOOOOOOO";
        nums[1] = key.guess(str);
        str = "CCCCCCCCCCCC";
        nums[2] = key.guess(str);
        str = "HHHHHHHHHHHH";
        nums[3] = key.guess(str);
        nums[4] = 12 - nums[0] - nums[1] - nums[2] - nums[3];
    }

    public int order(char c){
        if(c == 'M'){
            return 0;
        } else if (c == 'O'){
            return 1;
        } else if (c == 'C'){
            return 2;
        } else if (c == 'H'){
            return 3;
        }
        return 4;
    }
}
