package it.incalza.notification.system.b.repository;

import it.incalza.notification.bucket.domain.model.DuplicateNotificationException;
import it.incalza.notification.bucket.domain.model.Notifications;
import it.incalza.notification.bucket.domain.repository.DataStore;
import it.incalza.notification.storage.JdbcDataStore;
import it.incalza.notification.system.b.model.NotificationA;
import org.springframework.dao.DuplicateKeyException;

import javax.sql.DataSource;

/**
 * Created by sincalza on 04/12/2016.
 */
public class JdbcNotificationARepository  implements NotificationARepository {
    public static final String INSERT_QUERY = "insert into notification_a(uuid, user_id, message) values (:uuid, :userId, :message)";
    private final DataStore jdbcDataStore;

    public JdbcNotificationARepository(DataSource dataSource) {
        this.jdbcDataStore = new JdbcDataStore(dataSource, INSERT_QUERY);
    }

    @Override
    public void save(Notifications<NotificationA> entities) {
        try {
            this.jdbcDataStore.save(entities);
        } catch (DuplicateKeyException e) {
            throw new DuplicateNotificationException("Already exist a notification", e);
        }
    }
}
