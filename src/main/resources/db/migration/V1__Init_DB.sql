create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );

create table book (
    id bigint not null,
    author varchar(255),
    descriptions varchar(2048),
    filename varchar(255),
    image_name varchar(255),
    name varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table lesson (
    id bigint not null,
    name varchar(255) not null,
    user_id bigint,
    primary key (id)
) engine=InnoDB;

create table sub_group (
    id bigint not null,
    name varchar(255) not null,
    lesson_id bigint,
    primary key (id)
) engine=InnoDB;

create table sub_group_books (
    book_id bigint not null,
    subgroup_id bigint not null,
    primary key (book_id, subgroup_id)
) engine=InnoDB;

create table user_role (
    user_id bigint not null,
    roles varchar(255)
) engine=InnoDB;

create table usr (
    id bigint not null,
    activation_code varchar(255),
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    fio varchar(255) not null,
    primary key (id)
) engine=InnoDB;

alter table lesson
    add constraint lesson_user_fk
    foreign key (user_id) references usr (id);

alter table sub_group
    add constraint subGroup_lesson_fk
    foreign key (lesson_id) references lesson (id);

alter table sub_group_books
    add constraint subGroup_book_fk
    foreign key (subgroup_id) references book (id);

alter table sub_group_books
    add constraint book_subGroup_fk
    foreign key (book_id) references sub_group (id);

alter table user_role
    add constraint user_role_fk
    foreign key (user_id) references usr (id);