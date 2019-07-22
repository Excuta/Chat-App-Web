package com.piedpiper.piperchat.data.requestbody;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Created By: Yahia
 */
public class CredentialsRequest {
    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String password;

    public CredentialsRequest() {
    }

    public CredentialsRequest(@NotEmpty @NotNull @Email String email, @NotNull @NotEmpty @Size(min = 3) String password) {
        this.email = email;
        this.password = password;
    }

    public static CredentialsRequest test() {
        return new CredentialsRequest("email@domain.com", "validpassword123141");
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialsRequest that = (CredentialsRequest) o;
        return email.equals(that.email) &&
            password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
