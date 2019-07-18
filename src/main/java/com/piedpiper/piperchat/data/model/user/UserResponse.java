package com.piedpiper.piperchat.data.model.user;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class UserResponse {
    @NonNull
    private final String Id;
    @NonNull
    private final String name;
    @NonNull
    private final String email;

    public UserResponse(Long id, String firstName, @Nullable String lastName, String email) {
        Id = String.valueOf(id);
        StringBuilder name = new StringBuilder(firstName);
        if (lastName != null && !lastName.isEmpty()) name.append(" ").append(lastName);
        this.name = name.toString();
        this.email = email;
    }

    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getId(),
                                user.getFirstName(),
                                user.getLastName(),
                                user.getEmail());
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
