package it.incalza.notification.system.b.model;

import it.incalza.notification.bucket.domain.model.Notification;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class NotificationB extends Notification {

    private String userId;
    private String message;
    private String media;
    private String mediaType;

    public NotificationB() {
    }

    public NotificationB(String userId, String message, String media, MediaType mediaType) {
        this.userId = userId;
        this.message = message;
        this.media = media;
        this.mediaType = mediaType.name();
    }

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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType.name();
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        NotificationB that = (NotificationB) o;

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
