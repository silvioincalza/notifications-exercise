CREATE TABLE notification_a (
  uuid VARCHAR(36) NOT NULL,
  user_id VARCHAR(150) NOT NULL,
  message VARCHAR(256) NOT NULL,
  CONSTRAINT UK_NOTIFICATION_A UNIQUE (user_id, message),
  PRIMARY KEY (uuid)
);

CREATE INDEX IDX_USER_ID ON notification_a(user_id);
CREATE INDEX IDX_MESSAGE ON notification_a(message);