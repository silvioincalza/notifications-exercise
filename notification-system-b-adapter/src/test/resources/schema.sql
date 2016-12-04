CREATE TABLE notification_b (
  uuid VARCHAR(36) NOT NULL,
  user_id VARCHAR(150) NOT NULL,
  message VARCHAR(256) NOT NULL,
  media TEXT,
  media_type VARCHAR(10),
  PRIMARY KEY (uuid)
);

CREATE INDEX IDX_USER_ID ON notification_b(user_id);
CREATE INDEX IDX_MESSAGE ON notification_b(message);