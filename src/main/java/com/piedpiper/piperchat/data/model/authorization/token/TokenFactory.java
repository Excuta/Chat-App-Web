package com.piedpiper.piperchat.data.model.authorization.token;

import com.piedpiper.piperchat.bean.security.authorization.TokenConfig;
import com.piedpiper.piperchat.bean.security.authorization.TokenGenerator;
import org.springframework.lang.NonNull;

/**
 * Created By: Yahia
 */
public class TokenFactory {

    private TokenGenerator tokenGenerator;
    private TokenConfig tokenConfig;
    private int conversion = 1;

    public TokenFactory(TokenGenerator tokenGenerator, TokenConfig tokenConfig) {
        this.tokenGenerator = tokenGenerator;
        this.tokenConfig = tokenConfig;
    }

    @SuppressWarnings("ConstantConditions")
    public Token createToken(@NonNull Token refreshToken) {
        long tokenDurationInSeconds = (tokenConfig.getTokenDurationInSeconds() * conversion);
        if (willExceedRefreshToken(refreshToken, tokenDurationInSeconds)) {
            tokenDurationInSeconds = refreshToken.getExpirationTimeInSeconds() - Token.getCurrentTimeInSeconds();
        }
        return new Token(tokenGenerator.generate(), tokenDurationInSeconds);
    }

    private boolean willExceedRefreshToken(Token refreshToken, long tokenDurationInSeconds) {
        return refreshToken.getExpirationTimeInSeconds() != null && refreshToken.getExpirationTimeInSeconds() <= Token.getCurrentTimeInSeconds() + tokenDurationInSeconds;
    }

    public Token createRefreshToken() {
        return new Token(tokenGenerator.generate(), (long) (tokenConfig.getRefreshTokenDurationInSeconds() * conversion));
    }
}
