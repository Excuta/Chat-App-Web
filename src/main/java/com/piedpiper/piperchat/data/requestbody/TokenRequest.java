package com.piedpiper.piperchat.data.requestbody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created By: Yahia
 */
public class TokenRequest {
    @NotNull
    @NotBlank
    private String refreshToken;

    public TokenRequest() {
    }

    public TokenRequest(@NotNull @NotBlank String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
