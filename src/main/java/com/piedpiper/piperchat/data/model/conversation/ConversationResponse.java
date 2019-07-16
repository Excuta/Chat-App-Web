package com.piedpiper.piperchat.data.model.conversation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by: Yahia.Ragae
 * Creation Date: 7/16/2019 1:57 PM
 **/
public class ConversationResponse {
    private final String id;
    private final String name;

    public ConversationResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ConversationResponse fromConversation(Conversation conversation) {
        return new ConversationResponse(String.valueOf(conversation.getId()), conversation.getName());
    }

    public static Collection<ConversationResponse> fromConversation(Collection<Conversation> conversations) {
        ArrayList<ConversationResponse> conversationsResponse = new ArrayList<>();
        for (Conversation conversation : conversations) {
            conversationsResponse.add(fromConversation(conversation));
        }
        return conversationsResponse;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationResponse that = (ConversationResponse) o;
        return id.equals(that.id) &&
            name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
