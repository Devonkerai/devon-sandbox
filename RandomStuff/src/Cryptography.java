import java.security.MessageDigest;

public class Cryptography {

    public static void main(String[] args) throws Exception {

        String password = "helloworld";

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());

        byte byteData[] = messageDigest.digest();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            stringBuilder.append(Integer.toString(byteData[i] & 0xff + 0x100, 16).substring(1));
        }

        System.out.println("Hex format of password: " + stringBuilder.toString());
    }
}
