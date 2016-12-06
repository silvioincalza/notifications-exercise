package it.incalza.notification.bucket.domain;

/**
 * Created by sincalza on 04/12/2016.
 */
public class BucketException extends RuntimeException {
    public BucketException(String message) {
        super(message);
    }

    public BucketException(String message, Throwable cause) {
        super(message, cause);
    }
}
