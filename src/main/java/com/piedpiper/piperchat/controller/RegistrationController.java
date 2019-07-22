package com.piedpiper.piperchat.controller;

import com.piedpiper.piperchat.bean.security.authorization.userauth.UserAuth;
import com.piedpiper.piperchat.bean.security.authorization.userauth.UserAuthResponse;
import com.piedpiper.piperchat.data.model.user.User;
import com.piedpiper.piperchat.data.model.user.UserResponse;
import com.piedpiper.piperchat.data.requestbody.Credentials;
import com.piedpiper.piperchat.service.registration.auth.AuthService;
import com.piedpiper.piperchat.service.registration.login.LoginService;
import com.piedpiper.piperchat.service.registration.signup.SignUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created By: Yahia
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private SignUpService signUpService;
    private LoginService loginService;
    private AuthService authService;

    public RegistrationController(SignUpService signUpService, LoginService loginService, AuthService authService) {
        this.signUpService = signUpService;
        this.loginService = loginService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new IllegalArgumentException();
        signUpService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> attemptLogin(@RequestBody @Valid Credentials credentials, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new IllegalArgumentException();
        User user = loginService.attemptLogin(credentials);
        UserAuth userAuth = authService.createAuthFor(user);
        return ResponseEntity.ok(UserResponse.from(user, UserAuthResponse.from(userAuth)));
    }
}
