Alter table users
   ADD CONSTRAINT fk_address_id FOREIGN KEY(fk_address_id) REFERENCES address(id_address)