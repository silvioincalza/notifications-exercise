package it.incalza.notification.bucket.domain;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class ExampleNotification implements Notification {
    private final String userId;
    private final UUID uuid;

    public ExampleNotification(String userId) {
        this.userId = userId;
        this.uuid = randomUUID();
    }

    public ExampleNotification(UUID uuid, String userId) {
        this.uuid = uuid;
        this.userId = userId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }
}