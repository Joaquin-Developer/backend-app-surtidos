create database if not exists surtidos_app;

create table if not exists data_surtidos(
    surtido_id integer auto_increment,
    username varchar(100) not null,
    audit_date timestamp not null,
    json_products LONGTEXT not null,
    total_price decimal(30, 5) not null,,
    audit_date_only_date date default date(audit_date),
    primary key(surtido_id)
);

alter table data_surtidos add unique index unique_user_date(username, audit_date_only_date);
