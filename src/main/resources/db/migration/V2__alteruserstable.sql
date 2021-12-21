Alter table users
 add column fk_address_id bigint;
Alter table users
drop column if exists fk_user;

