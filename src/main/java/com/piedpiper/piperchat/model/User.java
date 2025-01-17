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
    private Set<Conversation> conversations = new HashSet<>();
//    private byte[] picture; todo implement probably create a model

    public User(@NotNull String firstName, @Null String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
