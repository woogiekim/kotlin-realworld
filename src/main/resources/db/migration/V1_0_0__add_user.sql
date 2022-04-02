create table user
(
    id        bigint not null auto_increment,
    create_at datetime(6),
    email     varchar(255),
    name      varchar(255),
    password  varchar(255),
    primary key (id)
);

alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
