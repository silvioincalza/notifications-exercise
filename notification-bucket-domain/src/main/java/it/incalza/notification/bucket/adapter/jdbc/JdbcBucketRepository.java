package it.incalza.notification.bucket.adapter.jdbc;

import it.incalza.notification.bucket.domain.BucketItem;
import it.incalza.notification.bucket.domain.BucketRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by sincalza on 06/12/2016.
 */
public class JdbcBucketRepository implements BucketRepository {

    private static final String INSERT_QUERY = "insert into notification_bucket(notification_id, system_id, alert_email, alert_sent) values (:notificationId, :systemId, :alertEmail, :alertSent)";
    private static final String MARK_SENT_TRUE_UPDATE_QUERY = "update notification_bucket set alert_sent = true where " +
            "notification_id = :notificationId and system_id = :systemId and alert_email = :alertEmail and alert_sent = false";
    public static final String NOT_SENT_SELECT_QUERY = "select * from notification_bucket where alert_sent = false group by id, alert_email";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcBucketRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void put(Set<BucketItem> entities) {
        jdbcTemplate.batchUpdate(INSERT_QUERY, getParameters(entities));
    }

    @Override
    public Map<String, Set<BucketItem>> getNotSent() {
        Map<String, Set<BucketItem>> items = new HashMap<>();
        List<BucketItem> results = jdbcTemplate.query(NOT_SENT_SELECT_QUERY, rowMapper());
        results.forEach(v -> {
            if (!items.containsKey(v.getAlertEmail())) {
                items.put(v.getAlertEmail(), new HashSet<>());
            }
            items.get(v.getAlertEmail()).add(v);
        });
        return items;
    }

    @Override
    public void markTrueAlertSent(Set<BucketItem> entities) {
        jdbcTemplate.batchUpdate(MARK_SENT_TRUE_UPDATE_QUERY, getParameters(entities));
    }

    private BeanPropertySqlParameterSource[] getParameters(Set<BucketItem> entities) {
        return entities.stream().map(BeanPropertySqlParameterSource::new).toArray(BeanPropertySqlParameterSource[]::new);
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
