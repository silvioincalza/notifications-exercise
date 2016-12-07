package it.incalza.notification.bucket.adapter.systemA.repository;

import it.incalza.notification.bucket.adapter.systemA.model.ANotification;
import it.incalza.notification.bucket.domain.DuplicateNotificationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by sincalza on 04/12/2016.
 */
public class JdbcANotificationRepository implements ANotificationRepository {
    public static final String INSERT_QUERY = "insert into notification_a(uuid, user_id, message) " +
            "values (:uuid, :userId, :message)";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcANotificationRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void save(Set<ANotification> entities) {
        try {
            Set<BeanPropertySqlParameterSource> parameterSources = entities.stream().map(BeanPropertySqlParameterSource::new).collect(toSet());
            jdbcTemplate.batchUpdate(INSERT_QUERY, parameterSources.toArray(new SqlParameterSource[parameterSources.size()]));
        } catch (DuplicateKeyException e) {
            throw new DuplicateNotificationException("Already exist a notification", e);
        }
    }


}
