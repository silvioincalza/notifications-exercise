package it.incalza.notification.bucket;

import it.incalza.notification.bucket.adapter.SimpleUserRepository;
import it.incalza.notification.bucket.adapter.systemA.ANotificationDefaultReceiver;
import it.incalza.notification.bucket.adapter.systemA.repository.JdbcANotificationRepository;
import it.incalza.notification.bucket.adapter.systemB.BNotificationDefaultReceiver;
import it.incalza.notification.bucket.adapter.systemB.repository.BNotificationRepository;
import it.incalza.notification.bucket.adapter.systemB.repository.JdbcBNotificationRepository;
import it.incalza.notification.bucket.domain.EmailProvider;
import it.incalza.notification.bucket.adapter.jdbc.JdbcBucketRepository;
import it.incalza.notification.bucket.adapter.systemA.repository.ANotificationRepository;
import it.incalza.notification.bucket.domain.BucketRepository;
import it.incalza.notification.bucket.domain.NotifyByEmail;
import it.incalza.notification.bucket.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static java.lang.String.format;

/**
 * Created by sincalza on 06/12/2016.
 */
@Configuration
public class JdbcBucketConfig {

    private static final Logger logger = LoggerFactory.getLogger(JdbcBucketConfig.class);

    @Bean
    public ANotificationRepository aNotificationRepository(DataSource dataSource) {
        return new JdbcANotificationRepository(dataSource);
    }

    @Bean
    public BNotificationRepository bNotificationRepository(DataSource dataSource) {
        return new JdbcBNotificationRepository(dataSource);
    }

    @Bean
    public BucketRepository bucketRepository(DataSource dataSource) {
        return new JdbcBucketRepository(dataSource);
    }

    @Bean
    public UserRepository userRepository() {
        return new SimpleUserRepository();
    }

    @Bean
    public ANotificationDefaultReceiver aNotificationDefaultReceiver(ANotificationRepository aNotificationRepository, BucketRepository bucketRepository, UserRepository userRepository) {
        return new ANotificationDefaultReceiver(aNotificationRepository, bucketRepository, userRepository);
    }

    @Bean
    public BNotificationDefaultReceiver bNotificationDefaultReceiver(BNotificationRepository bNotificationRepository, BucketRepository bucketRepository, UserRepository userRepository) {
        return new BNotificationDefaultReceiver(bNotificationRepository, bucketRepository, userRepository);
    }

    @Bean
    public EmailProvider emailProvider() {
        return (email, content) -> logger.error("SEND EMAIL NOT IMPLEMENTED NOW !!! emailToBeSend: {} content: {}", email, content);
    }

    @Bean
    public NotifyByEmail notifyByEmail(BucketRepository bucketRepository, EmailProvider emailProvider) {
        return new NotifyByEmail(bucketRepository, emailProvider);
    }

}
