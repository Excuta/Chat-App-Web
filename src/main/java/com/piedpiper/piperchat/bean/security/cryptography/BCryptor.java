package com.piedpiper.piperchat.bean.security.cryptography;

import java.security.SecureRandom;

/**
 * Created By: Yahia
 */
public interface BCryptor extends Encryptor {
    String encrypt(String plain, SecureRandom secureRandom);

    boolean matches(String plain, String hashed);
}
