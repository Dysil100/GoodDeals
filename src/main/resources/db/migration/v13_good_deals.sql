create table if not exists product
(
                         id serial primary key,
                         user_email varchar(255),
                         title varchar(255),
                         description text,
                         price double precision,
                         vente boolean,
                         active boolean,
                         city varchar(255) not null,
                         quartier varchar(255),
                         image varchar(255),
                         created_at timestamp default now(),
                         updated_at timestamp default now()
);

create table if not exists chatmessage (
                          id serial primary key,
                          sender varchar(255),
                          receiver varchar(255),
                          message text,
                          created_at timestamp default current_timestamp
);
