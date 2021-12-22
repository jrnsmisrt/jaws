create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date not null, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date not null, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address bigint generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code integer not null, primary key (id_address));
create table division (id_division bigint generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id bigint generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id bigint not null, primary key (id));
alter table division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address bigint generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code integer not null, primary key (id_address));
create table division (id_division bigint generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id bigint generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id bigint not null, primary key (id));
alter table division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address bigint generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code integer not null, primary key (id_address));
create table division (id_division bigint generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id bigint generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id bigint not null, primary key (id));
alter table division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address int8 generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code int4 not null, primary key (id_address));
create table division (id_division int8 generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id int8 generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id int8 not null, primary key (id));
alter table if exists division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table if exists users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address bigint generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code integer not null, primary key (id_address));
create table division (id_division bigint generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id bigint generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id bigint not null, primary key (id));
alter table division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address bigint generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code integer not null, primary key (id_address));
create table division (id_division bigint generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id bigint generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id bigint not null, primary key (id));
alter table division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address bigint generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code integer not null, primary key (id_address));
create table division (id_division bigint generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id bigint generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id bigint not null, primary key (id));
alter table division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address bigint generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code integer not null, primary key (id_address));
create table division (id_division bigint generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id bigint generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id bigint not null, primary key (id));
alter table division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address bigint generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code integer not null, primary key (id_address));
create table division (id_division bigint generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id bigint generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id bigint not null, primary key (id));
alter table division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
create table address (id_address bigint generated by default as identity, city varchar(255) not null, country varchar(255) not null, street varchar(255) not null, street_number varchar(255) not null, zip_code integer not null, primary key (id_address));
create table division (id_division bigint generated by default as identity, director_fullname varchar(255) not null, name varchar(255) not null, original_name varchar(255) not null, primary key (id_division));
create table users (id bigint generated by default as identity, cellphone_number varchar(255), email varchar(255), home_number varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, license_plate varchar(255) not null, registration_date date, fk_address_id bigint not null, primary key (id));
alter table division add constraint UK_bw4owjjddobaevrmh4o67vaho unique (name);
alter table users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address;
