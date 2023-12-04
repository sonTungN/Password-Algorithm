package Password;

import Password.MyADT.MyHashMap;

public class guesser {
    public MyHashMap candidates;
    public boolean isFound;
    public int[] nums;
    public String chosen;
    public char mid;

    public void start(){
        SecretKey key = new SecretKey();
        isFound = false;
        candidates = new MyHashMap(20);
        nums = new int[4];
        chosen = "";
        setup(key);
        String str = form();
        chosen = str;

        int count = 0;
        int result = 0;
        while(result != 16){
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
        permutate(c, taken, tmp, 0);
        isFound = false;
        return chosen;
    }

    public String form(){
        String result = "";
        result += "R".repeat(nums[0]);
        result += "M".repeat(nums[1]);
        result += "I".repeat(nums[2]);
        result += "T".repeat(nums[3]);
        return result;
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

    public void permutate(char[] input, boolean[] taken, char[] current, int index){
        if(isFound){
            return;
        }

        if(index == input.length){
            process(String.valueOf(current));
            return;
        }

        boolean[] duplicate = new boolean[4];
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

            permutate(input, taken, current, index + 1);
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
            if(c != 'R' && c != 'M' && c != 'I' && c != 'T'){
                return -1;
            }
            if(c == another.charAt(i)){
                match++;
            }
        }
        return match;
    }

    public void setup(SecretKey key){
        String str = "RRRRRRRRRRRRRRRR";
        nums[0] = key.guess(str);
        str = "MMMMMMMMMMMMMMMM";
        nums[1] = key.guess(str);
        str = "IIIIIIIIIIIIIIII";
        nums[2] = key.guess(str);
        nums[3] = 16 - nums[0] - nums[1] - nums[2];
    }

    public int order(char c){
        if(c == 'R'){
            return 0;
        } else if (c == 'M'){
            return 1;
        } else if (c == 'I'){
            return 2;
        }
        return 3;
    }
}
