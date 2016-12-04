package it.incalza.notification.storage;

import it.incalza.notification.bucket.domain.support.Assert;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Created by sincalza on 04/12/2016.
 */
public class JdbcQueries {

    private final String insertQuery;

    private JdbcQueries(String insertQuery) {
        this.insertQuery = insertQuery;
    }

    public String getInsertQuery() {
        return insertQuery;
    }

    public static class JdbcQueriesBuilder {

        private String insertQuery;

        public JdbcQueriesBuilder withInserQuery(String insertQuery) {
            this.insertQuery = insertQuery;
            return this;
        }

        public JdbcQueries build() {
            Assert.state(isNotBlank(insertQuery), "Insert query required");
            return new JdbcQueries(insertQuery);
        }
    }
}
