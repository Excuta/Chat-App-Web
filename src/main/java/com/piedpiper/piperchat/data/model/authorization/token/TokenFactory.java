package com.piedpiper.piperchat.data.model.authorization.token;

import com.piedpiper.piperchat.bean.security.authorization.TokenConfig;
import com.piedpiper.piperchat.bean.security.authorization.TokenGenerator;

/**
 * Created By: Yahia
 */
public class TokenFactory {

    private TokenGenerator tokenGenerator;
    private TokenConfig tokenConfig;
    private int minuteToSecond = 60;

    public TokenFactory(TokenGenerator tokenGenerator, TokenConfig tokenConfig) {
        this.tokenGenerator = tokenGenerator;
        this.tokenConfig = tokenConfig;
    }

    public Token createToken() {
        return new Token(tokenGenerator.generate(), (long) (tokenConfig.getTokenDurationInMinutes() * minuteToSecond));
    }

    public Token createRefreshToken() {
        return new Token(tokenGenerator.generate(), (long) (tokenConfig.getRefreshTokenDurationInMinutes() * minuteToSecond));
    }
}
