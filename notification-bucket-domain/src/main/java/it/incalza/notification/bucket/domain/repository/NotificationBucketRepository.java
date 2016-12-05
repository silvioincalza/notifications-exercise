package it.incalza.notification.bucket.domain.repository;

import it.incalza.notification.bucket.domain.model.Notification;
import it.incalza.notification.bucket.domain.model.NotificationBucket;
import it.incalza.notification.bucket.domain.model.Notifications;

/**
 * Created by sincalza on 04/12/2016.
 */
public interface NotificationBucketRepository {

     void save(NotificationBucket notifications);

}
