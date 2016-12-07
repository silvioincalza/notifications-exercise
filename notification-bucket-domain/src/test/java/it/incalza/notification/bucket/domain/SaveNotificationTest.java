package it.incalza.notification.bucket.domain;

import org.junit.Test;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.*;

/**
 * Created by sincalza on 05/12/2016.
 */
public class SaveNotificationTest {

    @Test
    public void onReceive() {
        NotificationRepository<ExampleNotification> notificationRepository = mock(NotificationRepository.class);
        SaveNotifications<ExampleNotification> saveNotification = new SaveNotifications<>(notificationRepository);
        saveNotification.onNotifications(new HashSet<>(asList(new ExampleNotification("userID"))));
        verify(notificationRepository, only()).save(anySetOf(ExampleNotification.class));
    }

}