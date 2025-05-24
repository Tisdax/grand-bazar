DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS shelf;
DROP TABLE IF EXISTS promotion;
DROP TABLE IF EXISTS command_line;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS `function`;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS loyalty_card;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS locality;

CREATE TABLE locality (
	zip_code MEDIUMINT,
    name VARCHAR(30),
    CONSTRAINT locality_pk primary key (zip_code, name),
    CONSTRAINT locality_zip_code_chk CHECK (zip_code > 0)
);

CREATE TABLE address (
	locality_zip_code MEDIUMINT,
    locality_name VARCHAR(30),
    street VARCHAR(50),
    house_number VARCHAR(4),
    postal_box_number TINYINT,
    CONSTRAINT locality_fk foreign key (locality_zip_code, locality_name) references locality (zip_code, name),
    CONSTRAINT address_pk primary key (locality_zip_code, locality_name, street, house_number),
    CONSTRAINT address_locality_zip_code_chk CHECK (locality_zip_code > 0),
    CONSTRAINT postal_box_number_chk CHECK (postal_box_number > 0)
);

CREATE TABLE type (
	name VARCHAR(20),
    description TEXT NOT NULL,
    CONSTRAINT type_pk primary key(name)
);

CREATE TABLE customer (
	id MEDIUMINT,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    birthdate DATE NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100),
    is_subscribed_to_newsletter BOOLEAN NOT NULL,
    vat_number VARCHAR(15),
    address_locality_zip_code MEDIUMINT NOT NULL,
    address_locality_name VARCHAR(30) NOT NULL,
    address_street VARCHAR (50) NOT NULL,
    address_house_number VARCHAR(4) NOT NULL,
    type VARCHAR(20) NOT NULL,
    CONSTRAINT customer_pk PRIMARY KEY (id),
    CONSTRAINT customer_address_fk FOREIGN KEY (address_locality_zip_code, address_locality_name, address_street, address_house_number) REFERENCES address (locality_zip_code, locality_name, street, house_number),
    CONSTRAINT customer_type_fk FOREIGN KEY (type) REFERENCES type(name),
    CONSTRAINT customer_id_chk CHECK (id > 0),
    CONSTRAINT customer_locality_zip_code_chk CHECK (address_locality_zip_code > 0)
);

CREATE TABLE loyalty_card (
	number INT,
    total_points SMALLINT NOT NULL,
    is_valid BOOLEAN NOT NULL,
    customer MEDIUMINT NOT NULL,
    CONSTRAINT loyalty_card_pk PRIMARY KEY (number),
    CONSTRAINT loyalty_card_customer_fk FOREIGN KEY (customer) REFERENCES customer(id),
    CONSTRAINT loyalty_cart_number_chk CHECK (number > 0)
);

CREATE TABLE employee (
	id SMALLINT,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    manager_id SMALLINT,
    address_locality_zip_code MEDIUMINT NOT NULL,
    address_locality_name VARCHAR(30) NOT NULL,
    address_street VARCHAR (50) NOT NULL,
    address_house_number VARCHAR(4) NOT NULL,
    CONSTRAINT employee_pk PRIMARY KEY (id),
    CONSTRAINT manager_id_fk FOREIGN KEY (manager_id) REFERENCES employee(id),
	CONSTRAINT employee_address_fk FOREIGN KEY (address_locality_zip_code, address_locality_name, address_street, address_house_number) REFERENCES address (locality_zip_code, locality_name, street, house_number),
    CONSTRAINT employee_id_chk CHECK (id > 0),
    CONSTRAINT employee_locality_zip_code_chk CHECK (address_locality_zip_code > 0)
);

CREATE TABLE `function` (
	id VARCHAR(10),
    name VARCHAR(40) NOT NULL,
    CONSTRAINT function_pk PRIMARY KEY (id)
);

CREATE TABLE role (
    `function` VARCHAR(10),
    employee SMALLINT,
    CONSTRAINT role_pk PRIMARY KEY (`function`, employee),
	CONSTRAINT position_fk FOREIGN KEY (`function`) REFERENCES `function`(id),
    CONSTRAINT role_employee_fk FOREIGN KEY (employee) REFERENCES employee(id)
);

