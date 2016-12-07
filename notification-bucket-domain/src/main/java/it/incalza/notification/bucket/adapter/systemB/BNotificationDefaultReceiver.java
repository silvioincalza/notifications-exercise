package it.incalza.notification.bucket.adapter.systemB;

import it.incalza.notification.bucket.adapter.systemB.model.BNotification;
import it.incalza.notification.bucket.adapter.systemB.repository.BNotificationRepository;
import it.incalza.notification.bucket.domain.BucketRepository;
import it.incalza.notification.bucket.domain.DefaultNotificationReceiver;
import it.incalza.notification.bucket.domain.UserRepository;

/**
 * Created by sincalza on 06/12/2016.
 */
public class BNotificationDefaultReceiver extends DefaultNotificationReceiver<BNotification> {

    public BNotificationDefaultReceiver(BNotificationRepository notificationRepository, BucketRepository bucketRepository, UserRepository userRepository) {
        super(notificationRepository, bucketRepository, userRepository);
    }

    public String systemId() {
        return BNotification.class.getSimpleName();
    }

}