create table profile
(
    id       bigint not null auto_increment,
    bio      longtext,
    image    varchar(300),
    username varchar(100),
    primary key (id)
);

alter table user
    add column profile_id bigint;

alter table user
    add constraint FKof44u64o1d7scaukghm9veo23 foreign key (profile_id) references profile (id);
