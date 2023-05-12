create table if not exists dyshajob (
                    id serial primary key,
                    title varchar(255),
                    description text,
                    posted_date timestamp default current_timestamp,
                    employeur varchar(255),
                    location varchar(500),
                    user_id integer

);

create table if not exists dyshaworker (
                    id serial primary key,
                    name varchar(255),
                    description text,
                    location varchar (500),
                    started_on timestamp default current_timestamp,
                    user_id integer
    );

create table if not exists workerjobrelation (
                    id serial primary key,
                    job_id INTEGER ,
                    worker_id INTEGER ,
                    started_on timestamp default current_timestamp
    );

CREATE TABLE dysha_file (
                        id serial primary key,
                        user_id INTEGER,
                        entity_id INTEGER,
                        table_name varchar (500),
                        file_type varchar,
                        file bytea,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);
