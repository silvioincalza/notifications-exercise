package it.incalza.notification.bucket.adapter.systemA.model;

import it.incalza.notification.bucket.domain.Notification;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

import static java.util.UUID.randomUUID;


public class ANotification implements Notification {

    private String userId;
    private String message;
    private UUID uuid;

    public ANotification() {
        this.uuid = randomUUID();

    }

    public ANotification(String userId, String message) {
        this();
        this.userId = userId;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ANotification that = (ANotification) o;

        return new EqualsBuilder()
                .append(userId, that.userId)
                .append(message, that.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(message)
                .toHashCode();
    }
}
