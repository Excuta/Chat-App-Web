package com.piedpiper.piperchat.service.registration.auth;

import com.piedpiper.piperchat.bean.security.authorization.userauth.UserAuth;
import com.piedpiper.piperchat.bean.security.authorization.userauth.UserAuthFactory;
import com.piedpiper.piperchat.bean.security.authorization.userauth.repo.AuthRepo;
import com.piedpiper.piperchat.data.model.user.User;
import org.springframework.stereotype.Service;

/**
 * Created By: Yahia
 */
@Service
public class AuthServiceImpl implements AuthService {

    private AuthRepo authRepo;
    private UserAuthFactory userAuthFactory;

    public AuthServiceImpl(AuthRepo authRepo, UserAuthFactory userAuthFactory) {
        this.authRepo = authRepo;
        this.userAuthFactory = userAuthFactory;
    }

    @Override
    public UserAuth createAuthFor(User user) {
        UserAuth userAuth = userAuthFactory.create(user);
        authRepo.save(userAuth);
        return userAuth;
    }
}
