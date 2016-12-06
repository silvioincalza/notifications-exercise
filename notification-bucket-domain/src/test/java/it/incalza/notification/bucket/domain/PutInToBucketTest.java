package it.incalza.notification.bucket.domain;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.UUID.randomUUID;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by sincalza on 06/12/2016.
 */
public class PutInToBucketTest {
    static class Example extends Notification {
        public Example(UUID uuid, String userId) {
            super(uuid, userId);
        }
    }

    UserRepository userRepository = new UserRepository() {
        @Override
        public String retrieveEmail(String userId) {
            return userId;
        }
    };
    BucketRepository bucketRepository = mock(BucketRepository.class);
    PutInToBucket<Example> putInToBucket = new PutInToBucket<>("system1", bucketRepository, userRepository);

    @Test
    public void onReceive() {
        UUID uuid = randomUUID();
        Set<Example> notifications = new HashSet<>(asList(new Example(uuid, "userID")));
        HashSet<BucketItem> expectedToPut = new HashSet<>(asList(new BucketItem(uuid, "system1", "userID", false)));
        putInToBucket.onReceive(notifications);
        verify(bucketRepository, times(1)).put(eq(expectedToPut));
    }


}