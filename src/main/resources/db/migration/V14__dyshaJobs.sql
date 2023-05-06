create table if not exists dyshajob (
                    id serial primary key,
                    title varchar(255),
                    description text,
                    posted_date timestamp default current_timestamp,
                    employeur varchar(255),
                    location varchar(500)
    );

create table if not exists dyshaworker (
                    id serial primary key,
                    name varchar(255),
                    description text,
                    location varchar (500),
                    started_on timestamp default current_timestamp
    );

create table if not exists workerjobrelation (
                    id serial primary key,
                    job_id INTEGER ,
                    worker_id INTEGER ,
                    stated_at timestamp default current_timestamp
    );