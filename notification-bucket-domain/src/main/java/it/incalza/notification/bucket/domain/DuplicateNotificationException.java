package it.incalza.notification.bucket.domain;

/**
 * Created by sincalza on 04/12/2016.
 */
public class DuplicateNotificationException extends BucketException {
    public DuplicateNotificationException(String message) {
        super(message);
    }

    public DuplicateNotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
