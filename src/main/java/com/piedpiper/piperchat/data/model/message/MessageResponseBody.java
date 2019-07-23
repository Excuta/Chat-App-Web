package com.piedpiper.piperchat.data.model.message;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created By: Yahia
 */
public class MessageResponseBody {
    @NonNull
    private List<MessageResponse> messageResponses = new ArrayList<>();

    public MessageResponseBody(@Nullable Collection<MessageResponse> messageResponses) {
        if (messageResponses != null) this.messageResponses.addAll(messageResponses);
    }

    public List<MessageResponse> getMessageResponses() {
        return messageResponses;
    }
}
