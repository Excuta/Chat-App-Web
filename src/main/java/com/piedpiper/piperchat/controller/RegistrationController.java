package com.piedpiper.piperchat.controller;

import com.piedpiper.piperchat.data.model.User;
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
@Slf4j
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private SignUpService signUpService;

    public RegistrationController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new IllegalArgumentException();
        signUpService.createUser(user);
        return ResponseEntity.ok().build();
    }
}
