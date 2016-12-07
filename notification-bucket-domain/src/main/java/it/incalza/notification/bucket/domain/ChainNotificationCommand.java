package it.incalza.notification.bucket.domain;

import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Created by sincalza on 06/12/2016.
 */
public class ChainNotificationCommand<T extends Notification> implements NotificationCommand<T> {

    private final List<NotificationCommand<T>> commands;

    public ChainNotificationCommand(NotificationCommand<T>... commands) {
        this.commands = asList(commands);
    }

    @Override
    public void onNotifications(Set<T> notifications) {
        for (NotificationCommand<T> command : commands) {
            command.onNotifications(notifications);
        }
    }
}
