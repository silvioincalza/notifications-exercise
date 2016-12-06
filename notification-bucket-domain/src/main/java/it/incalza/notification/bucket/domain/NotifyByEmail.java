package it.incalza.notification.bucket.domain;

import java.util.Map;
import java.util.Set;

/**
 * Created by sincalza on 06/12/2016.
 */
public class NotifyByEmail implements NotifierCommand {

    private final BucketRepository bucketRepository;

    public NotifyByEmail(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    @Override
    public void onNotify() {
        Map<String, Set<BucketItem>> notSent = bucketRepository.getNotSent();


    }
}
