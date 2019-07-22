package com.piedpiper.piperchat.bean.security.authorization.token.contract;

/**
 * Created By: Yahia
 */
public class TokenConfigImpl implements TokenConfig {
    private final Integer tokenDurationInMinutes;
    private final Integer refreshTokenDurationInMinutes;

    public TokenConfigImpl(Integer tokenDurationInMinutes, Integer refreshTokenDurationInMinutes) {
        this.tokenDurationInMinutes = tokenDurationInMinutes;
        this.refreshTokenDurationInMinutes = refreshTokenDurationInMinutes;
    }

    @Override
    public Integer getTokenDurationInMinutes() {
        return tokenDurationInMinutes;
    }

    @Override
    public Integer getRefreshTokenDurationInMinutes() {
        return refreshTokenDurationInMinutes;
    }
}
