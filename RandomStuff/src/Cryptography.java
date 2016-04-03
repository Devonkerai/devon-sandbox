import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cryptography {

    private static String password = "AAB";
    private static String encryptedPassword;
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static List<List<String>> prefixBySize = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        encryptedPassword = encryptPassword(password);
        System.out.println("Your password is: " + password + ". It's hash is: " + encryptedPassword);
        decryptPassword();
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
        int prefixCounter = 0;
        while (pass == null) {
            final List<String> prefixes = prefixBySize.get(prefixCounter);
            pass = attemptDecrypt(prefixes);
            prefixCounter++;
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
        prefixBySize.add(failedAttempts);
        return null;
    }

    private static boolean guessPassword(String attempt) throws NoSuchAlgorithmException {
        String encryptedPasswordAttempt = encryptPassword(attempt);
//        System.out.println("Encoded password of: " + attempt + " is: " + encryptedPasswordAttempt);
        if (validateHash(encryptedPasswordAttempt)) {
            System.out.println("You have found the password! It is: " + attempt + ". It has the hash: " + encryptedPasswordAttempt);
            return true;
        }
        return false;
    }

    private static boolean validateHash(String encryptedPasswordAttempt) {

        return encryptedPasswordAttempt.equals(encryptedPassword);
    }
}