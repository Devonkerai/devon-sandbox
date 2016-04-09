import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BruteForcePasswordFinder {

    private static String password = "7yB5";
    private static String encryptedPassword;
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static List<List<String>> prefixBySize = new ArrayList<>(0);

    public static void main(String[] args) throws Exception {
        getPassword();
        encryptedPassword = encryptPassword(password);
        System.out.println("Your password is: " + password + ". It's hash is: " + encryptedPassword);
        long startTime = System.currentTimeMillis();
        decryptPassword();
        long stopTime = System.currentTimeMillis();
        long timeTaken = stopTime - startTime;
        System.out.println("It took: " + timeTaken + "ms.");
    }

    private static void getPassword() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.println("Please type in your password. It must be alphanumeric: ");
        input = bufferedReader.readLine();
        // Validate input is alphanumeric.
        password = input;
    }

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
            String newprefix = prefix;
            for (char c : alphabet) {
                String tempPass = newprefix += c;
                if (guessPassword(tempPass)) {
                    return tempPass;
                }
                failedAttempts.add(tempPass);
                newprefix = prefix;
            }
        }
        prefixBySize.add(0, failedAttempts);
        return null;
    }

    private static boolean guessPassword(String attempt) throws NoSuchAlgorithmException {
        String encryptedAttempt = encryptPassword(attempt);
//        System.out.println("Encoded password of: " + attempt + " is: " + encryptedAttempt);
        if (validateHash(encryptedAttempt)) {
            System.out.println("You have found the password! It is: " + attempt + ". It has the hash: " + encryptedAttempt);
            return true;
        }
        return false;
    }

    private static boolean validateHash(String encryptedAttempt) {
        return encryptedAttempt.equals(encryptedPassword);
    }
}