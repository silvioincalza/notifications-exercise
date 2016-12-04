package it.incalza.notification.bucket.domain.storage;

import it.incalza.notification.bucket.domain.model.Notification;
import it.incalza.notification.bucket.domain.model.Notifications;

import java.io.Serializable;

/**
 * Created by sincalza on 04/12/2016.
 */
public interface DataStore<T extends Notification> {

    void save(T entity);

    void save(Notifications<T> entities);

}
