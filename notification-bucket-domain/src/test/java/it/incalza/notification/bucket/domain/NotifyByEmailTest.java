package it.incalza.notification.bucket.domain;

import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

/**
 * Created by sincalza on 06/12/2016.
 */

public class NotifyByEmailTest {

    BucketRepository bucketRepository = mock(BucketRepository.class);
    EmailProvider emailProvider = mock(EmailProvider.class);
    NotifyByEmail notifyByEmail = new NotifyByEmail(bucketRepository, emailProvider);

    @Test
    public void onNotify() throws Exception {
        Set<BucketItem> bucketItems = new HashSet<>(asList(new BucketItem(randomUUID(), "system", "EMAIL", false)));
        Map<String, Set<BucketItem>> map = singletonMap("EMAIL", bucketItems);
        when(bucketRepository.getNotSent()).thenReturn(map);
        notifyByEmail.onNotify();
        verify(emailProvider, times(1)).send(eq("EMAIL"), eq("You have received 1 notification"));
        verify(bucketRepository, times(1)).markTrueAlertSent(eq(bucketItems));
    }

}