package com.piedpiper.piperchat.data.model.authorization.userauth;

import com.piedpiper.piperchat.data.model.authorization.token.Token;
import com.piedpiper.piperchat.data.model.authorization.token.TokenFactory;
import com.piedpiper.piperchat.data.model.user.User;

/**
 * Created By: Yahia
 */
public class UserAuthFactory {

    private TokenFactory tokenFactory;

    public UserAuthFactory(TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    public UserAuth create(User user) {
        Token refreshToken = tokenFactory.createRefreshToken();
        return new UserAuth(tokenFactory.createToken(refreshToken), refreshToken, user);
    }
}
