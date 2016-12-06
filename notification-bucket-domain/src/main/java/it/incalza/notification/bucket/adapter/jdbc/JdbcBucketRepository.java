package it.incalza.notification.bucket.adapter.jdbc;

import it.incalza.notification.bucket.domain.BucketItem;
import it.incalza.notification.bucket.domain.BucketRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * Created by sincalza on 06/12/2016.
 */
public class JdbcBucketRepository implements BucketRepository {

    private static final String INSERT_QUERY = "insert into notificaiton_bucket(notification_id, system_id, alert_email, alert_sent) values (:notificationId, :systemId, :alertEmail, :alertSent)";
    private static final String MARK_SENT_TRUE_UPDATE_QUERY = "update notification_bucket set alert_sent = true where " +
            "notification_id = :notificationId and system_id = :systemId and alert_email = :alertEmail and alert_sent = false";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcBucketRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void put(Set<BucketItem> entities) {
        Set<BeanPropertySqlParameterSource> parameterSources = entities.stream().map(BeanPropertySqlParameterSource::new).collect(toSet());
        jdbcTemplate.batchUpdate(INSERT_QUERY, parameterSources.toArray(new SqlParameterSource[parameterSources.size()]));
    }

    @Override
    public Map<String, Set<BucketItem>> getNotSent() {
        Map<String, Set<BucketItem>> items = new HashMap<>();
        List<BucketItem> results = jdbcTemplate.query("select * from notification_bucket where alert_sent = false group by alert_email", rowMapper());
        results.stream().map(v -> items.getOrDefault(v.getAlertEmail(), new HashSet<>()).add(v));
        return items;
    }


    @Override
    public void markTrueAlertSent(Set<BucketItem> entities) {
        Set<BeanPropertySqlParameterSource> parameterSources = entities.stream().map(BeanPropertySqlParameterSource::new).collect(toSet());
        jdbcTemplate.batchUpdate(MARK_SENT_TRUE_UPDATE_QUERY, parameterSources.toArray(new SqlParameterSource[parameterSources.size()]));
    }


    private RowMapper<BucketItem> rowMapper() {
        return (rs, rowNum) -> {
            UUID notificationId = UUID.fromString(rs.getString("notification_id"));
            String systemId = rs.getString("system_id");
            String alertEmail = rs.getString("alert_email");
            boolean alertSent = rs.getBoolean("alert_sent");
            return new BucketItem(notificationId, systemId, alertEmail, alertSent);
        };
    }

}