package Password;

public class SecretKey {
    private final String correctKey;
    private int counter;

    public SecretKey() {
        // for the real test, your program will not know this M O C H A
        correctKey = "ACHAOCHMOCHA";
        counter = 0;
        validateKey();
    }
    private void validateKey(){
        for(int i = 0; i < correctKey.length(); i++){
            char check = correctKey.charAt(i);
            if (check != 'M' && check != 'O' && check != 'C' && check != 'H' && check != 'A') {
                System.out.println("Unexpected tokens in KEY: " + check + " at [" + i + "].");
                System.exit(1);
            }
        }
    }

    public int guess(String guessedKey) {
        counter++;
        // validation
        if (guessedKey.length() != correctKey.length()) {
            System.out.println("Unexpected Length in KEY: " + correctKey.length());
            System.exit(0);
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
        if (matched == correctKey.length()) {
            System.out.println("Number of guesses: " + counter);
        }
        return matched;
    }

    public static void main(String[] args) { new SecretKeyGuesser().start(); }
}