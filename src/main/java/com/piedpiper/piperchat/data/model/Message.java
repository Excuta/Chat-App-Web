package com.piedpiper.piperchat.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

/**
 * Created By: Yahia
 */
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotNull
    private String text;
    @Null
    @ManyToOne
    private Conversation conversation;

    public Message() {
    }

    public Message(@NotNull String text) {
        this.text = text;
    }

    public Message(@NotNull String text, @Null Conversation conversation) {
        this.text = text;
        this.conversation = conversation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Id.equals(message.Id) &&
            text.equals(message.text) &&
            conversation.equals(message.conversation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, text, conversation);
    }
}
