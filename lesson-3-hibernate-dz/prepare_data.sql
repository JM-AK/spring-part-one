use hibernate_lesson_3_1;

begin;

drop table if exists categories cascade;
create table categories (
    id bigint auto_increment,
    title varchar(255) not null unique key,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists products cascade;
create table products (
    id bigint auto_increment,
    title varchar(255) not null unique key,
    description text,
    price decimal,
    category_id bigint,
    primary key (id),
    constraint fk_products_categories_category_id
                      foreign key (category_id) references categories(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists consumers cascade;
create table consumers (
    id bigint auto_increment,
    firstname varchar(100) not null,
    lastname varchar(100) not null,
    login varchar(100) not null,
    email varchar(100) not null,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists orders cascade;
create table orders (
    id bigint auto_increment,
    consumer_id bigint,
    primary key (id),
    constraint fk_orders_consumers_consumer_id
                foreign key (consumer_id) references consumers(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists sales cascade;
create table sales (
    id bigint auto_increment,
    order_id bigint not null,
    product_id bigint not null,
    primary key (id),
    constraint fk_sales_orders_order_id
        foreign key (order_id) references orders(id),
    constraint fk_sales_products_product_id
        foreign key (product_id) references products(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into categories (title) values
('PC accessories'),
('PC'),
('Laptop');

insert into products (title, description, price, category_id) values
('Keyboard thinkpad 1', 'blablabla', 10, 1),
('MacMini', 'wowowow', 500, 2),
('MacBook Air 15', 'hmmmmm', 1500, 3);

insert into consumers (firstname, lastname, login, email) values
('Oleg', 'Black', 'oleg_black', 'oleg_black@mail.ru'),
('Svetlana', 'White', 'sv_white', 'sv_white@ya.ru'),
('Mike', 'Grey', 'm_grey', 'm_grey@ya.ru');

insert into orders (consumer_id) values
(1),
(2),
(3),
(1);

insert into sales (order_id, product_id) values
(1, 1),
(2, 2),
(3, 3),
(4, 2),
(4, 3);

commit
