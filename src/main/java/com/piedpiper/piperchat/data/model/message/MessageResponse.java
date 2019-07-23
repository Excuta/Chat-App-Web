package com.piedpiper.piperchat.data.model.message;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created By: Yahia
 */
public class MessageResponse {

    @NonNull
    private Long Id;
    @NonNull
    private String text;
    private long timeCreated;

    public MessageResponse() {
    }

    public MessageResponse(Long id, String text, long timeCreated) {
        Id = id;
        this.text = text;
        this.timeCreated = timeCreated;
    }

    public static MessageResponse from(Message message) {
        return new MessageResponse(message.getId(),
                                   message.getText(),
                                   message.getTimeCreated());
    }

    public static ArrayList<MessageResponse> from(Collection<Message> messages) {
        ArrayList<MessageResponse> messageResponses = new ArrayList<>();
        for (Message message : messages) {
            messageResponses.add(from(message));
        }
        return messageResponses;
    }

    public Long getId() {
        return Id;
    }

    public String getText() {
        return text;
    }

    public long getTimeCreated() {
        return timeCreated;
    }
}
