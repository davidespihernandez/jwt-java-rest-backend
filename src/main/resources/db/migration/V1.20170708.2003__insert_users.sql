INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_ADMIN');

--calculate password hashes using https://bcrypt-generator.com/

-- david:david
INSERT INTO "user" (id, name, password, email, user_info, created_date, modified_date)
VALUES (1, 'david', '$2a$04$cFR4k1FmdH.VO6iSwGqi5O6EGzgxoRmOydEHxd4HAFtkKMkGoma1m', 'davidespihernandez@gmail.com', jsonb_build_object('timezone', 'CET', 'mobile', '666555444'), now(), now());

-- lirios:lirios
INSERT INTO "user" (id, name, password, email, user_info, created_date, modified_date)
VALUES (2, 'lirios', '$2a$04$4oGoNnZJYrECNPkaJNwB9.zDnOMuCzRGZ0vU0aGWeWqTaWxrANbIS', 'liriosespi@gmail.com', jsonb_build_object('timezone', 'CET', 'mobile', '666555444'), now(), now());

insert into user_authority(user_id, authority_id)
    VALUES (1, 1);

insert into user_authority(user_id, authority_id)
VALUES (1, 2);

--lirios not admin
insert into user_authority(user_id, authority_id)
VALUES (2, 1);
