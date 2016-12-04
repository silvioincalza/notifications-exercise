package it.incalza.notification.bucket.domain.model;

/**
 * Created by sincalza on 04/12/2016.
 */
public class NotificationBucketException extends RuntimeException {
    public NotificationBucketException(String message) {
        super(message);
    }

    public NotificationBucketException(String message, Throwable cause) {
        super(message, cause);
    }
}
