package it.incalza.notification.bucket.domain.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by sincalza on 04/12/2016.
 */
public abstract class Notification implements Serializable {

    private final UUID uuid;

    public Notification() {
        this.uuid = UUID.randomUUID();
    }

    public Notification(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
