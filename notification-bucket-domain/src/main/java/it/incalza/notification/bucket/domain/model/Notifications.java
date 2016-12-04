package it.incalza.notification.bucket.domain.model;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;

/**
 * Created by sincalza on 04/12/2016.
 */
public class Notifications<T extends Notification> extends HashSet<T> {

    public Notifications(T... notifications) {
        super(asList(notifications));
    }

}
