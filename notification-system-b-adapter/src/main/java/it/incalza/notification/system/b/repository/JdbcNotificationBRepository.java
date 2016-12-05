package it.incalza.notification.system.b.repository;

import it.incalza.notification.bucket.domain.model.Notifications;
import it.incalza.notification.bucket.domain.repository.DataStore;
import it.incalza.notification.storage.JdbcDataStore;
import it.incalza.notification.system.b.model.NotificationB;

import javax.sql.DataSource;

import static java.sql.Types.VARCHAR;
import static java.util.Collections.singletonMap;

/**
 * Created by sincalza on 04/12/2016.
 */
public class JdbcNotificationBRepository implements NotificationBRepository {
    public static final String INSERT_QUERY = "insert into notification_b(uuid, user_id, message, media, media_type) values (:uuid, :userId, :message, :media, :mediaType)";
    private final DataStore jdbcDataStore;

    public JdbcNotificationBRepository(DataSource dataSource) {
        this.jdbcDataStore = new JdbcDataStore(dataSource, INSERT_QUERY, singletonMap("media_type", VARCHAR));
    }

    @Override
    public void save(Notifications<NotificationB> entities) {
        this.jdbcDataStore.save(entities);
    }

}
