package it.incalza.notification.bucket.adapter.systemA;

import it.incalza.notification.bucket.adapter.systemA.model.ANotification;
import it.incalza.notification.bucket.adapter.systemA.repository.ANotificationRepository;
import it.incalza.notification.bucket.domain.BucketRepository;
import it.incalza.notification.bucket.domain.DefaultNotificationReceiver;
import it.incalza.notification.bucket.domain.UserRepository;

/**
 * Created by sincalza on 06/12/2016.
 */
public class ANotificationDefaultReceiver extends DefaultNotificationReceiver<ANotification> {

    public ANotificationDefaultReceiver(ANotificationRepository notificationRepository, BucketRepository bucketRepository, UserRepository userRepository) {
        super(notificationRepository, bucketRepository, userRepository);
    }

    @Override
    public String systemId() {
        return ANotification.class.getSimpleName();
    }
}
