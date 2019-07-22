package com.piedpiper.piperchat.service.registration.auth;

import com.piedpiper.piperchat.data.model.authorization.token.Token;
import com.piedpiper.piperchat.data.model.authorization.token.TokenFactory;
import com.piedpiper.piperchat.data.model.authorization.userauth.UserAuth;
import com.piedpiper.piperchat.data.model.authorization.userauth.UserAuthFactory;
import com.piedpiper.piperchat.data.model.user.User;
import com.piedpiper.piperchat.data.repo.AuthRepo;
import com.piedpiper.piperchat.exception.InvalidTokenException;
import com.piedpiper.piperchat.exception.TokenExpiredException;
import org.springframework.stereotype.Service;

/**
 * Created By: Yahia
 */
@Service
public class AuthServiceImpl implements AuthService {

    private AuthRepo authRepo;
    private UserAuthFactory userAuthFactory;
    private TokenFactory tokenFactory;

    public AuthServiceImpl(AuthRepo authRepo, UserAuthFactory userAuthFactory, TokenFactory tokenFactory) {
        this.authRepo = authRepo;
        this.userAuthFactory = userAuthFactory;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public UserAuth createAuthFor(User user) {
        UserAuth userAuth = userAuthFactory.create(user);
        authRepo.save(userAuth);
        return userAuth;
    }

    @Override
    public UserAuth refreshToken(String token) {
        UserAuth userAuth = authRepo.findByRefreshToken(Token.fromValue(token));
        if (userAuth == null) throw new InvalidTokenException();
        if (userAuth.isRefreshTokenValid()) throw new TokenExpiredException();
        userAuth.newToken(tokenFactory.createToken(userAuth.getRefreshToken()));
        authRepo.save(userAuth);
        return userAuth;
    }
}
