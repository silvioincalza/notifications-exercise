package it.incalza.notification.bucket.adapter.systemB.repository;

import it.incalza.notification.bucket.adapter.systemA.model.NotificationA;
import it.incalza.notification.bucket.adapter.systemB.model.NotificationB;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.util.Set;

import static java.sql.Types.VARCHAR;
import static java.util.Collections.singletonMap;
import static java.util.stream.Collectors.toSet;

/**
 * Created by sincalza on 04/12/2016.
 */
public class JdbcNotificationBRepository implements NotificationBRepository {
    public static final String INSERT_QUERY = "insert into notification_b(uuid, user_id, message, media, media_type) " +
            "values (:uuid, :userId, :message, :media, :mediaType)";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcNotificationBRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void save(Set<NotificationB> entities) {
        Set<BeanPropertySqlParameterSource> parameterSources = entities.stream().map(this::toSqlParameterSource).collect(toSet());
        jdbcTemplate.batchUpdate(INSERT_QUERY, parameterSources.toArray(new SqlParameterSource[parameterSources.size()]));
    }


    private BeanPropertySqlParameterSource toSqlParameterSource(NotificationB e) {
        BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(e);
        sqlParameterSource.registerSqlType("mediaType", VARCHAR);
        return sqlParameterSource;
    }
}
