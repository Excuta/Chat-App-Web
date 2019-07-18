package com.piedpiper.piperchat.controller;

import com.piedpiper.piperchat.bean.validation.IdParser;
import com.piedpiper.piperchat.data.model.user.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by: Yahia.Ragae
 * Creation Date: 7/16/2019 2:11 PM
 **/
@RestController
@RequestMapping("/contacts")
public class ContactsController {
    private IdParser idParser;

    public ContactsController(IdParser idParser) {
        this.idParser = idParser;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection<UserResponse>> getContacts(@PathVariable("id") String userId) {
        long id = idParser.parse(userId);
        return null;
    }
}
