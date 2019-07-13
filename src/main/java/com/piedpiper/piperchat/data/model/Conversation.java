package com.piedpiper.piperchat.data.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created By: Yahia
 */
@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToMany(mappedBy = "conversations")
    private Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
        mappedBy = "conversation",
        orphanRemoval = true)
    private Set<Message> messages = new HashSet<>();

    public Conversation() {
    }

    public Conversation(Set<User> users, Set<Message> messages) {
        this.users.addAll(users);
        this.messages.addAll(messages);
    }

    public Set<User> getUsers() {
        return users;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    public boolean hasUser(User user) {
        return users.contains(user);
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public boolean addMessage(Message message) {
        message.setConversation(this);
        return messages.add(message);
    }

    public boolean removeMessage(Message message) {
        message.setConversation(null);
        return messages.remove(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Id.equals(that.Id) &&
            users.equals(that.users) &&
            messages.equals(that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, users, messages);
    }
}
