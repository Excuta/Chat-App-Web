package com.piedpiper.piperchat.bean.security.authorization.userauth;

import com.piedpiper.piperchat.bean.security.authorization.token.Token;
import com.piedpiper.piperchat.data.model.user.User;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

/**
 * Created By: Yahia
 */
@Entity
public class UserAuth {
    @NonNull
    private final Token token;
    @NonNull
    private final Token refreshToken;
    @NonNull
    @OneToOne
    private final User user;

    UserAuth(@NonNull Token token, @NonNull Token refreshToken, @NonNull User user) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    @NonNull
    public Token getToken() {
        return token;
    }

    @NonNull
    public Token getRefreshToken() {
        return refreshToken;
    }

    @NonNull
    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuth userAuth = (UserAuth) o;
        return token.equals(userAuth.token) &&
            refreshToken.equals(userAuth.refreshToken) &&
            user.equals(userAuth.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, refreshToken, user);
    }
}
