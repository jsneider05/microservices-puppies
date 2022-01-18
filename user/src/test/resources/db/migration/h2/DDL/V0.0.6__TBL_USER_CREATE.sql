create table if not exists public.user
(
    id         varchar(255) not null,
    dob        timestamp    not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    constraint user_pkey
        primary key (id)
);

