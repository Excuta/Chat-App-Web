package com.piedpiper.piperchat.bean.security;

import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.security.SecureRandom;

/**
 * Created By: Yahia
 */
public class BCryptorImpl implements BCryptor, Serializable {

    public BCryptorImpl() {
    }

    @Override
    public String encrypt(String plain) {
        String salt = BCrypt.gensalt();
        return encrypt(plain, salt);
    }

    @Override
    public String encrypt(String plain, SecureRandom secureRandom) {
        String salt = BCrypt.gensalt(10, secureRandom);
        return encrypt(plain, salt);
    }

    String testEncrypt(String plain, String salt) {
        return encrypt(plain, salt);
    }

    private String encrypt(String plain, String salt) {
        return BCrypt.hashpw(plain, salt);
    }

    @Override
    public boolean matches(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }
}
