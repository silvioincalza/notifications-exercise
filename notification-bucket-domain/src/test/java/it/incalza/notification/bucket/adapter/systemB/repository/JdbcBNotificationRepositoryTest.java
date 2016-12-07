package it.incalza.notification.bucket.adapter.systemB.repository;

import it.incalza.notification.bucket.adapter.systemB.model.MediaType;
import it.incalza.notification.bucket.adapter.systemB.model.BNotification;
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
import java.util.Set;

import static it.incalza.notification.bucket.adapter.systemB.model.MediaType.picture;
import static it.incalza.notification.bucket.adapter.systemB.model.MediaType.video;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * Created by sincalza on 05/12/2016.
 */
public class JdbcBNotificationRepositoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private JdbcBNotificationRepository repository;
    private NamedParameterJdbcTemplate jdbcTemplate;
    private EmbeddedDatabase dataSource;

    @Before
    public void setUp() {
        dataSource = new EmbeddedDatabaseBuilder().setType(H2).generateUniqueName(true).addDefaultScripts().build();
        repository = new JdbcBNotificationRepository(dataSource);
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @After
    public void tearDown() {
        if (dataSource != null)
            dataSource.shutdown();
    }

    @Test
    public void save() throws Exception {
        Set<BNotification> entities = new HashSet<>(asList(new BNotification("user1", "text1", "url1", picture),
                new BNotification("user2", "text2", "url2", picture),
                new BNotification("user3", "text3", "url3", video)));
        repository.save(entities);
        assertThat(count("user1", "text1", "url1", picture), is(1L));
        assertThat(count("user2", "text2", "url2", picture), is(1L));
        assertThat(count("user3", "text3", "url3", video), is(1L));
    }

    @Test
    public void notFailedOnDuplication() throws Exception {
        repository.save(new HashSet<>(asList(new BNotification("user1", "text1", "url1", picture))));
        assertThat(count("user1", "text1", "url1", picture), is(1L));
        repository.save(new HashSet<>(asList(new BNotification("user1", "text1", "url1", picture))));
        assertThat(count("user1", "text1", "url1", picture), is(2L));
    }

    public long count(String userId, String message, String media, MediaType mediaType) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("message", message)
                .addValue("media", media)
                .addValue("mediaType", mediaType.name());
        return jdbcTemplate.queryForObject("select count(uuid) from notification_b where user_id = :userId and message = :message and media = :media and media_type = :mediaType", parameterSource, new SingleColumnRowMapper<Long>());
    }

}