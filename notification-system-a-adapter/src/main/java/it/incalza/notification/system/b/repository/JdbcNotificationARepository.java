package it.incalza.notification.system.b.repository;

import it.incalza.notification.bucket.domain.model.DuplicateNotificationException;
import it.incalza.notification.bucket.domain.model.Notifications;
import it.incalza.notification.storage.JdbcNotificationSystemRepository;
import it.incalza.notification.storage.JdbcQueries.JdbcQueriesBuilder;
import it.incalza.notification.system.b.model.NotificationA;
import org.springframework.dao.DuplicateKeyException;

import javax.sql.DataSource;

/**
 * Created by sincalza on 04/12/2016.
 */
public class JdbcNotificationARepository extends JdbcNotificationSystemRepository<NotificationA> implements NotificationARepository {
    public JdbcNotificationARepository(DataSource dataSource) {
        super(dataSource, new JdbcQueriesBuilder()
                .withInserQuery("insert into notification_a(uuid, user_id, message) values (:uuid, :userId, :message)")
                .build());
    }

    @Override
    public void save(Notifications<NotificationA> entities) {
        try {
            super.save(entities);
        } catch (DuplicateKeyException e) {
            throw new DuplicateNotificationException("Already exist a notification", e);
        }
    }
}
