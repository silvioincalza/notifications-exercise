package it.incalza.notification.bucket.domain;

/**
 * Created by sincalza on 06/12/2016.
 */
public interface EmailProvider {

    void send(String email, String content);

}
