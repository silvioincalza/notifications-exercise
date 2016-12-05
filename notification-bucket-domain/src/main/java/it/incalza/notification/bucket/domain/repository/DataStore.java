package it.incalza.notification.bucket.domain.repository;

import it.incalza.notification.bucket.domain.model.Notification;
import it.incalza.notification.bucket.domain.model.Notifications;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by sincalza on 04/12/2016.
 */
public interface DataStore {

    <T extends Serializable> void save(Set<T> entities);

}
