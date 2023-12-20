package Password;

public class SecretKey {
    private final String correctKey;
    private int counter;

    public SecretKey() {
        correctKey = "MMMMMMMMMMMK";
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
        if (matched == correctKey.length()) {
            System.out.println("Number of guesses: " + counter);
        }
        return matched;
    }

    public static void main(String[] args) { new SecretKeyGuesser().start(); }
}