package com.piedpiper.piperchat.data.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.piedpiper.piperchat.data.model.authorization.userauth.UserAuthResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class UserResponse {
    @NonNull
    private final String Id;
    @NonNull
    private final String name;
    @NonNull
    private final String email;
    @NonNull
    private final UserAuthResponse userAuthResponse;

    public UserResponse(Long id, String firstName, @Nullable String lastName, String email, UserAuthResponse userAuthResponse) {
        Id = String.valueOf(id);
        this.userAuthResponse = userAuthResponse;
        StringBuilder name = new StringBuilder(firstName);
        if (lastName != null && !lastName.isEmpty()) name.append(" ").append(lastName);
        this.name = name.toString();
        this.email = email;
    }

    public static UserResponse from(User user, UserAuthResponse userAuthResponse) {
        return new UserResponse(user.getId(),
                                user.getFirstName(),
                                user.getLastName(),
                                user.getEmail(),
                                userAuthResponse);
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

    @JsonProperty(value = "authorization")
    public UserAuthResponse getUserAuthResponse() {
        return userAuthResponse;
    }
}
