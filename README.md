# userapp

# Script to create the database
create database if not exists userdb;
use userdb;

create table user (
   id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(30) NOT NULL,
   email VARCHAR(100) NOT NULL,
   PRIMARY KEY (ID)
);			   

alter table user
add unique index unique_name_index (name);

commit;
