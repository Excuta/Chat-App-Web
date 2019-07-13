package com.piedpiper.piperchat.controller;

import com.piedpiper.piperchat.data.model.User;
import com.piedpiper.piperchat.data.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created By: Yahia
 */
@Slf4j
@RestController(value = "registration")
public class RegistrationController {

    private UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> createUser(@RequestParam("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new IllegalArgumentException();
        userRepo.save(user);
        return ResponseEntity.ok().build();
    }
}
