package com.piedpiper.piperchat.controller;

import com.piedpiper.piperchat.data.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By: Yahia
 */
@RestController(value = "registration")
public class RegistrationController {

    @PostMapping("/signup")
    public void createUser(@RequestParam("user") User user){

    }
}
