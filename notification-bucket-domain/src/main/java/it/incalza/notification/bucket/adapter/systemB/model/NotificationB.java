package it.incalza.notification.bucket.adapter.systemB.model;

import it.incalza.notification.bucket.domain.Notification;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class NotificationB extends Notification {
    private String message;
    private String media;
    private MediaType mediaType;

    public NotificationB(String userId, String message, String media, MediaType mediaType) {
        super(userId);
        this.message = message;
        this.media = media;
        this.mediaType = mediaType;
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

        NotificationB that = (NotificationB) o;

        return new EqualsBuilder()
                .append(message, that.message)
                .append(media, that.media)
                .append(mediaType, that.mediaType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(message)
                .append(media)
                .append(mediaType)
                .toHashCode();
    }
}
