create table user
(
    id        bigint not null auto_increment,
    create_at datetime(6),
    email     varchar(100),
    username  varchar(100),
    password  varchar(100),
    primary key (id)
);

alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
