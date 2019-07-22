package com.piedpiper.piperchat.data.model.authorization.token;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created By: Yahia
 */
@Entity
public class Token {
    @Id
    @Column(length = 100)
    private String value;
    @Nullable
    private Long creationTimeInSeconds;
    @Nullable
    private Long expirationTimeInSeconds;

    public Token() {
    }

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

    public static Token test() {
        return new Token("");
    }

    public static Long getCurrentTimeInSeconds() {
        return System.currentTimeMillis() / 1000;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return value.equals(token.value) &&
            Objects.equals(creationTimeInSeconds, token.creationTimeInSeconds) &&
            Objects.equals(expirationTimeInSeconds, token.expirationTimeInSeconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, creationTimeInSeconds, expirationTimeInSeconds);
    }
}
