package it.incalza.notification.bucket.domain;

import java.util.Set;

/**
 * Created by sincalza on 05/12/2016.
 */
public class SaveNotifications<T extends Notification> implements NotificationCommand<T> {

    private final NotificationRepository<T> notificationRepository;

    public SaveNotifications(NotificationRepository<T> notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void onNotifications(Set<T> notifications) {
        notificationRepository.save(notifications);
    }

}
