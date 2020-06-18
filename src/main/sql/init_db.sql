DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS users_shopping_lists;
DROP TABLE IF EXISTS shopping_lists;
DROP TABLE IF EXISTS users_recipes;
DROP TABLE IF EXISTS recipes;

create table if not exists products
(
    id           serial       not null
        constraint products_pkey
            primary key,
    product_name varchar(100) not null,
    department   varchar(100) not null
);

create table if not exists users
(
    id       serial       not null
        constraint users_pkey
            primary key,
    login    varchar(100) not null,
    password varchar(225) not null,
    email    varchar(100) not null
);

create table if not exists users_recipes
(
    id      serial        not null
        constraint users_recipes_pk
            primary key,
    user_id integer
        constraint users_recipes_users_id_fk
            references users
            on update cascade on delete cascade,
    method  varchar(1000) not null,
    picture varchar,

);

create table if not exists user_recipe_products
(
    product_id     integer not null
        constraint recipes_products_id_fk
            references products,
    user_recipe_id integer not null
        constraint recipes_users_recipes_id_fk
            references users_recipes
            on update cascade on delete cascade
);

create table if not exists shopping_lists
(
    id               serial  not null
        constraint shopping_lists_pk
            primary key,
    user_id          integer not null
        constraint shopping_lists_users_id_fk
            references users
            on update cascade on delete cascade,
    product_id       integer
        constraint shopping_lists_products_id_fk
            references products,
    shopping_list_id integer not null
);

create unique index if not exists shopping_lists_id_uindex
    on shopping_lists (id);

