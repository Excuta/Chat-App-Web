package com.piedpiper.piperchat.service.registration.auth;

import com.piedpiper.piperchat.data.model.authorization.userauth.UserAuth;
import com.piedpiper.piperchat.data.model.user.User;

/**
 * Created By: Yahia
 */
public interface AuthService {
    UserAuth createAuthFor(User user);

    UserAuth refreshToken(String token);
}
