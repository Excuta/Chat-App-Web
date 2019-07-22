package com.piedpiper.piperchat.bean.security.authorization.token.contract;

/**
 * Created By: Yahia
 */
public interface TokenConfig {
    Integer getTokenDurationInMinutes();

    Integer getRefreshTokenDurationInMinutes();
}
