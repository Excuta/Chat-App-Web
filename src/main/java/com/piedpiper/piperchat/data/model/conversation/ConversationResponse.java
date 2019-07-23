package com.piedpiper.piperchat.data.model.conversation;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by: Yahia.Ragae
 * Creation Date: 7/16/2019 1:57 PM
 **/
public class ConversationResponse {
    private final String id;
    @Nullable
    private final String name;

    public ConversationResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ConversationResponse from(Conversation conversation) {
        return new ConversationResponse(String.valueOf(conversation.getId()), conversation.getName());
    }

    public static Collection<ConversationResponse> from(Collection<Conversation> conversations) {
        ArrayList<ConversationResponse> conversationsResponse = new ArrayList<>();
        for (Conversation conversation : conversations) {
            conversationsResponse.add(from(conversation));
        }
        return conversationsResponse;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationResponse that = (ConversationResponse) o;
        return id.equals(that.id) &&
            Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
