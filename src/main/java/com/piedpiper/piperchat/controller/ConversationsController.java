package com.piedpiper.piperchat.controller;

import com.piedpiper.piperchat.data.model.conversation.ConversationResponse;
import com.piedpiper.piperchat.service.conversations.ConversationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/conversations")
public class ConversationsController {
    private ConversationsService conversationsService;

    public ConversationsController(ConversationsService conversationsService) {
        this.conversationsService = conversationsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection<ConversationResponse>> getConversations(@PathVariable("id") String userId) {
        try {
            long id = Long.parseLong(userId);
            return ResponseEntity.ok(ConversationResponse.fromConversation(conversationsService.getUserConversations(id)));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
