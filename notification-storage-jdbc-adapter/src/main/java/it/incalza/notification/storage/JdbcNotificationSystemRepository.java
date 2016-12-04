package it.incalza.notification.storage;

import it.incalza.notification.bucket.domain.model.Notification;
import it.incalza.notification.bucket.domain.model.Notifications;
import it.incalza.notification.bucket.domain.repository.NotificationRepository;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

/**
 * Created by sincalza on 04/12/2016.
 */
public abstract class JdbcNotificationSystemRepository<T extends Notification> implements NotificationRepository<T> {

    private final NamedParameterJdbcOperations jdbcOperations;
    private final JdbcQueries jdbcQueries;

    public JdbcNotificationSystemRepository(DataSource dataSource, JdbcQueries jdbcQueries) {
        this.jdbcOperations = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcQueries = jdbcQueries;
    }

    @Override
    public void save(Notifications<T> entities) {
        Set<BeanPropertySqlParameterSource> parameterSources = entities.stream().map(BeanPropertySqlParameterSource::new).collect(toSet());
        jdbcOperations.batchUpdate(jdbcQueries.getInsertQuery(), parameterSources.toArray(new BeanPropertySqlParameterSource[parameterSources.size()]));
    }
}
