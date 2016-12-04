package it.incalza.notification.system.b.model;

import it.incalza.notification.bucket.domain.model.DuplicateNotificationException;
import it.incalza.notification.bucket.domain.model.Notifications;
import it.incalza.notification.system.b.repository.JdbcNotificationARepository;
import org.hamcrest.core.Is;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;


public class JdbcNotificationARepositoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private JdbcNotificationARepository repository;
    private NamedParameterJdbcTemplate jdbcTemplate;
    private EmbeddedDatabase dataSource;

    @Before
    public void setUp() {
        dataSource = new EmbeddedDatabaseBuilder().setType(H2).generateUniqueName(true).addDefaultScripts().build();
        repository = new JdbcNotificationARepository(dataSource);
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @After
    public void tearDown() {
        if (dataSource != null)
            dataSource.shutdown();
    }


    @Test
    public void save() throws Exception {
        Notifications<NotificationA> entities = new Notifications<>(new NotificationA("user1", "text1"),
                new NotificationA("user2", "text2"),
                new NotificationA("user3", "text3"));
        repository.save(entities);
        assertExist("user1", "text1");
        assertExist("user2", "text2");
        assertExist("user3", "text3");
    }


    @Test
    public void failedOnDuplication() throws Exception {
        expectedException.expect(DuplicateNotificationException.class);
        repository.save(new Notifications<>(new NotificationA("user1", "text1")));
        assertExist("user1", "text1");
        repository.save(new Notifications<>(new NotificationA("user1", "text1")));

    }

    public void assertExist(String userId, String message) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("userId", userId).addValue("message", message);
        Long count = jdbcTemplate.queryForObject("select count(uuid) from notification_a where user_id = :userId and message = :message", parameterSource, new SingleColumnRowMapper<Long>());
        Assert.assertThat(count, is(1L));
    }

}