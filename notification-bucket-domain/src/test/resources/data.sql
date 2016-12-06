INSERT INTO notification_bucket (id, notification_id, system_id, alert_email, alert_sent)
VALUES (1, 'cd5286f7-33f6-4ef6-afba-cdc7d4844f21', 'system_a', 'email@gmail.com', 'false');

INSERT INTO notification_bucket (id, notification_id, system_id, alert_email, alert_sent)
VALUES (2, 'a0cc746c-e355-48c7-a876-37f04ddc1c94', 'system_a', 'email@gmail.com', 'false');

INSERT INTO notification_bucket (id, notification_id, system_id, alert_email, alert_sent)
VALUES (3, '5268f8b8-fb3f-45af-80ed-53ba0d3a8ca7', 'system_a', 'email@gmail.com', 'true');

INSERT INTO notification_bucket (id, notification_id, system_id, alert_email, alert_sent)
VALUES (4, 'cd5286f7-33f6-4ef6-afba-cdc7d4844f21', 'system_b', 'incalza.silvio@gmail.com', 'false');

INSERT INTO notification_bucket (id, notification_id, system_id, alert_email, alert_sent)
VALUES (5, '260ac374-53f9-4383-a59c-749e6ca2b2f5', 'system_b', 'incalza.silvio@gmail.com', 'false');

INSERT INTO notification_bucket (id, notification_id, system_id, alert_email, alert_sent)
VALUES (6, 'f6d2570e-5916-4e59-b9db-3e4d2a08561a', 'system_b', 'incalza.silvio@gmail.com', 'false');

INSERT INTO notification_a (uuid, user_id, message)
VALUES ('cd5286f7-33f6-4ef6-afba-cdc7d4844f21', 'email@gmail.com', 'message');

INSERT INTO notification_a (uuid, user_id, message)
VALUES ('a0cc746c-e355-48c7-a876-37f04ddc1c94', 'email@gmail.com', 'message2');

INSERT INTO notification_b (uuid, user_id, message, media, media_type)
VALUES ('cd5286f7-33f6-4ef6-afba-cdc7d4844f21', 'incalza.silvio@gmail.com', 'message', 'http://media.url', 'picture');

INSERT INTO notification_b (uuid, user_id, message, media, media_type)
VALUES ('260ac374-53f9-4383-a59c-749e6ca2b2f5', 'incalza.silvio@gmail.com', 'message2', 'http://media.url', 'picture');
