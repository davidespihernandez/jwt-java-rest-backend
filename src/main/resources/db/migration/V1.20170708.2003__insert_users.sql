INSERT INTO "user" (name, email, user_info, created_date, modified_date)
VALUES ('david', 'davidespihernandez@gmail.com', jsonb_build_object('timezone', 'CET', 'mobile', '666555444'), now(), now());

INSERT INTO "user" (name, email, user_info, created_date, modified_date)
VALUES ('lirios', 'liriosespi@gmail.com', jsonb_build_object('timezone', 'CET', 'mobile', '666555444'), now(), now());
