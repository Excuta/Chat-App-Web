package com.piedpiper.piperchat.service.registration.login;

import com.piedpiper.piperchat.data.model.user.User;
import com.piedpiper.piperchat.data.requestbody.CredentialsRequest;

/**
 * Created By: Yahia
 */
public interface LoginService {
    User attemptLogin(CredentialsRequest credentialsRequest);
}
