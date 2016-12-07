package it.incalza.notification.bucket.domain;

import java.util.Set;

/**
 * Created by sincalza on 04/12/2016.
 */
public interface NotificationRepository<T extends Notification> {

    void save(Set<T> entities);

}
