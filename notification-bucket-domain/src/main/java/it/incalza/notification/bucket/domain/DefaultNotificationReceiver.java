package it.incalza.notification.bucket.domain;

import java.util.Set;

/**
 * Created by sincalza on 06/12/2016.
 */
public abstract class DefaultNotificationReceiver<T extends Notification> implements Receiver<T> {
    private final ChainNotificationCommand<T> command;

    public DefaultNotificationReceiver(NotificationRepository<T> notificationRepository, BucketRepository bucketRepository, UserRepository userRepository) {
        command = new ChainNotificationCommand(new SaveNotifications<>(notificationRepository), new PutInToBucket(systemId(), bucketRepository, userRepository));
    }

    @Override
    public void onReceive(Set<T> notifications) {
        command.onNotifications(notifications);
    }

}
