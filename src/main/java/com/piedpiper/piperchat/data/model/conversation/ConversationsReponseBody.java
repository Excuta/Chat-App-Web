package com.piedpiper.piperchat.data.model.conversation;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created By: Yahia
 */
public class ConversationsReponseBody {
    @NonNull
    private List<ConversationResponse> conversationResponseList = new ArrayList<>();

    public ConversationsReponseBody(@Nullable Collection<ConversationResponse> conversationResponseList) {
        if (conversationResponseList != null) this.conversationResponseList.addAll(conversationResponseList);
    }

    public List<ConversationResponse> getConversationResponseList() {
        return conversationResponseList;
    }
}
