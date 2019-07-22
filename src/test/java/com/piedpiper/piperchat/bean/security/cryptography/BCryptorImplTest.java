package com.piedpiper.piperchat.bean.security.cryptography;

import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.Assert.*;

/**
 * Created By: Yahia
 */
public class BCryptorImplTest {
    private String salt;
    private BCryptorImpl bCryptor;
    private String password;

    @Before
    public void setUp() {
        bCryptor = new BCryptorImpl();
        password = "123abc24fw";
        salt = BCrypt.gensalt();
    }

    @Test
    public void test_BCrypt_implementation() {
        String expected = BCrypt.hashpw(password, salt);
        String actual = bCryptor.testEncrypt(password, salt);
        assertEquals(expected, actual);
    }

    @Test
    public void test_matches_with_same_password() {
        String encrypt = bCryptor.encrypt(password);
        boolean matches = bCryptor.matches(password, encrypt);
        assertTrue(matches);
    }

    @Test
    public void test_matches_with_different_password() {
        String encrypt = bCryptor.encrypt(password);
        boolean matches = bCryptor.matches("not_same-password", encrypt);
        assertFalse(matches);
    }
}