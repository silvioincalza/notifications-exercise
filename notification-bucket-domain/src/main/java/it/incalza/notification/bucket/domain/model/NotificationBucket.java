package it.incalza.notification.bucket.domain.model;

import java.util.UUID;

/**
 * Created by sincalza on 04/12/2016.
 */
public class NotificationBucket {

    private UUID notificationUuid;
    private String alertEmail;
    private boolean sentAlert;

    public NotificationBucket() {
    }

    public NotificationBucket(UUID notificationUuid, String alertEmail, boolean sentAlert) {
        this.notificationUuid = notificationUuid;
        this.alertEmail = alertEmail;
        this.sentAlert = sentAlert;
    }

    public UUID getNotificationUuid() {
        return notificationUuid;
    }

    public void setNotificationUuid(UUID notificationUuid) {
        this.notificationUuid = notificationUuid;
    }

    public String getAlertEmail() {
        return alertEmail;
    }

    public void setAlertEmail(String alertEmail) {
        this.alertEmail = alertEmail;
    }

    public boolean isSentAlert() {
        return sentAlert;
    }

    public void setSentAlert(boolean sentAlert) {
        this.sentAlert = sentAlert;
    }
}
