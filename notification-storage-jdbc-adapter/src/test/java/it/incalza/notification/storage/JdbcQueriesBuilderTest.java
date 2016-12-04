package it.incalza.notification.storage;

import it.incalza.notification.bucket.domain.model.NotificationBucketException;
import it.incalza.notification.storage.JdbcQueries.JdbcQueriesBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by sincalza on 04/12/2016.
 */
public class JdbcQueriesBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void build() throws Exception {
        JdbcQueries jdbcQueries = new JdbcQueriesBuilder().withInserQuery("::insertQuery::").build();
        assertThat(jdbcQueries.getInsertQuery(), is("::insertQuery::"));
    }

    @Test
    public void build_insert_query_is_blank() throws Exception {
        expectedException.expect(NotificationBucketException.class);
        new JdbcQueriesBuilder().withInserQuery("         ").build();
    }

}