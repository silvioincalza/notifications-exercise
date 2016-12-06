package it.incalza.notification.bucket.adapter.jdbc;

import it.incalza.notification.bucket.domain.BucketItem;
import it.incalza.notification.bucket.domain.BucketRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Set;

/**
 * Created by sincalza on 06/12/2016.
 */
public class JdbcBucketRepository implements BucketRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcBucketRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void put(Set<BucketItem> notifications) {
//        this.dataStore
    }

    @Override
    public Map<String, Set<BucketItem>> getNotSent() {
        return null;
    }
}
