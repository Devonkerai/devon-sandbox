import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BruteForcePasswordFinder {

    private static String password;
    private static String encryptedPassword;
    private static final char[] validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static final List<List<String>> prefixBySize = new ArrayList<>(0);

    public static void main(String[] args) throws Exception {
        getPassword();
        encryptedPassword = encryptPassword(password);
        System.out.println("It has the hash: " + encryptedPassword);
        long startTime = System.currentTimeMillis();
        decryptPassword();
        long stopTime = System.currentTimeMillis();
        long timeTaken = stopTime - startTime;
        System.out.println("It took: " + timeTaken + "ms.");
    }

    // Get password via Stdin.
    private static void getPassword() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please type in your password. It must be alphanumeric: ");
        String inputPassword = bufferedReader.readLine();
        validateInputPassword(inputPassword);
    }

    // Validate entered password that it contains only valid characters.
    private static void validateInputPassword(String enteredPassword) throws IOException {
        final int lengthOfInput = enteredPassword.length();
        final char[] inputArray = enteredPassword.toCharArray();
        int numberofValidCharsInEnteredPassword = 0;

        if (lengthOfInput > 0) {
            for (char c : inputArray) {
                for (char alpha : validChars) {
                    if (c == alpha) {
                        numberofValidCharsInEnteredPassword++;
                    }
                }
            }
            if (numberofValidCharsInEnteredPassword == lengthOfInput) {
                System.out.println("Your password is: " + enteredPassword);
                password = enteredPassword;
            } else {
                System.out.println("Your password is not valid! Please try again.");
                getPassword();
            }
        } else {
            System.out.println("Your password is not valid! Please try again.");
            getPassword();
        }
    }

    // Encrypt password using SHA-256.
    private static String encryptPassword(String password) throws NoSuchAlgorithmException {
        StringBuilder hash = new StringBuilder();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        byte byteData[] = messageDigest.digest();
        for (byte aByte : byteData) {
            hash.append(Integer.toString(aByte & 0xff + 0x100, 16).substring(1));
        }
        return hash.toString();
    }

    private static void decryptPassword() throws NoSuchAlgorithmException {
        prefixBySize.add(Collections.singletonList(""));

        String pass = null;
        while (pass == null) {
            final List<String> prefixes = prefixBySize.get(0);
            pass = attemptDecrypt(prefixes);
        }
    }

    private static String attemptDecrypt(List<String> prefixes) throws NoSuchAlgorithmException {
        List<String> failedAttempts = new ArrayList<>();

        for (String prefix : prefixes) {
            for (char c : validChars) {
                String tempPass = prefix + c;
                if (guessPassword(tempPass)) {
                    return tempPass;
                }
                failedAttempts.add(tempPass);
            }
        }
        prefixBySize.add(0, failedAttempts);
        return null;
    }

    private static boolean guessPassword(String attempt) throws NoSuchAlgorithmException {
        String encryptedAttempt = encryptPassword(attempt);
//        System.out.println("Encoded password of: " + attempt + " is: " + encryptedAttempt);
        if (validateHash(encryptedAttempt)) {
            System.out.println("You have found the password! It is: " + attempt);
            return true;
        }
        return false;
    }

    private static boolean validateHash(String encryptedAttempt) {
        return encryptedAttempt.equals(encryptedPassword);
    }
}