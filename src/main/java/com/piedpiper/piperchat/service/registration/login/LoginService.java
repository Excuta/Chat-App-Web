package com.piedpiper.piperchat.service.registration.login;

import com.piedpiper.piperchat.data.model.user.User;
import com.piedpiper.piperchat.data.requestbody.Credentials;

/**
 * Created By: Yahia
 */
public interface LoginService {
    User attemptLogin(Credentials credentials);
}
