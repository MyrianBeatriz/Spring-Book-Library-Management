drop table if exists product;
create table product(
    id bigint generated by default as identity,
    name varchar(255) not null,
    price numeric(38,2) not null,
    primary key(id)
);