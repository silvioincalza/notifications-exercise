package it.incalza.notification.bucket.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by sincalza on 04/12/2016.
 */
public abstract class Notification implements Serializable {

    private final UUID uuid;

    private final String userId;

    public Notification(String userId) {
        this.userId = userId;
        this.uuid = UUID.randomUUID();
    }

    public Notification(UUID uuid, String userId) {
        this.uuid = uuid;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        return new EqualsBuilder()
                .append(userId, that.userId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .toHashCode();
    }
}
