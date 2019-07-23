package com.piedpiper.piperchat.controller;

import com.piedpiper.piperchat.bean.validation.IdParser;
import com.piedpiper.piperchat.data.model.conversation.ConversationResponse;
import com.piedpiper.piperchat.data.model.conversation.ConversationsReponseBody;
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
    private IdParser idParser;
    private ConversationsService conversationsService;

    public ConversationsController(IdParser idParser, ConversationsService conversationsService) {
        this.idParser = idParser;
        this.conversationsService = conversationsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversationsReponseBody> getConversations(@PathVariable("id") String userId) {
        long id = idParser.parse(userId);
        Collection<ConversationResponse> conversationResponseList = ConversationResponse.from(conversationsService.getUserConversations(id));
        return ResponseEntity.ok(new ConversationsReponseBody(conversationResponseList));
    }
}
