package it.incalza.notification.storage;

import it.incalza.notification.bucket.domain.repository.DataStore;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toSet;

/**
 * Created by sincalza on 05/12/2016.
 */
public class JdbcDataStore implements DataStore {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final String insertQuery;
    private final Map<String, Integer> registerParametersSqlType;

    public JdbcDataStore(DataSource dataSource, String insertQuery) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertQuery = insertQuery;
        this.registerParametersSqlType = emptyMap();
    }

    public JdbcDataStore(DataSource dataSource, String insertQuery, Map<String, Integer> registerParametersSqlType) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertQuery = insertQuery;
        this.registerParametersSqlType = registerParametersSqlType;
    }

    @Override
    public <T extends Serializable> void save(Set<T> entities) {
        Set<SqlParameterSource> parameterSources = entities.stream().map(e -> toSqlParameterSource(e)).collect(toSet());
        jdbcTemplate.batchUpdate(insertQuery, parameterSources.toArray(new SqlParameterSource[parameterSources.size()]));
    }

    private <T extends Serializable> BeanPropertySqlParameterSource toSqlParameterSource(T e) {
        BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(e);
        registerParametersSqlType.forEach((k, v) -> sqlParameterSource.registerSqlType(k, v));
        return sqlParameterSource;
    }
}
