package it.incalza.notification.bucket.domain;

import org.junit.Test;

import java.util.Set;
import java.util.UUID;

import static java.util.Collections.singleton;
import static java.util.UUID.randomUUID;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by sincalza on 06/12/2016.
 */
public class DefaultNotificationReceiverTest {

    static class Receiver extends DefaultNotificationReceiver<ExampleNotification> {
        public Receiver(NotificationRepository<ExampleNotification> notificationRepository, BucketRepository bucketRepository, UserRepository userRepository) {
            super(notificationRepository, bucketRepository, userRepository);
        }

        @Override
        public String systemId() {
            return "test";
        }
    }

    private NotificationRepository<ExampleNotification> notificationRepository = mock(NotificationRepository.class);
    private BucketRepository bucketRepository = mock(BucketRepository.class);
    private UserRepository userRepository = mock(UserRepository.class);


    private Receiver receiver = new Receiver(notificationRepository, bucketRepository, userRepository);

    @Test
    public void onReceive() {
        UUID notificationID = randomUUID();
        String user_id = "USER_ID";
        Set<ExampleNotification> notifications = singleton(new ExampleNotification(notificationID, user_id));
        when(userRepository.retrieveEmail(eq(user_id))).thenReturn(user_id);

        receiver.onReceive(notifications);

        verify(notificationRepository, times(1)).save(eq(notifications));
        verify(userRepository, times(1)).retrieveEmail(eq(user_id));
        verify(bucketRepository, times(1)).put(eq(singleton(new BucketItem(notificationID, "test", user_id, false))));
    }

}