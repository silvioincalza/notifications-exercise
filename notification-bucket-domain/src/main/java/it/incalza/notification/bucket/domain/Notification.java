package it.incalza.notification.bucket.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.UUID;

import static java.util.UUID.randomUUID;

/**
 * Created by sincalza on 04/12/2016.
 */
public interface Notification extends Serializable {

//    private final UUID uuid;
//
//    private final String userId;
//
//    public Notification(String userId) {
//        this(randomUUID(), userId);
//    }
//
//    public Notification(UUID uuid, String userId) {
//        this.uuid = uuid;
//        this.userId = userId;
//    }

    String getUserId();

    UUID getUuid();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Notification that = (Notification) o;
//
//        return new EqualsBuilder()
//                .append(userId, that.userId)
//                .isEquals();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder(17, 37)
//                .append(userId)
//                .toHashCode();
//    }
}
