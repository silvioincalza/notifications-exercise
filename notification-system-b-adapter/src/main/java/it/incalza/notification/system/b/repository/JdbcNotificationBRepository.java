package it.incalza.notification.system.b.repository;

import it.incalza.notification.storage.JdbcNotificationSystemRepository;
import it.incalza.notification.storage.JdbcQueries.JdbcQueriesBuilder;
import it.incalza.notification.system.b.model.NotificationB;

import javax.sql.DataSource;

/**
 * Created by sincalza on 04/12/2016.
 */
public class JdbcNotificationBRepository extends JdbcNotificationSystemRepository<NotificationB> implements NotificationBRepository {
    public JdbcNotificationBRepository(DataSource dataSource) {
        super(dataSource, new JdbcQueriesBuilder()
                .withInserQuery("insert into notification_b(uuid, user_id, message, media, media_type) values (:uuid, :userId, :message, :media, :mediaType)")
                .build());
    }


}
