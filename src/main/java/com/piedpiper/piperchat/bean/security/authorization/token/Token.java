package com.piedpiper.piperchat.bean.security.authorization.token;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created By: Yahia
 */
@Entity
public class Token {
    @Id
    @NonNull
    private final String value;
    @Nullable
    private final Long creationTimeInSeconds;
    @Nullable
    private final Long expirationTimeInSeconds;

    Token(@NonNull String value, @NonNull Long tokenDurationInSeconds) {
        this.creationTimeInSeconds = getCurrentTimeInSeconds();
        expirationTimeInSeconds = creationTimeInSeconds + tokenDurationInSeconds;
        this.value = value;
    }

    private Token(@NonNull String value) {
        this.value = value;
        this.creationTimeInSeconds = null;
        this.expirationTimeInSeconds = null;
    }

    public static Token fromValue(String value) {
        return new Token(value);
    }

    @NonNull

    public String getValue() {
        return value;
    }

    @Nullable
    public Long getCreationTimeInSeconds() {
        return creationTimeInSeconds;
    }

    @Nullable
    public Long getExpirationTimeInSeconds() {
        return expirationTimeInSeconds;
    }

    public boolean isExpired() {
        return expirationTimeInSeconds != null && getCurrentTimeInSeconds() >= expirationTimeInSeconds;
    }

    private Long getCurrentTimeInSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return value.equals(token.value) &&
            creationTimeInSeconds.equals(token.creationTimeInSeconds) &&
            expirationTimeInSeconds.equals(token.expirationTimeInSeconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, creationTimeInSeconds, expirationTimeInSeconds);
    }
}
