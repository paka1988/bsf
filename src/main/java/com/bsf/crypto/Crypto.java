package com.bsf.crypto;

/**
 * Interface for encrypt and decrypt data.
 *
 * @author pkalashnikov
 *
 */
public interface Crypto {

    /**
     *
     * @param password
     *            password value.
     * @return encrypted password value.
     */
    String encryptPassword(final String password);

    /**
     *
     * @param hash
     *            encrypted value
     * @param key
     *            specific key
     * @return decrypted password or null.
     */
    String decryptPassword(final String hash, final String key);
}
