package com.piedpiper.piperchat.controller;

import com.piedpiper.piperchat.data.model.User;
import com.piedpiper.piperchat.data.model.user.UserResponse;
import com.piedpiper.piperchat.data.requestbody.Credentials;
import com.piedpiper.piperchat.service.registration.login.LoginService;
import com.piedpiper.piperchat.service.registration.signup.SignUpService;
import lombok.extern.slf4j.Slf4j;
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

    public RegistrationController(SignUpService signUpService, LoginService loginService) {
        this.signUpService = signUpService;
        this.loginService = loginService;
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
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }
}
