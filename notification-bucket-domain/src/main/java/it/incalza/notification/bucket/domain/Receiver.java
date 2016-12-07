package it.incalza.notification.bucket.domain;

import java.util.Set;

/**
 * Created by sincalza on 06/12/2016.
 */
public interface Receiver<T extends Notification> {
    void onReceive(Set<T> notifications);

    String systemId();
}
