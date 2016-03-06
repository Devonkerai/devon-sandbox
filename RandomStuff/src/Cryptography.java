import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography {

    private static String password = "Bn";
    private static char[] guess = new char[password.length()];
    private static StringBuilder encryptedPassword = new StringBuilder();
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) throws Exception {
        encryptedPassword = encryptPassword(password);
        System.out.println("Password hash of: " + password + " is: " + encryptedPassword);
        decryptPassword();
    }

    private static StringBuilder encryptPassword(String password) throws NoSuchAlgorithmException {
        StringBuilder hash = new StringBuilder();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        byte byteData[] = messageDigest.digest();
        for (byte aByteData : byteData) {
            hash.append(Integer.toString(aByteData & 0xff + 0x100, 16).substring(1));
        }
        return hash;
    }

    private static void decryptPassword() throws NoSuchAlgorithmException {
        for (char firstLetter : alphabet) {
            for (char secondLetter : alphabet) {
                guess[0] = firstLetter;
                guess[1] = secondLetter;
                guessPassword();
            }
        }
    }

    private static void guessPassword() throws NoSuchAlgorithmException {
        String attempt = printGuess();
        StringBuilder encryptedPasswordAttempt = encryptPassword(attempt);
//                    System.out.println("Attempt is: " + attempt);
//                    System.out.println("Encoded password of: " + attempt + " is: " + encryptedPasswordAttempt);
        validateHash(attempt, encryptedPasswordAttempt);
    }

    private static String printGuess() {
        String guessAsString = "";
        for (char c : guess) {
            guessAsString += c;
        }
        return guessAsString;
    }

    private static void validateHash(String attempt, StringBuilder encryptedPasswordAttempt) {
        if (encryptedPasswordAttempt.toString().equals(encryptedPassword.toString())) {
            System.out.println("You have found the password! It is: " + attempt);
            System.exit(0);
        }
    }
}
