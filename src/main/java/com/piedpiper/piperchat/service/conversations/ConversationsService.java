package com.piedpiper.piperchat.service.conversations;

import com.piedpiper.piperchat.data.model.conversation.Conversation;

import java.util.Collection;

/**
 * Creadted by: Yahia
 **/
public interface ConversationsService {
    Collection<Conversation> getUserConversations(Long userId);
}
