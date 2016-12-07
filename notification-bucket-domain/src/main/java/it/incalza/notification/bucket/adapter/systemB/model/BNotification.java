package it.incalza.notification.bucket.adapter.systemB.model;

import it.incalza.notification.bucket.domain.Notification;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

import static java.util.UUID.randomUUID;


public class BNotification implements Notification {
    private String userId;
    private String message;
    private String media;
    private MediaType mediaType;
    private UUID uuid;

    public BNotification() {
        uuid = randomUUID();
    }

    public BNotification(String userId, String message, String media, MediaType mediaType) {
        this();
        this.userId = userId;
        this.message = message;
        this.media = media;
        this.mediaType = mediaType;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BNotification that = (BNotification) o;

        return new EqualsBuilder()
                .append(userId, that.userId)
                .append(message, that.message)
                .append(media, that.media)
                .append(mediaType, that.mediaType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(message)
                .append(media)
                .append(mediaType)
                .toHashCode();
    }
}
