package it.incalza.notification.storage;

import it.incalza.notification.bucket.domain.model.Notification;
import it.incalza.notification.bucket.domain.model.Notifications;
import it.incalza.notification.storage.JdbcQueries.JdbcQueriesBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * Created by sincalza on 04/12/2016.
 */
public class JdbcNotificationRepositoryTest {

    private static final String INITIAL_STRING_UUID = "cd5286f7-33f6-4ef6-afba-cdc7d4844f21";
    public static final String INSERT_QUERY = "insert into test_notification(uuid) values(:uuid)";
    private Impl repository;
    private EmbeddedDatabase dataSource;

    class TestNotification extends Notification {
        public TestNotification() {
        }

    }

    class Impl extends JdbcNotificationSystemRepository<TestNotification> {
        public Impl(DataSource dataSource, JdbcQueries jdbcQueries) {
            super(dataSource, jdbcQueries);
        }
    }

    @Before
    public void setUp() {
        dataSource = new EmbeddedDatabaseBuilder().setType(H2).addDefaultScripts().build();
        repository = new Impl(dataSource, new JdbcQueriesBuilder()
                .withInserQuery(INSERT_QUERY)
                .build());
    }

    @After
    public void tearDown() {
        if (dataSource != null)
            dataSource.shutdown();
    }

    @Test
    public void saveACollections() throws Exception {
        repository.save(new Notifications<>(new TestNotification(), new TestNotification(), new TestNotification()));
    }

}