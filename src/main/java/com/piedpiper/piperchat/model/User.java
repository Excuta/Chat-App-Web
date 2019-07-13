package com.piedpiper.piperchat.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created By: Yahia
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotNull
    private String firstName;
    @Null
    private String lastName;
    @ManyToMany
    @JoinTable(name = "user_conversation",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "conversation_id"))
    private Set<Conversation> conversations = new HashSet<>();
//    private byte[] picture; todo implement probably create a model

    public User() {
    }

    public User(@NotNull String firstName, @Null String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(@NotNull String firstName, @Null String lastName, Set<Conversation> conversations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.conversations.addAll(conversations);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder(firstName);
        if (lastName != null) fullName.append(" ").append(lastName);
        return fullName.toString();
    }

    public Set<Conversation> getConversations() {
        return conversations;
    }

    public boolean addConversation(Conversation conversation) {
        return conversations.add(conversation);
    }

    public boolean removeConversation(Conversation conversation) {
        return conversations.remove(conversation);
    }

    public boolean hasConversation(Conversation conversation) {
        return conversations.contains(conversation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Id.equals(user.Id) &&
            firstName.equals(user.firstName) &&
            Objects.equals(lastName, user.lastName) &&
            conversations.equals(user.conversations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, firstName, lastName, conversations);
    }
}
