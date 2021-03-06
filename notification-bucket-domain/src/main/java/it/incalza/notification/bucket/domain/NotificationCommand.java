package it.incalza.notification.bucket.domain;

import java.util.Set;

/**
 * Created by sincalza on 06/12/2016.
 */
public interface NotificationCommand<T extends Notification> {
    void onNotifications(Set<T> notifications);
}
