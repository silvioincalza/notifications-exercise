package it.incalza.notification.storage;

import it.incalza.notification.bucket.domain.model.Notification;
import it.incalza.notification.bucket.domain.model.Notifications;
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
public class JdbcDataStoreTest {

    public static final String INSERT_QUERY = "insert into test_table(uuid) values(:uuid)";
    private JdbcDataStore jdbcDataStore;
    private EmbeddedDatabase dataSource;

    class TestNotification extends Notification {
        public TestNotification() {
        }

    }


    @Before
    public void setUp() {
        dataSource = new EmbeddedDatabaseBuilder().setType(H2).addDefaultScripts().build();
        jdbcDataStore = new JdbcDataStore(dataSource, INSERT_QUERY);
    }

    @After
    public void tearDown() {
        if (dataSource != null)
            dataSource.shutdown();
    }

    @Test
    public void saveACollections() throws Exception {
        jdbcDataStore.save(new Notifications<>(new TestNotification(), new TestNotification(), new TestNotification()));
//        assertThat(new JdbcTemplate)
    }

}