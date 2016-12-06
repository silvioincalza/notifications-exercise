package it.incalza.notification.bucket.domain;

import java.util.Map;
import java.util.Set;

/**
 * Created by sincalza on 04/12/2016.
 */
public interface BucketRepository {

    void put(Set<BucketItem> entities);

    Map<String, Set<BucketItem>> getNotSent();

    void markTrueAlertSent(Set<BucketItem> entities);
}
