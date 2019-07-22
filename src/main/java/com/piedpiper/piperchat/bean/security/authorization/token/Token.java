package com.piedpiper.piperchat.bean.security.authorization.token;

import org.springframework.lang.NonNull;

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
    @NonNull
    private final Long creationTimeInSeconds;
    @NonNull
    private final Long expirationTimeInSeconds;

    Token(@NonNull String value,@NonNull Long tokenDurationInSeconds) {
        this.creationTimeInSeconds = getCurrentTimeInSeconds();
        expirationTimeInSeconds = creationTimeInSeconds + tokenDurationInSeconds;
        this.value = value;
    }

    @NonNull
    public String getValue() {
        return value;
    }
    @NonNull
    public Long getCreationTimeInSeconds() {
        return creationTimeInSeconds;
    }
    @NonNull
    public Long getExpirationTimeInSeconds() {
        return expirationTimeInSeconds;
    }

    public boolean isExpired() {
        return getCreationTimeInSeconds() >= expirationTimeInSeconds;
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
