create table if not exists users
(
    id         serial primary key,
    first_name varchar(60)         not null,
    last_name  varchar(60)         not null,
    email      varchar(100) unique not null,
    password   varchar(200)        not null,
    locked     boolean,
    enabled    boolean,
    role       varchar(40)         not null
);

create table if not exists archiv
(
    id         serial primary key,
    first_name varchar(60)         not null,
    last_name  varchar(60)         not null,
    email      varchar(100) unique not null,
    password   varchar(200)        not null
);

create table if not exists confirmation_token
(
    id           serial primary key,
    token        varchar(200) unique not null,
    created_at   varchar(20)         not null,
    expired_at   varchar(20)         not null,
    confirmed_at varchar(20),
    username     varchar(100) unique not null
);

create table if not exists product
(
                         id serial primary key,
                         user_id integer,
                         user_email varchar(255),
                         title varchar(255),
                         description text,
                         price double precision,
                         vente boolean,
                         active boolean,
                         cathegorie varchar(255),
                         region varchar(255) not null,
                         ville varchar(255),
                         quartier varchar(255),
                         images text[],
                         created_at timestamp default current_timestamp,
                         updated_at timestamp default current_timestamp
);

create table if not exists chatmessage (
                          id serial primary key,
                          sender varchar(255),
                          receiver varchar(255),
                          sujet varchar(255),
                          message text,
                          discussion_hash text,
                          created_at timestamp default current_timestamp
);

create  table if not exists discussion (
                        id serial primary key,
                        discussion_hash text,
                        created_at timestamp default now()
);

create table if not exists dysha_files (
                                           id serial primary key,
                                           name varchar(255) not null,
                                           data bytea not null
);