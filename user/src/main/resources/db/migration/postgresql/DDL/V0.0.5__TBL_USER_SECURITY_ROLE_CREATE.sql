create table if not exists public.user_security_role
(
    user_security_id varchar(255) not null,
    role_id          varchar(255) not null,
    constraint user_security_role_pkey
        primary key (user_security_id, role_id),
    constraint user_security_role_role_id_fk
        foreign key (role_id) references public.role,
    constraint user_security_role_user_security_id_fk
        foreign key (user_security_id) references public."user_security"
);

