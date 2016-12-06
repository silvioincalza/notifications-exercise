package it.incalza.notification.bucket.domain;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static java.util.UUID.randomUUID;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by sincalza on 06/12/2016.
 */

import static org.mockito.Mockito.*;

public class NotifyByEmailTest {

    BucketRepository bucketRepository = mock(BucketRepository.class);
    EmailProvider emailProvider = mock(EmailProvider.class);
    NotifyByEmail notifyByEmail = new NotifyByEmail(bucketRepository, emailProvider);

    @Test
    public void onNotify() throws Exception {
        when(bucketRepository.getNotSent()).thenReturn(singletonMap("EMAIL", new HashSet<>(asList(new BucketItem(randomUUID(), "system", "EMAIL", false)))));
        notifyByEmail.onNotify();
        verify(emailProvider, times(1)).send(eq("EMAIL"), eq("You have received 1 notification"));
    }

}