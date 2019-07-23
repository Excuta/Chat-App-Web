package com.piedpiper.piperchat.service.message;

import com.piedpiper.piperchat.data.model.message.Message;
import com.piedpiper.piperchat.data.repo.MessagesRepo;
import com.piedpiper.piperchat.data.repo.SycnhronizedMessagesRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created By: Yahia
 */
@Service
public class MessageServiceImpl implements MessageService {
    private MessagesRepo messagesRepo;

    public MessageServiceImpl(@SycnhronizedMessagesRepo MessagesRepo messagesRepo) {
        this.messagesRepo = messagesRepo;
    }

    @Override
    public Collection<Message> getMessages(long conversationId, int page) {
        return null;
    }

    @Override
    public void sendMessage(String text, long senderId, long conversationId) {

    }
}
