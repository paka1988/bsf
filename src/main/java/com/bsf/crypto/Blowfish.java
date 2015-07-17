package com.bsf.crypto;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Implements methods to encrypt password.
 *
 * @author pkalashnikov
 *
 */
public class Blowfish implements Crypto {

    private static final String ALGORITHM = "Blowfish";
    private static final String ALGORITHM_MODE = "CBC";
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String CIPHER_BLOWFISH = ALGORITHM + "/" + ALGORITHM_MODE + "/PKCS5Padding";
    private static final byte[] IV = { 0, 0, 0, 0, 0, 0, 0, 0 };

    @Override
    public String encryptPassword(final String password) {

        final SecretKeySpec secretKey;
        byte[] encrypted = null;

        try {

            final Cipher cipher = Cipher.getInstance(CIPHER_BLOWFISH);

            secretKey = new SecretKeySpec(password.getBytes(UTF8), ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(IV));

            encrypted = cipher.doFinal(password.getBytes(UTF8));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(encrypted);
    }

    @Override
    public String decryptPassword(final String hash, final String key) {

        final SecretKey secretKey;
        byte[] decrypted = null;

        try {

            final Cipher cipher = Cipher.getInstance(CIPHER_BLOWFISH);

            secretKey = new SecretKeySpec(key.getBytes(UTF8), ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV));

            decrypted = cipher.doFinal(Base64.getDecoder().decode(hash));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new String(decrypted);
    }
}
