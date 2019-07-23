package com.piedpiper.piperchat.data.model.message;

import com.piedpiper.piperchat.data.model.conversation.Conversation;
import com.piedpiper.piperchat.data.model.user.User;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Nullable
    @ManyToOne
    private Conversation conversation;
    @Nullable
    @ManyToOne
    private User user;

    private long timeCreated;

    public Message() {
        timeCreated = System.currentTimeMillis();
    }

    public Message(@NotNull String text) {
        super();
        this.text = text;
    }

    public Message(@NotNull String text, @Nullable Conversation conversation) {
        super();
        this.text = text;
        this.conversation = conversation;
    }

    public Message(@NotNull String text, @Nullable Conversation conversation, @Nullable User user) {
        super();
        this.text = text;
        this.conversation = conversation;
        this.user = user;
    }

    public Long getId() {
        return Id;
    }

    public long getTimeCreated() {
        return timeCreated;
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

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return timeCreated == message.timeCreated &&
            Objects.equals(Id, message.Id) &&
            text.equals(message.text) &&
            Objects.equals(conversation, message.conversation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, text, conversation, timeCreated);
    }
}
