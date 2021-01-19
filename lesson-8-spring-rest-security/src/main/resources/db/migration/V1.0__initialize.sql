create table users (
    id                      bigserial,
    login                   varchar(255) not null,
    password                varchar(255) not null,
    email                   varchar(255) not null,
    primary key (id)
);

create table roles (
    id                      serial,
    name                    varchar(50) not null,
    primary key (id)
);

create table users_roles (
    user_id                 bigint not null,
    role_id                 int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
);

insert into roles(name)
values
('ROLE_USER'),
('ROLE_ADMIN'),
('ROLE_MANAGER'),
('ROLE_SUPERADMIN');

insert into users(login, password, email)
values
('user', '$2y$10$wzdBHCtfwteBro/Z5XOfxeg6.a0UFPN9/m/Mpv4sVJ.anCTL6Npem' , 'user@gmail.com'),
('admin', '$2y$10$itNKYOpw2WFqeh5HqKM.Nu/yfplrMZB2dkKIP2QdSmhRX9wOd3wOC ', 'admin@gmail.com'),
('manager', '$2y$10$IAa.hf4LEA4BY5Gp3JNJOecJJIDP.cIGx6Y48Mss/CISwNoq/gU0a ', 'manager@gmail.com'),
('superadmin', '$2y$10$aqSx.8/Fm57x6aE0YoggGeFWapqOscTjUbhwvPe/ahF.kaKrrHEni ', 'superadmin@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2),
(3, 3),
(4, 4);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int
);

create table orders (
    id                      bigserial primary key,
    user_id                 bigint references users(id),
    price                   int
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price                   int,
    price_per_product       int,
    quantity                int
);

insert into products (title, price)
values
('Bread1', 21),
('Bread2', 22),
('Bread3', 23),
('Bread4', 24),
('Bread5', 25),
('Bread6', 26),
('Bread7', 27),
('Bread8', 28),
('Bread9', 29),
('Bread10', 30),
('Bread11', 31),
('Bread12', 32),
('Bread13', 33),
('Bread14', 34);
