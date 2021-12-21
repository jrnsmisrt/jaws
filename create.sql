create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, fk_user int8 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date not null, primary key (id));
alter table if exists division add constraint UK_gy8f7i7c6a76y0h4jk3e83kgl unique (director_fullname);
alter table if exists address add constraint FKsgdc52gx5lvfb3xy9yevvtvu8 foreign key (fk_user) references users;
