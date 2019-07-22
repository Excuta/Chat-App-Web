package com.piedpiper.piperchat.bean.security.authorization;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created By: Yahia
 */
public class TokenGeneratorImpl implements TokenGenerator {

    private SecureRandom random;
    private Base64.Encoder base64Encoder;

    public TokenGeneratorImpl() {
        random = new SecureRandom();
        base64Encoder = Base64.getUrlEncoder();
    }

    @Override
    public String generate() {
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return base64Encoder.encodeToString(bytes);
    }
}
