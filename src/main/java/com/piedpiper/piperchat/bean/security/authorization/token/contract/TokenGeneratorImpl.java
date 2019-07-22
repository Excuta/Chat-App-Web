package com.piedpiper.piperchat.bean.security.authorization.token.contract;

import java.security.SecureRandom;

/**
 * Created By: Yahia
 */
public class TokenGeneratorImpl implements TokenGenerator {

    private SecureRandom random;

    public TokenGeneratorImpl() {
        random = new SecureRandom();
    }

    @Override
    public String generate() {
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return new String(bytes);
    }
}
