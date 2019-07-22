package com.piedpiper.piperchat.data.requestbody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created By: Yahia
 */
public class TokenRequest {
    @NotNull
    @NotBlank
    private String token;

    public TokenRequest(@NotNull @NotBlank String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
