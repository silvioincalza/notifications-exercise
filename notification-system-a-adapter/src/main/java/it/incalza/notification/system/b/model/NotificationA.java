package it.incalza.notification.system.b.model;

import it.incalza.notification.bucket.domain.model.Notification;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class NotificationA extends Notification {

    private String userId;
    private String message;

    public NotificationA() {
        super();
    }

    public NotificationA(String userId, String message) {
        this();
        this.userId = userId;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        NotificationA that = (NotificationA) o;

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
