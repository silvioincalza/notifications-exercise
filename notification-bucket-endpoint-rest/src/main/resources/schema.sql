CREATE TABLE notification_bucket (
  id              BIGINT       NOT NULL AUTO_INCREMENT,
  notification_id VARCHAR(36)  NOT NULL,
  system_id       VARCHAR(50)  NOT NULL,
  alert_email     VARCHAR(256) NOT NULL,
  alert_sent      BIT (1) NOT NULL DEFAULT 0,
  CONSTRAINT UK_NOTIFICATION_BUCKET UNIQUE (notification_id, system_id, alert_email),
  PRIMARY KEY (id)
);

CREATE TABLE notification_a (
  uuid    VARCHAR(36)  NOT NULL,
  user_id VARCHAR(150) NOT NULL,
  message VARCHAR(256) NOT NULL,
  CONSTRAINT UK_NOTIFICATION_A UNIQUE (user_id, message),
  PRIMARY KEY (uuid)
);

CREATE INDEX IDX_USER_ID_A
  ON notification_a (user_id);
CREATE INDEX IDX_MESSAGE_A
  ON notification_a (message);

CREATE TABLE notification_b (
  uuid       VARCHAR(36)  NOT NULL,
  user_id    VARCHAR(150) NOT NULL,
  message    VARCHAR(256) NOT NULL,
  media      TEXT,
  media_type VARCHAR(10),
  PRIMARY KEY (uuid)
);

CREATE INDEX IDX_USER_ID_B
  ON notification_b (user_id);
CREATE INDEX IDX_MESSAGE_B
  ON notification_b (message);


