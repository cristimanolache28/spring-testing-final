INSERT INTO categories (id, name) VALUES (1, 'Main Dish');
INSERT INTO categories (id, name) VALUES (2, 'Desert');
INSERT INTO categories (id, name) VALUES (3, 'Chef Speciality');
INSERT INTO categories (id, name) VALUES (4, 'Salads');

INSERT INTO products (id, name, price, id_category) VALUES (1, 'Italian Burger', 45.5, 3);
INSERT INTO products (id, name, price, id_category) VALUES (2, 'Lava Cake', 15.00, 2);
INSERT INTO products (id, name, price, id_category) VALUES (3, 'Greek Salad', 25.00, 4);
INSERT INTO products (id, name, price, id_category) VALUES (4, 'Tiramisu', 15.00, 2);
INSERT INTO products (id, name, price, id_category) VALUES (5, 'Beef Steak', 65.00, 1);

INSERT INTO ingredients (id, name) VALUES (1, 'Beef minced meat');
INSERT INTO ingredients (id, name) VALUES (2, 'Chocolate');
INSERT INTO ingredients (id, name) VALUES (3, 'Mascarpone');
INSERT INTO ingredients (id, name) VALUES (4, 'Beef meat');
INSERT INTO ingredients (id, name) VALUES (5, 'Fried chips');

INSERT INTO product_ingredients (id, id_product, id_ingredient) VALUES (1, 1, 2);
INSERT INTO product_ingredients (id, id_product, id_ingredient) VALUES (2, 2, 1);
INSERT INTO product_ingredients (id, id_product, id_ingredient) VALUES (3, 3, 2);
INSERT INTO product_ingredients (id, id_product, id_ingredient) VALUES (4, 4, 5);
INSERT INTO product_ingredients (id, id_product, id_ingredient) VALUES (5, 5, 3);
INSERT INTO product_ingredients (id, id_product, id_ingredient) VALUES (6, 1, 1);
INSERT INTO product_ingredients (id, id_product, id_ingredient) VALUES (7, 2, 2);

INSERT INTO employers (id, name, job_title, badge_code) VALUES (1, 'John Lew', 'waiter', 'adcf6efc-a86c-11ec-b909-0242ac120002');
INSERT INTO employers (id, name, job_title, badge_code) VALUES (2, 'Mateo Woltz', 'waiter', 'bdcf6efc-a86c-11ec-b909-0242ac120002');
INSERT INTO employers (id, name, job_title, badge_code) VALUES (3, 'Trina Swander', 'waiter', 'cdcf6efc-a86c-11ec-b909-0242ac120002');


-- INSERT INTO product_category (id, id_prod, id_categ) VALUES (1, 3, 2);
-- INSERT INTO product_category (id, id_prod, id_categ) VALUES (1, 4, 4);
-- INSERT INTO product_category (id, id_prod, id_categ) VALUES (1, 5, 3);
-- INSERT INTO product_category (id, id_prod, id_categ) VALUES (1, 1, 1);
-- INSERT INTO product_category (id, id_prod, id_categ) VALUES (1, 2, 2);




