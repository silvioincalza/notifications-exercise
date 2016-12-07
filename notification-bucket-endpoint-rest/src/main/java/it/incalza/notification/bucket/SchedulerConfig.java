package it.incalza.notification.bucket;

import it.incalza.notification.bucket.domain.NotifyByEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by sincalza on 07/12/2016.
 */
@Configuration
public class SchedulerConfig {

    @Autowired
    private NotifyByEmail notifyByEmail;

    @Scheduled(fixedDelayString = "${notifyByEmail.scheduler.fixedDelay}")
    public void schedulerNotifyByEmail() {
        notifyByEmail.onNotify();
    }
}
