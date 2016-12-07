package it.incalza.notification.bucket.adapter.jdbc;

import it.incalza.notification.bucket.domain.BucketItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.UUID.randomUUID;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * Created by sincalza on 06/12/2016.
 */
public class JdbcBucketRepositoryTest {


    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private JdbcBucketRepository repository;
    private NamedParameterJdbcTemplate jdbcTemplate;
    private EmbeddedDatabase dataSource;

    @Before
    public void setUp() {
        dataSource = new EmbeddedDatabaseBuilder().setType(H2).generateUniqueName(true).addDefaultScripts().build();
        repository = new JdbcBucketRepository(dataSource);
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @After
    public void tearDown() {
        if (dataSource != null)
            dataSource.shutdown();
    }

    @Test
    public void put() throws Exception {
        repository.put(items(new BucketItem(randomUUID(), "SYSTEM", "EMAIL", false),
                new BucketItem(randomUUID(), "SYSTEM", "EMAIL", false)));
        assertCount("SYSTEM", "EMAIL", 2);
    }

    @Test
    public void getNotSent() throws Exception {
        repository.put(items(new BucketItem(randomUUID(), "SYSTEM", "EMAIL", false),
                new BucketItem(randomUUID(), "SYSTEM", "EMAIL", false),
                new BucketItem(randomUUID(), "SYSTEM", "EMAIL2", false),
                new BucketItem(randomUUID(), "SYSTEM", "EMAIL2", true)));
        Map<String, Set<BucketItem>> notSent = repository.getNotSent();
        assertThat(notSent.containsKey("EMAIL"), is(true));
        assertThat(notSent.containsKey("EMAIL2"), is(true));
        assertThat(notSent.get("EMAIL").size(), is(2));
        assertThat(notSent.get("EMAIL2").size(), is(1));
    }

    @Test
    public void markTrueAlertSent() {
        Set<BucketItem> items = items(new BucketItem(randomUUID(), "SYSTEM", "EMAIL", false));
        repository.put(items);
        repository.markTrueAlertSent(items);
        assertAlertSentIsTrue("SYSTEM", "EMAIL");
    }

    private void assertCount(String system, String email, long expectedCount) {
        long count = jdbcTemplate.queryForObject("select count(id) from notification_bucket where alert_email = :alertEmail and system_id = :systemId",
                new MapSqlParameterSource().addValue("alertEmail", email).addValue("systemId", system),
                new SingleColumnRowMapper<Long>(Long.class));
        assertThat(count, is(expectedCount));
    }

    private void assertAlertSentIsTrue(String system, String email) {
        long count = jdbcTemplate.queryForObject("select count(id) from notification_bucket where alert_email = :alertEmail and system_id = :systemId and alert_sent = true",
                new MapSqlParameterSource().addValue("alertEmail", email).addValue("systemId", system),
                new SingleColumnRowMapper<Long>(Long.class));
        assertThat(count, is(1L));
    }

    private Set<BucketItem> items(BucketItem... bucketItems) {
        return new HashSet<>(asList(bucketItems));
    }

}