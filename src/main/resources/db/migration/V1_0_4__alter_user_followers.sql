create table user_followers
(
    user_id      bigint not null,
    followers_id bigint not null
);

alter table user_followers
    add constraint FKpvkdr9tjpc96kdwe7591oixnj foreign key (followers_id) references user (id);

alter table user_followers
    add constraint FKokc5w6fibhnthvwnxjxyrlfc1 foreign key (user_id) references user (id);
