package it.incalza.notification.bucket.domain;

import java.util.Map;
import java.util.Set;

import static java.lang.String.format;

/**
 * Created by sincalza on 06/12/2016.
 */
public class NotifyByEmail implements NotifierCommand {

    private final BucketRepository bucketRepository;
    private final EmailProvider emailProvider;

    public NotifyByEmail(BucketRepository bucketRepository, EmailProvider emailProvider) {
        this.bucketRepository = bucketRepository;
        this.emailProvider = emailProvider;
    }

    @Override
    public void onNotify() {
        Map<String, Set<BucketItem>> notSent = bucketRepository.getNotSent();
        notSent.forEach((k, bucketItems) -> emailProvider.send(k, format("You have received %1s notification", bucketItems.size())));
    }
}
