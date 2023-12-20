package util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public final class PasswordUtil {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final String SALT_HEX = "6b7c38459065ddfbbd59096c9a4cd4d0";

    private PasswordUtil() {
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    private static byte[] hexToBytes() {
        byte[] result = new byte[PasswordUtil.SALT_HEX.length() / 2];
        for (int i = 0; i < PasswordUtil.SALT_HEX.length(); i += 2) {
            result[i / 2] = (byte) Integer.parseInt(PasswordUtil.SALT_HEX.substring(i, i + 2), 16);
        }
        return result;
    }

    public static String hashPassword(String plainTextPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = hexToBytes();
        KeySpec spec = new PBEKeySpec(plainTextPassword.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().withoutPadding().encodeToString(hash);
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return PasswordUtil.hashPassword(plainTextPassword).equals(hashedPassword);
    }
}
