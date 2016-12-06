package it.incalza.notification.bucket.domain;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * Created by sincalza on 05/12/2016.
 */
public class SaveNotificationTest {

    static class Example extends Notification {
        public Example(String userId) {
            super(userId);
        }
    }

    @Test
    public void onReceive() {
        NotificationRepository<Example> notificationRepository = mock(NotificationRepository.class);
        SaveNotification<Example> saveNotification = new SaveNotification<>(notificationRepository);
        saveNotification.onReceive(new HashSet<>(asList(new Example("userID"))));
        verify(notificationRepository, only()).save(anySetOf(Example.class));
    }

}