package it.incalza.notification.bucket.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.UUID;

/**
 * Created by sincalza on 04/12/2016.
 */
public class BucketItem {

    private UUID notificationId;
    private String systemId;
    private String alertEmail;
    private boolean alertSent;

    public BucketItem() {
    }

    public BucketItem(UUID notificationId, String systemId, String alertEmail, boolean alertSent) {
        this.notificationId = notificationId;
        this.systemId = systemId;
        this.alertEmail = alertEmail;
        this.alertSent = alertSent;
    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(UUID notificationId) {
        this.notificationId = notificationId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getAlertEmail() {
        return alertEmail;
    }

    public void setAlertEmail(String alertEmail) {
        this.alertEmail = alertEmail;
    }

    public boolean isAlertSent() {
        return alertSent;
    }

    public void setAlertSent(boolean alertSent) {
        this.alertSent = alertSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BucketItem that = (BucketItem) o;

        return new EqualsBuilder()
                .append(alertSent, that.alertSent)
                .append(notificationId, that.notificationId)
                .append(systemId, that.systemId)
                .append(alertEmail, that.alertEmail)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(notificationId)
                .append(systemId)
                .append(alertEmail)
                .append(alertSent)
                .toHashCode();
    }
}
