package it.incalza.notification.bucket.domain.model;

/**
 * Created by sincalza on 04/12/2016.
 */
public class DuplicateNotificationException extends NotificationBucketException {
    public DuplicateNotificationException(String message) {
        super(message);
    }

    public DuplicateNotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
