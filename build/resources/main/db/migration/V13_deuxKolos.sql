create table if not exists product(
                         id bigint primary key,
                         user_email varchar(255),
                         title varchar(255),
                         description text,
                         price double precision,
                         active boolean
);
