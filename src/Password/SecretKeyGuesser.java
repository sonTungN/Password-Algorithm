package Password;

import Password.MyADT.CustomHashMap;

public class SecretKeyGuesser {
    // Initialize HINT and LENGTH of the key
    final String HINT = "MOCHA";
    final int LENGTH = 12;

    // STOP the permute() method when 1 token process()
    boolean STOP;

    // Initialize the CustomHashMap to store value
    CustomHashMap candidates;

    /*
    Initialize the counters to count the matched in init()
    As we will compare 5 string: M..M, O..O, C..C, H..H, A..A (each length = 12) with the correctKey to find the matches
    --> the position in the counters is M(0), O(1), C(2), H(3), A(4)

    The counter will then be used to create a setup() token for the first run
    Ex:
        KEY: MOCHAMOCHA
        --> The setup() = MMOOCCHHAA
     */
    int[] counters;

    // Token using throughout the guessing process
    String token;

    /**
     * Start with this method.
     */
    public void start(){
        SecretKey key = new SecretKey();
        // Initialize the size of the HashMap
        candidates = new CustomHashMap(20);

        // As the HINT is MOCHA --> HINT.length() = 5
        counters = new int[HINT.length()];

        // Set the STOP to false because nothing happens yet
        STOP = false;

        // Find values to initialize the first token
        init(key);

        // Set up the first tokens using counters[5]
        token = String.valueOf(setup());

        // Count the matched if matched == LENGTH (all matched with CORRECT) --> GET THE ANSWER
        // Else STORED it as the Value of the Element with the token is its Key
        int matched = 0;
        while(true){
            System.out.println("Guessing....." + token);

            matched = key.guess(token);

            // GET THE ANSWER
            if(matched == LENGTH){
                break;
            }
            /*
            The functionality of HashMap is now being used
            Stored the tokens into the map with (Key, Value) is the (token, matched)
             */
            candidates.put(token, matched);
            System.out.println("ADD " + token + ", " + matched);

            // After the first guess(), go for the next token using the PERMUTATION idea
            token = next();
        }
        System.out.println("I found the secret key. It is " + token);
    }

    /**
     * The method to generate the next token using PERMUTATION generator permute(...)
     *
     * @return String (The next token to guess)
     */
    public String next(){
        char[] tokens = token.toCharArray();
        char[] stored = new char[tokens.length];
        boolean[] used = new boolean[tokens.length];
        /*
        Because the PERMUTATION is the UNIQUE problem
        (Link: https://www.youtube.com/watch?v=YW5F0WqBBWY&pp=ygUSdW5pcXVlIHBlcm11dGF0aW9u)
        --> Initialize the required arrays with 2 char[] and 1 boolean[]

        (There is 1 more boolean[] using later)
         */
        permute(tokens, stored, used, 0);

        STOP = false;
        return token;
    }

    /**
     * Method based on the idea of PERMUTATION to generate the token for the next guess.
     *
     * @param tokens Array of character from the current token
     * @param stored Array of character to store the permuted value
     * @param used   Array of boolean to LOCKED and UNLOCKED the characters
     * @param curr_index Start from 0 and End with `tokens length`
     */
    public void permute(char[] tokens, char[] stored, boolean[] used, int curr_index){
        // If 1 token is generated and process() --> Stop the recursions
        if(STOP) return;

        if(curr_index == tokens.length){
            // When 1 token is generated --> process it
            process(String.valueOf(stored));
            return;
        }

        /*
            Because it is the UNIQUE problem, duplicated is initialized to solve this problem
            Idea: Mark the used value as true
            --> So that when the permutation goes on, if the case has been generated --> CONTINUE
         */
        boolean[] duplicated = new boolean[HINT.length()];

        // Basic idea of the backtracking in PERMUTATION
        for(int i = 0; i < tokens.length; i++){
            if(used[i] || duplicated[valueOf(tokens[i])]) continue;

            duplicated[valueOf(tokens[i])] = true;

            stored[curr_index] = tokens[i];
            // LOCKED
            used[i] = true;

            permute(tokens, stored, used, curr_index + 1);
            // UNLOCKED
            used[i] = false;
        }
    }

    /**
     * In summary, the process() function is indeed concerned with finding a string (permutation of the current token)
     * that has the same number of character matches in the same positions as the correct secret key.
     * (More details will be covered in the report)
     *
     * @param stored A permutation of the current token to find the next potential token.
     */
    public void process(String stored){
        for(String key : candidates.keySet()){
            if(compare(stored, key) != candidates.get(key)) return;
        }
        // Set STOP to stop the permutation and get the stored as new potential token
        STOP = true;
        token = stored;
    }

    /**
     * Method to compare 2 strings and get the matching element. (like the guess()).
     *
     * @param s1 The first string
     * @param s2 The second string
     * @return int Return the number of matching characters between 2 strings.
     */
    public int compare(String s1, String s2){
        if(s1.length() != s2.length()) return -1;

        int match = 0;
        for(int i = 0; i < s1.length(); i++){
            char c = s1.charAt(i);
            if (c != 'M' && c != 'O' && c != 'C' && c != 'H' && c != 'A') return -1;

            if(c == s2.charAt(i)){
                match++;
            }
        }
        return match;
    }

    /**
     * Method to get the values of the counters[5] for the first token generation in setup().
     *
     * @param key
     */
    public void init(SecretKey key){
        int count = 0;
        String chars = "MOCHA";
        for (int i = 0; i < HINT.length() - 1; i++) {
            if (count == LENGTH) return;

            // Compare the M.., O.., C.., H.., A.. to the CORRECT KEY and store in counters[]
            String token = Character.toString(chars.charAt(i)).repeat(LENGTH);

            counters[i] = key.guess(token);
            System.out.println("Guessing....." + token);

            count += counters[i];
        }
        // Instead of using 5 guess() for the init(), the count variable is created to reduce to 4
        counters[4] = LENGTH - count;
    }

    public StringBuilder setup(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < counters.length; i++){
            str.append(Character.toString(toChar(i)).repeat(counters[i]));
        }
        return str;
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
