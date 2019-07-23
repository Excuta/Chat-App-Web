package com.piedpiper.piperchat.service.message;

import com.piedpiper.piperchat.data.model.message.Message;

import java.util.Collection;

/**
 * Created By: Yahia
 */
public interface MessageService {
    Collection<Message> getMessages(long conversationId, int page);

    void sendMessage(String text, long senderId, long conversationId);
}
