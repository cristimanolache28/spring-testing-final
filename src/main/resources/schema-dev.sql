------------------------ CATEGORIES TABLE ------------------------------------
CREATE TABLE IF NOT EXISTS categories (
                          id INTEGER NOT NULL AUTO_INCREMENT,
                          name VARCHAR(128) NOT NULL,
                          PRIMARY KEY (id)
);

------------------------ PRODUCTS TABLE ------------------------------------
CREATE TABLE IF NOT EXISTS products (
                         id INTEGER NOT NULL AUTO_INCREMENT,
                         name VARCHAR(128) NOT NULL,
                         price DOUBLE NOT NULL,
                         id_category INTEGER NOT NULL,
                         PRIMARY KEY (id)
);
ALTER TABLE products ADD FOREIGN KEY(id_category) REFERENCES categories(id);

------------------------ INGREDIENTS TABLE ------------------------------------
CREATE TABLE IF NOT EXISTS ingredients (
                          id INTEGER NOT NULL AUTO_INCREMENT,
                          name VARCHAR(128) NOT NULL,
                          PRIMARY KEY (id)
);

------------------------ PRODUCT_INGREDIENTS TABLE ------------------------------------
CREATE TABLE IF NOT EXISTS product_ingredients (
                          id INTEGER NOT NULL AUTO_INCREMENT,
                          id_product INTEGER NOT NULL,
                          id_ingredient INTEGER NOT NULL,
                          PRIMARY KEY (id)
);
ALTER TABLE product_ingredients ADD FOREIGN KEY(id_product) REFERENCES products(id);
ALTER TABLE product_ingredients ADD FOREIGN KEY(id_ingredient) REFERENCES ingredients(id);

------------------------ EMPLOYERS TABLE ------------------------------------
CREATE TABLE IF NOT EXISTS employers (
                           id INTEGER NOT NULL AUTO_INCREMENT,
                           name VARCHAR(128) NOT NULL,
                           job_title VARCHAR(128) NOT NULL,
                           badge_code VARCHAR(36),
                           PRIMARY KEY (id)
);

------------------------ ORDER TABLE ------------------------------------
CREATE TABLE IF NOT EXISTS orders (
                                     id INTEGER NOT NULL AUTO_INCREMENT,
                                     order_date DATE NOT NULL,
                                     id_employer INTEGER NOT NULL,
                                     discount DOUBLE,
                                     total DOUBLE NOT NULL,
                                     PRIMARY KEY (id)
);
ALTER TABLE orders ADD FOREIGN KEY(id_employer) REFERENCES employers(id);

CREATE TABLE IF NOT EXISTS order_products (
                                     id INTEGER NOT NULL AUTO_INCREMENT,
                                     id_order INTEGER NOT NULL,
                                     id_product INTEGER NOT NULL,
                                     PRIMARY KEY (id)
);
ALTER TABLE order_products ADD FOREIGN KEY(id_order) REFERENCES orders(id);
ALTER TABLE order_products ADD FOREIGN KEY(id_product) REFERENCES products(id);

