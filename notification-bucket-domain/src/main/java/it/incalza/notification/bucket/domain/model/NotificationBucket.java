package it.incalza.notification.bucket.domain.model;

/**
 * Created by sincalza on 04/12/2016.
 */
public class NotificationBucket {

    private Notification notification;
    private String alertEmail;
    private boolean alertSent;

    public NotificationBucket() {
    }

    public NotificationBucket(Notification notification , String alertEmail, boolean alertSent) {
        this.notification = notification;
        this.alertEmail = alertEmail;
        this.alertSent = alertSent;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
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
}
