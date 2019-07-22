package com.piedpiper.piperchat.bean.security.authorization;

/**
 * Created By: Yahia
 */
public class TokenConfigImpl implements TokenConfig {
    private final Integer tokenDurationInSeconds;
    private final Integer refreshTokenDurationInSeconds;

    public TokenConfigImpl(Integer tokenDurationInSeconds, Integer refreshTokenDurationInSeconds) {
        this.tokenDurationInSeconds = tokenDurationInSeconds;
        this.refreshTokenDurationInSeconds = refreshTokenDurationInSeconds;
    }

    @Override
    public Integer getTokenDurationInSeconds() {
        return tokenDurationInSeconds;
    }

    @Override
    public Integer getRefreshTokenDurationInSeconds() {
        return refreshTokenDurationInSeconds;
    }
}
