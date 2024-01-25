drop database if exists mybnb;

create database mybnb;

use mybnb;

create table acc_occupancy (
   acc_id char(10),
   vacancy int,

   primary key(acc_id)
);
create table reservations (
   resv_id char(8) unique not null,
   name varchar(128),
   email varchar(128),
   acc_id char(10),
   arrival_date date,
   duration int,

   primary key(resv_id)
);