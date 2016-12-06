package it.incalza.notification.bucket.domain;

import java.util.Map;
import java.util.Set;

/**
 * Created by sincalza on 04/12/2016.
 */
public interface BucketRepository {

    void put(Set<BucketItem> notifications);

    Map<String, Set<BucketItem>> getNotSent();
}
