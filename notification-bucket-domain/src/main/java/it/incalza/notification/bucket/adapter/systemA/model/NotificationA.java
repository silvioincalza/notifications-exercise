package it.incalza.notification.bucket.adapter.systemA.model;

import it.incalza.notification.bucket.domain.Notification;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class NotificationA extends Notification {

    private String message;

    public NotificationA(String userId, String message) {
        super(userId);
        this.message = message;
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
                .append(super.getUserId(), that.getUserId())
                .append(message, that.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(super.getUserId())
                .append(message)
                .toHashCode();
    }
}
