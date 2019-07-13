package com.piedpiper.piperchat.data.requestbody;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Created By: Yahia
 */
public class Credentials {
    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String password;

    public Credentials() {
    }

    public Credentials(@NotEmpty @NotNull @Email String email, @NotNull @NotEmpty @Size(min = 3) String password) {
        this.email = email;
        this.password = password;
    }

    public static Credentials test() {
        return new Credentials("email@domain.com", "validpassword123141");
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
        Credentials that = (Credentials) o;
        return email.equals(that.email) &&
            password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
