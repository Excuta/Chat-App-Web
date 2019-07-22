package com.piedpiper.piperchat.controller;

import com.piedpiper.piperchat.data.model.authorization.userauth.UserAuth;
import com.piedpiper.piperchat.data.model.authorization.userauth.UserAuthResponse;
import com.piedpiper.piperchat.data.requestbody.TokenRequest;
import com.piedpiper.piperchat.exception.InvalidTokenException;
import com.piedpiper.piperchat.service.registration.auth.AuthService;
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
@RequestMapping("/authorization")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserAuthResponse> refreshToken(@RequestBody @Valid TokenRequest tokenRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new InvalidTokenException();
        UserAuth userAuth = authService.refreshToken(tokenRequest.getToken());
        return ResponseEntity.ok(UserAuthResponse.from(userAuth));
    }
}
