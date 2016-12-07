package it.incalza.notification.bucket.adapter;

import it.incalza.notification.bucket.domain.UserRepository;

/**
 * Created by sincalza on 06/12/2016.
 */
public class SimpleUserRepository implements UserRepository {
    @Override
    public String retrieveEmail(String userId) {
        return userId;
    }
}
