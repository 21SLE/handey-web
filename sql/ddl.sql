drop table if exists todo_box CASCADE;
create table todo_box
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    title varchar(255),
    notitle boolean default 0,
    fixed boolean default 0,
    user_id bigint not null,
    primary key (id),
    foreign key (user_id) references join_mem(id) on delete cascade
);

drop table if exists todo_elm CASCADE;
create table todo_elm
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    content varchar(255),
    completed boolean default 0,
    to_do_box_id bigint not null,
    primary key (id),
    foreign key (to_do_box_id) references todo_box(id) on delete cascade
);

drop table if exists weekly_elm CASCADE;
create table weekly_elm
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    content varchar(255),
    completed boolean default 0,
    weekly_box_id bigint not null,
    primary key (id),
    foreign key (weekly_box_id) references weekly_box(id) on delete cascade
);

drop table if exists join_mem CASCADE;
create table join_mem
(
     id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
     username varchar(255),
     email varchar(255),
     password varchar(255),
     primary key(id)
);


drop table if exists memo CASCADE;
create table memo
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    content varchar(2000),
    user_id bigint not null,
    primary key (id),
    foreign key (user_id) references join_mem(id) on delete cascade
);

drop table if exists todo_box_hst CASCADE;
create table todo_box_hst
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    title varchar(255),
    saveDt date,
    user_id bigint not null,
    primary key (id),
    foreign key (user_id) references join_mem(id) on delete cascade
);

drop table if exists todo_elm_hst CASCADE;
create table todo_elm_hst
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    content varchar(255),
    todo_box_hst_id bigint not null,
    primary key (id),
    foreign key (todo_box_hst_id) references todo_box_hst(id) on delete cascade
);

drop table if exists trash_box CASCADE;
create table trash_box
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    title varchar(255),
    notitle boolean,
    registerDt date,
    endDt date,
    user_id bigint not null,
    primary key (id),
    foreign key (user_id) references join_mem(id) on delete cascade
);

drop table if exists trash CASCADE;
create table trash_elm
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    content varchar(255),
    completed boolean,
    trash_box_id bigint not null,
    primary key (id),
    foreign key (trash_box_id) references trash_box(id) on delete cascade
);

drop table if exists user_info CASCADE;
create table user_info
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    reset_time varchar(2) default '0',
    theme varchar(1) default '1',
    user_id bigint not null,
    primary key (id),
    foreign key (user_id) references join_mem(id) on delete cascade
);