CREATE TABLE sale (
	id INT,
    customer MEDIUMINT,
    date DATE NOT NULL,
    employee SMALLINT NOT NULL,
    CONSTRAINT sale_pk PRIMARY KEY (id),
    CONSTRAINT sale_customer_fk FOREIGN KEY (customer) REFERENCES customer(id),
    CONSTRAINT sale_employee_fk FOREIGN KEY (employee) REFERENCES employee(id),
    CONSTRAINT sale_id_chk CHECK (id > 0),
    CONSTRAINT sale_customer_chk CHECK (customer > 0),
    CONSTRAINT sale_employee_chk CHECK (employee > 0)
);

CREATE TABLE category (
	name VARCHAR(30),
    description TEXT NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (name)
);

CREATE TABLE product (
	id VARCHAR(13),
    name VARCHAR(30) NOT NULL,
    net_price DECIMAL(6,2) NOT NULL,
    vat_percentage TINYINT NOT NULL,
    loyalty_points_nb SMALLINT NOT NULL,
    is_edible BOOLEAN NOT NULL,
    min_quantity SMALLINT,
    promotion_min_quantity SMALLINT,
    sale_date DATE NOT NULL,
    time_before_removing SMALLINT,
    category VARCHAR(30) NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id),
    CONSTRAINT product_category_fk FOREIGN KEY (category) REFERENCES category(name),
    CONSTRAINT product_vat_percentage_chk CHECK (vat_percentage IN(6, 12, 21)),
    CONSTRAINT product_net_price_chk CHECK (net_price > 0),
    CONSTRAINT product_min_quantity_chk CHECK (min_quantity > 0),
    CONSTRAINT product_promotion_min_quantity_chk CHECK (promotion_min_quantity > 0),
    CONSTRAINT product_time_before_removing_chk CHECK (time_before_removing > 0)
);

CREATE TABLE command_line (
	sale INT,
    product VARCHAR(13),
    quantity SMALLINT NOT NULL,
    CONSTRAINT command_line_pk PRIMARY KEY (sale, product),
    CONSTRAINT command_line_sale_fk FOREIGN KEY (sale) REFERENCES sale(id),
    CONSTRAINT command_line_product_fk FOREIGN KEY (product) REFERENCES product(id),
    CONSTRAINT command_line_sale_id_chk CHECK (sale > 0),
    CONSTRAINT command_line_quantity_chk CHECK (quantity > 0)
);

CREATE TABLE promotion (
	id VARCHAR(10),
    min_quantity TINYINT NOT NULL,
    reduction_percentage TINYINT NOT NULL,
    product VARCHAR(13) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CONSTRAINT promotion_pk PRIMARY KEY (id),
    CONSTRAINT promotion_product_fk FOREIGN KEY (product) REFERENCES product(id),
    CONSTRAINT promotion_min_quantity_chk CHECK (min_quantity > 0),
    CONSTRAINT promotion_reduction_percentage_chk CHECK (reduction_percentage > 0),
    CONSTRAINT promotion_end_date_chk CHECK (end_date >= start_date)
);

CREATE TABLE shelf (
	id TINYINT,
    is_refrigirated BOOLEAN NOT NULL,
    CONSTRAINT shelf_pk PRIMARY KEY (id),
    CONSTRAINT shelf_id_chk CHECK (id > 0)
);

CREATE TABLE stock (
	shelf TINYINT,
    shelf_level TINYINT,
    product VARCHAR(13),
    quantity SMALLINT NOT NULL,
    CONSTRAINT stock_pk PRIMARY KEY (shelf, shelf_level, product),
    CONSTRAINT stock_shelf_fk FOREIGN KEY (shelf) REFERENCES shelf(id),
    CONSTRAINT stock_product_fk FOREIGN KEY (product) REFERENCES product(id),
    CONSTRAINT stock_shelf_id_chk CHECK (shelf > 0),
    CONSTRAINT stock_shelf_level_chk CHECK (shelf_level > 0),
    CONSTRAINT shelf_quantity_chk CHECK (quantity > 0)
);


-- pour tests création d objets client et produit
insert into category(name, description)
values
    ('fruits', 'fruits frais'),
    ('légumes', 'légumes frais'),
    ('boissons', 'boissons non alcoolisées'),
    ('produits ménagers', 'produits pour l’entretien de la maison');

