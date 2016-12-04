package it.incalza.notification.bucket.domain.support;

import it.incalza.notification.bucket.domain.model.NotificationBucketException;

/**
 * Created by sincalza on 04/12/2016.
 */
public final class Assert {

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new NotificationBucketException(message);
        }
    }

}
