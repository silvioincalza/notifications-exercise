CREATE TABLE notification_a (
  uuid VARCHAR(36) NOT NULL,
  user_id VARCHAR(150) NOT NULL,
  message VARCHAR(256) NOT NULL,
  CONSTRAINT UK_NOTIFICATION_A UNIQUE (user_id, message),
  PRIMARY KEY (uuid)
);

CREATE INDEX IDX_USER_ID_A ON notification_a(user_id);
CREATE INDEX IDX_MESSAGE_A ON notification_a(message);

CREATE TABLE notification_b (
  uuid VARCHAR(36) NOT NULL,
  user_id VARCHAR(150) NOT NULL,
  message VARCHAR(256) NOT NULL,
  media TEXT,
  media_type VARCHAR(10),
  PRIMARY KEY (uuid)
);

CREATE INDEX IDX_USER_ID_B ON notification_b(user_id);
CREATE INDEX IDX_MESSAGE_B ON notification_b(message);


