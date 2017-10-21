create table "user"
(
  id serial not null
    constraint users_pkey
    primary key,
  name text not null,
  email varchar(255) not null,
  user_info jsonb
)
;

create unique index users_email_uindex
  on "user" (email)
;

comment on table "user" is 'Users table'
;


-- auto-generated definition
create table measure
(
  id serial not null
    constraint measure_pkey
    primary key,
  date date not null,
  user_id bigint not null
    constraint measure_users_id_fk
    references "user",
  total integer not null
)
;

create unique index measure_user_id_date_uindex
  on measure (user_id, date)
;

