package com.piedpiper.piperchat.data.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @NotNull
    @Email
    private String email;
    @ManyToMany
    @JoinTable(name = "user_conversation",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "conversation_id"))
    private Set<Conversation> conversations = new HashSet<>();
//    private byte[] picture; todo implement probably create a model

    public User() {
    }

    public User(@NotNull String firstName, @Null String lastName, @NotNull @Email String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(@NotNull String firstName, @Null String lastName, @NotNull @Email String email, Set<Conversation> conversations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getEmail() {
        return email;
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
            email.equals(user.email) &&
            conversations.equals(user.conversations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, firstName, lastName, email, conversations);
    }
}
