package Password;

public class SecretKey {
    private final String correctKey;
    private int counter;

    public SecretKey(String secretKey) {
        correctKey = secretKey;
        counter = 0;
    }

    public void validateKey(){
        for(int i = 0; i < correctKey.length(); i++){
            char check = correctKey.charAt(i);
            if (check != 'M' && check != 'O' && check != 'C' && check != 'H' && check != 'A') {
                System.out.println("Unexpected tokens in KEY: " + check + " at [" + i + "].");
                System.exit(1);
            }
        }
    }

    public int guess(String guessedKey) {
        validateKey();

        counter++;
        // validation
        if (guessedKey.length() != correctKey.length()) {
            return -1;
        }
        int matched = 0;
        for (int i = 0; i < guessedKey.length(); i++) {
            char c = guessedKey.charAt(i);
            if (c != 'M' && c != 'O' && c != 'C' && c != 'H' && c != 'A') {
                return -1;
            }

            if (c == correctKey.charAt(i)) {
                matched++;
            }
        }
        return matched;
    }

    public static void main(String[] args) {
        int count = 0;
        int[] guesses = new int[20];
        // Random secret key to test cases


        long start = System.currentTimeMillis();
        while (count != 1_000_000) {
            // random secret key
            guesses[new SecretKeyGuesser().start(null) - 1]++; // input random key here
            count++;
        }
        long end = System.currentTimeMillis();

        System.out.println("Have tested " + count + " cases.");
        System.out.println("Execution time: " + (end - start) / 1000 + " seconds");
        for (int i = 0; i < guesses.length; i++) {
            System.out.println(i + 1 + ": " + guesses[i] + " times");
        }
        
     }
}