create table blog_user
(
    username varchar(255) not null
        constraint pk_username
            primary key,
    password varchar(255) not null,
    password_salt varchar(255) not null,
    version integer default 0,
    creation_time timestamp default now() not null,
    last_modification_time timestamp
);

alter table blog_user owner to blog_admin;

create table article
(
    id varchar(36) not null
        constraint article_pk
            primary key,
    title text not null,
    content text not null,
    author_id varchar(255) not null
        constraint article_user_username_fk
            references blog_user,
    version integer default 0,
    creation_time timestamp default now() not null,
    last_modification_time timestamp
);

alter table article owner to blog_admin;

create unique index article_id_uindex
    on article (id);

create table role
(
    id text not null
        constraint role_pk
            primary key,
    last_modification_time timestamp,
    creation_time timestamp default now() not null
);

alter table role owner to blog_admin;

create table user_roles
(
    username varchar(255) not null
        constraint fk_username
            references blog_user,
    role text not null
        constraint fk_roles
            references role,
    constraint pk_user_roles
        primary key (username, role)
);

alter table user_roles owner to blog_admin;

create unique index role_id_uindex
    on role (id);

create table permission
(
    id text not null
        constraint permission_pk
            primary key,
    last_modification_time timestamp,
    creation_time timestamp default now() not null
);

alter table permission owner to blog_admin;

create table roles_permissions
(
    role_id text not null
        constraint roles_permissions_role_id_fk
            references role,
    permission_id text not null
        constraint roles_permissions_permission_id_fk
            references permission,
    constraint roles_permissions_pk
        primary key (role_id, permission_id)
);

alter table roles_permissions owner to blog_admin;

create unique index permission_id_uindex
    on permission (id);

