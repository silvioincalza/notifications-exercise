package it.incalza.notification.bucket.domain;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by sincalza on 05/12/2016.
 */
public class PutInToBucket<T extends Notification> implements NotificationCommand<T> {

    private final String systemId;
    private final BucketRepository bucketRepository;
    private final UserRepository userRepository;

    public PutInToBucket(String systemId, BucketRepository bucketRepository, UserRepository userRepository) {
        this.systemId = systemId;
        this.bucketRepository = bucketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onReceive(Set<T> notifications) {
        Set<BucketItem> items = notifications.stream().map(v -> convert(v)).collect(toSet());
        bucketRepository.put(items);
    }

    private BucketItem convert(T v) {
        return new BucketItem(v.getUuid(), systemId, userRepository.retrieveEmail(v.getUserId()), false);
    }

}