insert into locality(zip_code, name)
values
    ('1340', 'Ottignies'),
    ('5030', 'Gembloux');

insert into type(name, description)
values
    ('particulier', 'client particulier sans numéro de tva'),
    ('professionnel', 'client professionnel avec numéro de tva');

insert into address(locality_zip_code, locality_name, street, house_number, postal_box_number)
values
    (1340, 'Ottignies', 'Rue du ruisseau', '24', 2),
    (5030, 'Gembloux', 'Rue du chêne', '7', 5);

insert into customer(id, last_name, first_name, birthdate, is_subscribed_to_newsletter, vat_number, address_locality_zip_code, address_locality_name, address_street, address_house_number, type)
values
    (1, 'Locht', 'Julien', '2005-10-04', FALSE, null, '1340', 'Ottignies', 'Rue du ruisseau', '24', 'particulier'),
    (2, 'Carton de Tournai', 'Martin', '2005-10-04', TRUE, '23869357964', '1340', 'Ottignies', 'Rue du ruisseau', '24', 'professionnel');

insert into loyalty_card(number, total_points, is_valid, customer)
values
    (5342632, 500, true, 1),
    (4562278, 875, true, 2);

insert into employee(id, last_name, first_name, address_locality_zip_code, address_locality_name, address_street, address_house_number)
values
    (1, 'Van der Cuylen', 'Mathias', '5030', 'Gembloux', 'Rue du chêne', '7');

insert into sale(id, customer, date, employee)
values
    (1, 1, '2024-05-09', 1),
    (2, 1, '2025-05-09', 1),
    (3, 1, '2024-12-01', 1);

INSERT INTO product (id, name, net_price, vat_percentage, loyalty_points_nb, is_edible, min_quantity, promotion_min_quantity, sale_date, time_before_removing, category)
VALUES
    ('P001', 'Pomme', 1.20, 6, 10, TRUE, 100, 3, '2025-05-29', 10, 'fruits'),
    ('P002', 'Banane', 0.90, 6, 8, TRUE, 200, 2, '2025-07-02', 7, 'fruits'),
    ('P003', 'Carotte', 0.60, 6, 5, TRUE, 1, 2, '2025-08-03', 12, 'légumes'),
    ('P004', 'Tomate', 1.10, 6, 6, TRUE, 1, 2, '2024-05-04', 8, 'légumes'),
    ('P005', 'Poire', 1.30, 6, 9, TRUE, 1, 3, '2025-05-05', 9, 'fruits'),
    ('P006', 'Jus d\'orange', 2.50, 12, 12, TRUE, 1, 2, '2025-02-20', 10, 'boissons'),
    ('P007', 'Nettoyant multi-surfaces', 3.75, 21, 15, FALSE, 1, 2, '2025-05-06', 30, 'produits ménagers');

-- Sale 101
INSERT INTO command_line (sale, product, quantity)
    VALUES
   (1, 'P001', 2),
   (1, 'P003', 1),
   (1, 'P005', 4),
   (2, 'P002', 3),
   (2, 'P004', 2),
   (2, 'P001', 1),
   (3, 'P003', 2),
   (3, 'P004', 1),
   (3, 'P002', 2);

INSERT INTO shelf (id, is_refrigirated)
VALUES
    (1, true),
    (2, true),
    (3, true),
    (4, false),
    (5, false),
    (6, false);

INSERT INTO stock (shelf, shelf_level, product, quantity)
VALUES
    (4, 1, 'P001', 40),
    (4, 2, 'P001', 50),
    (4, 3, 'P002', 40),
    (4, 4, 'P002', 40),
    (5, 1, 'P005', 30),
    (5, 2, 'P003', 60),
    (5, 3, 'P004', 55),
    (1, 1, 'P006', 45),
    (6, 1, 'P007', 25);

INSERT INTO promotion (id, min_quantity, reduction_percentage, product, start_date, end_date)
VALUES
    ('PROMO1', 3, 10, 'P001', '2025-05-05', '2025-05-15'),
    ('PROMO2', 2, 15, 'P002', '2025-05-07', '2025-05-20'),
    ('PROMO3', 2, 5, 'P004', '2025-05-08', '2025-05-18');