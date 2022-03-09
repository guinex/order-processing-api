SELECT * FROM CUSTOMERS;
INSERT INTO customers (id, name, email) VALUES (1, 'april', 'asc@as.com');
INSERT INTO customers (id, name, email) VALUES (2, 'may', 'assac@as.com');
INSERT INTO customers (id, name, email) VALUES (3, 'amy', 'sac@as.com');
INSERT INTO customers (id, name, email) VALUES (4, 'john', 'lks@as.com');

INSERT INTO addresses (id, address_line_1, address_line_2, city, state, zipcode, customer_id) VALUES (1, 'lane 1', '', 'Raleigh', 'NC', '34212', 1);
INSERT INTO addresses (id, address_line_1, address_line_2, city, state, zipcode, customer_id) VALUES (2, 'lane 2', '', 'Raleigh', 'NC', '34212', 2);
INSERT INTO addresses (id, address_line_1, address_line_2, city, state, zipcode, customer_id) VALUES (3, 'lane 3', '', 'Raleigh', 'NC', '34212', 3);
INSERT INTO addresses (id, address_line_1, address_line_2, city, state, zipcode, customer_id) VALUES (4, 'lane 4', '', 'Raleigh', 'NC', '34212', 4);


INSERT INTO carts (id, shipping_address_id, billing_address_id,customer_id) VALUES (1, 1, 1, 1);
INSERT INTO carts (id, shipping_address_id, billing_address_id, customer_id) VALUES (2, 2, 2, 2);
INSERT INTO carts (id, shipping_address_id, billing_address_id, customer_id) VALUES (3, 3, 3, 3);
INSERT INTO carts (id, shipping_address_id, billing_address_id, customer_id) VALUES (4, 4, 4, 4);

INSERT INTO listings (id, name) VALUES (1, 'list1');
INSERT INTO listings (id, name) VALUES (2, 'list2');
INSERT INTO listings (id, name) VALUES (3, 'list3');
INSERT INTO listings (id, name) VALUES (4, 'list4');


INSERT INTO products (id, name, description, price, listing_id) VALUES (1, 'product1', 'some product', 10.7, 1);
INSERT INTO products (id, name, description, price, listing_id) VALUES (2, 'product2', 'some product two', 60.3, 2);
INSERT INTO products (id, name, description, price, listing_id) VALUES (3, 'product3', 'some product three', 89.7, 1);
INSERT INTO products (id, name, description, price, listing_id) VALUES (4, 'product4', 'some product four', 76.0, 3);

