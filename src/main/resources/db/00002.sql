create table if not exists article_comment
(
    id                     char(36)                not null
        constraint article_comment_pk
            primary key,
    content                text                    not null,
    author_id              varchar(255)            not null
        constraint article_comment_user_username_fk
            references blog_user,
    version                integer   default 0,
    creation_time          timestamp with time zone default now() not null,
    last_modification_time timestamp with time zone
);

alter table article_comment
    owner to blog_admin;

create unique index if not exists article_comment_id_uindex
    on article_comment (id);

alter table article
    alter column creation_time type timestamp with time zone using creation_time:: timestamp with time zone;

alter table article
    alter column last_modification_time type timestamp with time zone using last_modification_time:: timestamp with time zone;

alter table permission
    alter column last_modification_time type timestamp with time zone using last_modification_time:: timestamp with time zone;

alter table permission
    alter column creation_time type timestamp with time zone using creation_time:: timestamp with time zone;

alter table blog_user
    alter column last_modification_time type timestamp with time zone using last_modification_time:: timestamp with time zone;

alter table blog_user
    alter column creation_time type timestamp with time zone using creation_time:: timestamp with time zone;


alter table role
    alter column creation_time type timestamp with time zone using creation_time:: timestamp with time zone;

alter table role
    alter column last_modification_time type timestamp with time zone using last_modification_time:: timestamp with time zone;
