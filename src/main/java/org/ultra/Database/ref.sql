-- DROP TABLES (reverse dependency order)
DROP TABLE IF EXISTS machine_product CASCADE;
DROP TABLE IF EXISTS machine_assignment CASCADE;
DROP TABLE IF EXISTS work_orders CASCADE;
DROP TABLE IF EXISTS areas CASCADE;
DROP TABLE IF EXISTS machines CASCADE;
DROP TABLE IF EXISTS machine_types CASCADE;
DROP TABLE IF EXISTS sites CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS order_status_codes CASCADE;


-- ORDER STATUS CODES
CREATE TABLE order_status_codes (
                                    status_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                    status TEXT NOT NULL UNIQUE
);


-- PRODUCTS
CREATE TABLE products (
                          product_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          sku_number TEXT NOT NULL UNIQUE,
                          product_name TEXT NOT NULL
);


-- WORK ORDERS
CREATE TABLE work_orders (
                             order_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             product_id INT NOT NULL,
                             quantity INT NOT NULL,
                             created_at TIMESTAMPTZ DEFAULT NOW(),
                             status_code INT NOT NULL,

                             CONSTRAINT FK_work_orders_status_code
                                 FOREIGN KEY (status_code)
                                     REFERENCES order_status_codes(status_id),

                             CONSTRAINT FK_work_orders_product_id
                                 FOREIGN KEY (product_id)
                                     REFERENCES products(product_id)
);


-- MACHINE TYPES
CREATE TABLE machine_types (
                               type_code INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                               machine_type TEXT NOT NULL UNIQUE
);


-- MACHINES
CREATE TABLE machines (
                          machine_code INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          machine_name TEXT,
                          machine_type INT NOT NULL,
                          active BOOLEAN DEFAULT TRUE,

                          CONSTRAINT FK_machines_machine_type
                              FOREIGN KEY (machine_type)
                                  REFERENCES machine_types(type_code)
);


-- SITES
CREATE TABLE sites (
                       site_code INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       site_name TEXT NOT NULL,
                       active BOOLEAN DEFAULT TRUE
);


-- AREAS
CREATE TABLE areas (
                       area_code INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       site_code INT NOT NULL,
                       area_name TEXT NOT NULL,
                       active BOOLEAN DEFAULT TRUE,

                       CONSTRAINT FK_areas_site_code
                           FOREIGN KEY (site_code)
                               REFERENCES sites(site_code)
);


-- MACHINE ASSIGNMENT
CREATE TABLE machine_assignment (
                                    assignment_code INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                    area_code INT NOT NULL,
                                    machine_code INT NOT NULL,
                                    created_at TIMESTAMPTZ DEFAULT NOW(),
                                    active BOOLEAN DEFAULT TRUE,

                                    CONSTRAINT FK_machine_assignment_area_code
                                        FOREIGN KEY (area_code)
                                            REFERENCES areas(area_code),

                                    CONSTRAINT FK_machine_assignment_machine_code
                                        FOREIGN KEY (machine_code)
                                            REFERENCES machines(machine_code)
);


-- MACHINE PRODUCT CAPABILITY
CREATE TABLE machine_product (
                                 machine_product_code INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                 type_code INT NOT NULL,
                                 product_code INT NOT NULL,

                                 CONSTRAINT FK_machine_product_product_code
                                     FOREIGN KEY (product_code)
                                         REFERENCES products(product_id),

                                 CONSTRAINT FK_machine_product_type_code
                                     FOREIGN KEY (type_code)
                                         REFERENCES machine_types(type_code),

                                 CONSTRAINT UQ_machine_product
                                     UNIQUE (type_code, product_code)
);
