create table address
(
    id_address    INTEGER auto_increment       not null,
    city          varchar(255) not null,
    country       varchar(255) not null,
    street        varchar(255) not null,
    street_number varchar(255) not null,
    zip_code      int4         not null,
    primary key (id_address)
);

create table division
(
    id_division       INTEGER auto_increment           not null,
    director_fullname varchar(255) not null,
    name              varchar(255) not null,
    original_name     varchar(255) not null,
    primary key (id_division)
);

create table users
(
    id                INTEGER auto_increment          not null,
    cellphone_number  varchar(255),
    email             varchar(255),
    home_number       varchar(255),
    first_name        varchar(255) not null,
    last_name         varchar(255) not null,
    license_plate     varchar(255) not null,
    registration_date date         not null,
    fk_user           int8         not null,
    primary key (id)
);

alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKqlif7se6sma4svjik6hbxmf4o foreign key (fk_user) references address;
