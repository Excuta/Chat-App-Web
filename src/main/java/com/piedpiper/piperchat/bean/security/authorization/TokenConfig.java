package com.piedpiper.piperchat.bean.security.authorization;

/**
 * Created By: Yahia
 */
public interface TokenConfig {
    Integer getTokenDurationInMinutes();

    Integer getRefreshTokenDurationInMinutes();
}
