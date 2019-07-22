package com.piedpiper.piperchat.bean.security.authorization.userauth;

import java.util.Objects;

/**
 * Created By: Yahia
 */
public class UserAuthResponse {
    private final String token;
    private final String refreshToken;

    private UserAuthResponse(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public UserAuthResponse from(UserAuth userAuth) {
        return new UserAuthResponse(userAuth.getToken().getValue(), userAuth.getRefreshToken().getValue());
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthResponse that = (UserAuthResponse) o;
        return token.equals(that.token) &&
            refreshToken.equals(that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, refreshToken);
    }
}
