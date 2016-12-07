package it.incalza.notification.bucket.adapter.systemA.repository;

import it.incalza.notification.bucket.adapter.systemA.model.ANotification;
import it.incalza.notification.bucket.domain.DuplicateNotificationException;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * Created by sincalza on 05/12/2016.
 */
public class JdbcANotificationRepositoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private JdbcANotificationRepository repository;
    private NamedParameterJdbcTemplate jdbcTemplate;
    private EmbeddedDatabase dataSource;

    @Before
    public void setUp() {
        dataSource = new EmbeddedDatabaseBuilder().setType(H2).generateUniqueName(true).addDefaultScripts().build();
        repository = new JdbcANotificationRepository(dataSource);
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @After
    public void tearDown() {
        if (dataSource != null)
            dataSource.shutdown();
    }


    @Test
    public void save() throws Exception {
        Set<ANotification> entities = new HashSet<>(asList(new ANotification("user1", "text1"),
                new ANotification("user2", "text2"),
                new ANotification("user3", "text3")));
        repository.save(entities);
        assertExist("user1", "text1");
        assertExist("user2", "text2");
        assertExist("user3", "text3");
    }

    @Test
    public void failedOnDuplication() throws Exception {
        expectedException.expect(DuplicateNotificationException.class);
        repository.save(new HashSet<>(asList(new ANotification("user1", "text1"))));
        assertExist("user1", "text1");
        repository.save(new HashSet<>(asList(new ANotification("user1", "text1"))));
    }

    public void assertExist(String userId, String message) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("userId", userId).addValue("message", message);
        Long count = jdbcTemplate.queryForObject("select count(uuid) from notification_a where user_id = :userId and message = :message", parameterSource, new SingleColumnRowMapper<Long>());
        Assert.assertThat(count, is(1L));
    }

}