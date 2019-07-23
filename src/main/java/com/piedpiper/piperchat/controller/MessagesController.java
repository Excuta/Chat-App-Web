package com.piedpiper.piperchat.controller;

import com.piedpiper.piperchat.bean.validation.IdParser;
import com.piedpiper.piperchat.data.model.message.Message;
import com.piedpiper.piperchat.data.model.message.MessageResponse;
import com.piedpiper.piperchat.data.model.message.MessageResponseBody;
import com.piedpiper.piperchat.service.message.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created By: Yahia
 */
@RestController
@RequestMapping("/messages")
public class MessagesController {

    private IdParser idParser;
    private MessageService messageService;

    public MessagesController(IdParser idParser, MessageService messageService) {
        this.idParser = idParser;
        this.messageService = messageService;
    }

    @GetMapping("/conversation/{id}")
    public ResponseEntity<MessageResponseBody> getMessages(@PathVariable("id") String conversationId) {
        long id = idParser.parse(conversationId);
        Collection<Message> messages = messageService.getMessages(id, 1);
        return ResponseEntity.ok(new MessageResponseBody(MessageResponse.from(messages)));
    }
}
