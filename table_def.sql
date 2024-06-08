create database if not exists surtidos_app;

create table if not exists data_surtidos(
    surtido_id integer auto_increment,
    username varchar(100) not null,
    audit_date timestamp not null,
    json_products LONGTEXT not null,
    total_price decimal(30, 5) not null,
    audit_date_only_date date default date(audit_date),
    primary key(surtido_id)
);

alter table data_surtidos add unique index unique_user_date(username, audit_date_only_date);

-- Historic:
insert into data_surtidos(username, audit_date, json_products, total_price) values ('Joaquin', '2024-05-10', '[]', 5754);
insert into data_surtidos(username, audit_date, json_products, total_price) values ('Joaquin', '2024-04-10', '[]', 5148);
insert into data_surtidos(username, audit_date, json_products, total_price) values ('Joaquin', '2024-03-10', '[]', 5144);
insert into data_surtidos(username, audit_date, json_products, total_price) values ('Joaquin', '2024-02-10', '[]', 4964);
insert into data_surtidos(username, audit_date, json_products, total_price) values ('Joaquin', '2024-01-10', '[]', 5283);

-- TODO - Buscar en estados de cuenta:
-- insert into data_surtidos(username, audit_date, json_products, total_price) values ('Joaquin', '2023-12-12', '[]', 0);
-- insert into data_surtidos(username, audit_date, json_products, total_price) values ('Joaquin', '2023-12-11', '[]', 0);
-- insert into data_surtidos(username, audit_date, json_products, total_price) values ('Joaquin', '2023-12-10', '[]', 0);
-- insert into data_surtidos(username, audit_date, json_products, total_price) values ('Joaquin', '2023-12-09', '[]', 0);
-- insert into data_surtidos(username, audit_date, json_products, total_price) values ('Joaquin', '2023-12-08', '[]', 0);
