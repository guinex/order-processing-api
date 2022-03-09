DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS listings CASCADE;
DROP TABLE IF EXISTS addresses CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS carts CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS ordered_items CASCADE;
DROP TABLE IF EXISTS payments CASCADE;
DROP TABLE IF EXISTS line_items CASCADE;
DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE TABLE customers (
    id   SERIAL    PRIMARY KEY NOT NULL ,
    name VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL
);

CREATE TABLE listings(
    id   SERIAL    PRIMARY KEY NOT NULL,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE addresses (
    id   SERIAL   PRIMARY KEY  NOT NULL,
    address_line_1 VARCHAR(128) NOT NULL,
    address_line_2 VARCHAR(128),
    city VARCHAR(128) NOT NULL,
    state VARCHAR(128) NOT NULL,
    zipcode VARCHAR(128) NOT NULL,
    customer_id INTEGER NOT NULL,
    foreign key (customer_id) references customers (id)
);


CREATE TABLE products(
    id   SERIAL  PRIMARY KEY  NOT NULL,
    name VARCHAR(128) NOT NULL,
    description VARCHAR(128) NOT NULL,
    price float NOT NULL,
    listing_id INTEGER NOT NULL,
    foreign key (listing_id) references listings (id)
);

CREATE TABLE carts(
    id   SERIAL    PRIMARY KEY  NOT NULL,
    shipping_address_id INTEGER NOT NULL,
    billing_address_id INTEGER NOT NULL,
    customer_id INTEGER NOT NULL,
    foreign key (shipping_address_id) references addresses (id),
    foreign key (billing_address_id) references addresses (id),
    foreign key (customer_id) references customers (id)
);


CREATE TABLE orders (
    id   SERIAL    PRIMARY KEY  NOT NULL,
    tax float,
    total float,
    shipping_charges float,
    paid float ,
    status BOOLEAN,
    shipping_address_id INTEGER NOT NULL,
    billing_address_id INTEGER NOT NULL,
    foreign key (shipping_address_id) references addresses (id),
    foreign key (billing_address_id) references addresses (id),
    customer_id INTEGER NOT NULL,
    foreign key (customer_id) references customers (id)
);


CREATE TABLE ordered_items (
    id   SERIAL  PRIMARY KEY  NOT NULL,
    tax float,
    sub_total float,
    order_status BOOLEAN,
    order_id INTEGER NOT NULL,
    foreign key (order_id) references orders(id)
);

CREATE TABLE payments (
    id   SERIAL  PRIMARY KEY   NOT NULL,
    payment_method VARCHAR(128) NOT NULL,
    confirmation_date VARCHAR(128),
    confirmation_number VARCHAR(128) NOT NULL,
    total float  NOT NULL,
    pay_type VARCHAR(128) NOT NULL,
    status BOOLEAN,
    order_id INTEGER NOT NULL,
    foreign key (order_id) references orders(id),
    customer_id INTEGER NOT NULL,
    foreign key (customer_id) references customers (id)
);


CREATE TABLE line_items(
    id   SERIAL   PRIMARY KEY  NOT NULL,
    name VARCHAR(128) NOT NULL,
    description VARCHAR(128) NOT NULL,
    price float NOT NULL,
    quantity INTEGER,
    status boolean,
    product_id INTEGER NOT NULL,
    foreign key (product_id) references products (id),
    cart_id INTEGER NOT NULL,
    foreign key (cart_id) references carts(id),
    ordered_item_id INTEGER,
    foreign key (ordered_item_id) references ordered_items (id)
);

CREATE SEQUENCE hibernate_sequence START 1;