package com.piedpiper.piperchat.service.registration.login;

import com.piedpiper.piperchat.data.requestbody.Credentials;

/**
 * Created By: Yahia
 */
public interface LoginService {
    void attemptLogin(Credentials credentials);
}
