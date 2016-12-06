package it.incalza.notification.bucket.domain;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by sincalza on 05/12/2016.
 */
public class SaveNotification<T extends Notification> implements NotificationCommand<T> {

    private final NotificationRepository<T> notificationRepository;

    public SaveNotification(NotificationRepository<T> notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void onReceive(Set<T> notifications) {
        notificationRepository.save(notifications);
    }


}
