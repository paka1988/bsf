package com.bsf.crypto;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for blowfish crypto functions.
 * 
 * @author pkalashnikov
 *
 */
public class CryptoTest {

	private Crypto blowfish = new Blowfish();
	private String testPassword = "Aa12345678#?";
	
	@Test
	public final void testEncryptPassword() {
		
		String encryptedPassword = blowfish.encryptPassword(testPassword);
		System.out.println(encryptedPassword);
		
		assertTrue("Password should be enrypted.", !testPassword.equals(encryptedPassword));
	}
	
	@Test
	public final void testDecryptPassword() {
		
		String decryptedPassword = blowfish.decryptPassword(blowfish.encryptPassword(testPassword), testPassword);
		assertEquals("Problem with EncryptDecrypt.", testPassword, decryptedPassword);
	}
}
