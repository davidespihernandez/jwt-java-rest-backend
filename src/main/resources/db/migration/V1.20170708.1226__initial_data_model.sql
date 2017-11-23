CREATE TABLE "user"
(
  id                       SERIAL       NOT NULL
    CONSTRAINT users_pkey
    PRIMARY KEY,
  name                     TEXT         NOT NULL,
  email                    VARCHAR(255) NOT NULL,
  password                 VARCHAR(100),
  enabled                  BOOLEAN DEFAULT TRUE,
  last_password_reset_date TIMESTAMP NULL,
  created_date             TIMESTAMP,
  modified_date            TIMESTAMP,
  user_info                JSONB
);

ALTER SEQUENCE user_id_seq RESTART WITH 1000;

CREATE UNIQUE INDEX users_email_uindex
  ON "user" (email);

COMMENT ON TABLE "user" IS 'Users table';

-- authorities
CREATE TABLE authority
(
  id   SERIAL      NOT NULL
    CONSTRAINT authority_pkey
    PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

ALTER SEQUENCE authority_id_seq RESTART WITH 1000;

COMMENT ON TABLE authority IS 'Roles or authorities';

CREATE TABLE user_authority
(
  user_id      BIGINT NOT NULL
    CONSTRAINT fk_user_auth_user REFERENCES "user" (id),
  authority_id BIGINT NOT NULL
    CONSTRAINT fk_user_auth_auth REFERENCES authority (id)
);

ALTER TABLE user_authority
  ADD CONSTRAINT user_authority_pkey
PRIMARY KEY (user_id, authority_id);

CREATE INDEX user_authority_authority_idx
  ON user_authority (authority_id);

CREATE TABLE measure
(
  id      SERIAL  NOT NULL
    CONSTRAINT measure_pkey
    PRIMARY KEY,
  date    DATE    NOT NULL,
  user_id BIGINT  NOT NULL
    CONSTRAINT measure_users_id_fk
    REFERENCES "user",
  total   INTEGER NOT NULL
);

CREATE UNIQUE INDEX measure_user_id_date_uindex
  ON measure (user_id, date);

