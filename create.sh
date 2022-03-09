#!/bin/sh

su postgres -c "psql << EOF
ALTER USER postgres WITH PASSWORD '123';
create database ecommerce;
\c ecommerce;
create table customer(name CHAR(20));
insert into customer values ('Pieter');
EOF"