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
	zip_code VARCHAR(12),
    name VARCHAR(30),
    CONSTRAINT locality_pk primary key (zip_code, name)
);

CREATE TABLE address (
	locality_zip_code VARCHAR(12),
    locality_name VARCHAR(30),
    street VARCHAR(50),
    house_number INT,
    postal_box_number INT,
    CONSTRAINT locality_fk foreign key (locality_zip_code, locality_name) references locality (zip_code, name),
    CONSTRAINT address_pk primary key (locality_zip_code, locality_name, street, house_number)
);

CREATE TABLE type (
	name VARCHAR(20),
    description TEXT NOT NULL,
    CONSTRAINT type_pk primary key(name)
);

CREATE TABLE customer (
	id INT,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    birthdate DATE NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100),
    is_subscribed_to_newsletter BOOLEAN NOT NULL,
    vat_number VARCHAR(15),
    address_locality_zip_code VARCHAR(12) NOT NULL,
    address_locality_name VARCHAR(30) NOT NULL,
    address_street VARCHAR (50) NOT NULL,
    address_house_number INT NOT NULL,
    type VARCHAR(20) NOT NULL,
    CONSTRAINT customer_pk PRIMARY KEY (id),
    CONSTRAINT customer_address_fk FOREIGN KEY (address_locality_zip_code, address_locality_name, address_street, address_house_number) REFERENCES address (locality_zip_code, locality_name, street, house_number),
    CONSTRAINT customer_type_fk FOREIGN KEY (type) REFERENCES type(name)
);

CREATE TABLE loyalty_card (
	number INT,
    total_points INT NOT NULL,
    is_valid BOOLEAN NOT NULL,
    customer INT NOT NULL,
    CONSTRAINT loyalty_card_pk PRIMARY KEY (number),
    CONSTRAINT loyalty_card_customer_fk FOREIGN KEY (customer) REFERENCES customer(id)
);

CREATE TABLE employee (
	id INT,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    manager_id INT,
    address_locality_zip_code VARCHAR(12) NOT NULL,
    address_locality_name VARCHAR(30) NOT NULL,
    address_street VARCHAR (50) NOT NULL,
    address_house_number INT NOT NULL,
    CONSTRAINT employee_pk PRIMARY KEY (id),
    CONSTRAINT manager_id_fk FOREIGN KEY (manager_id) REFERENCES employee(id),
	CONSTRAINT employee_address_fk FOREIGN KEY (address_locality_zip_code, address_locality_name, address_street, address_house_number) REFERENCES address (locality_zip_code, locality_name, street, house_number)
);

CREATE TABLE `function` (
	id VARCHAR(10),
    name VARCHAR(40) NOT NULL,
    CONSTRAINT function_pk PRIMARY KEY (id)
);

CREATE TABLE role (
    `function` VARCHAR(10),
    employee INT,
    CONSTRAINT role_pk PRIMARY KEY (`function`, employee),
	CONSTRAINT position_fk FOREIGN KEY (`function`) REFERENCES `function`(id),
    CONSTRAINT role_employee_fk FOREIGN KEY (employee) REFERENCES employee(id)
);

CREATE TABLE sale (
	id INT,
    customer INT,
    date DATE NOT NULL,
    employee INT NOT NULL,
    CONSTRAINT sale_pk PRIMARY KEY (id),
    CONSTRAINT sale_customer_fk FOREIGN KEY (customer) REFERENCES customer(id),
    CONSTRAINT sale_employee_fk FOREIGN KEY (employee) REFERENCES employee(id)
);

CREATE TABLE category (
	name VARCHAR(30),
    description TEXT NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (name)
);

CREATE TABLE product (
	id VARCHAR(10),
    name VARCHAR(30) NOT NULL,
    net_price DECIMAL(6,2) NOT NULL,
    vat_percentage INT NOT NULL,
    loyalty_points_nb INT NOT NULL,
    is_edible BOOLEAN NOT NULL,
    min_quantity INT,
    promotion_min_quantity INT,
    sale_date DATE NOT NULL,
    time_before_removing INT,
    category VARCHAR(30) NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id),
    CONSTRAINT product_category_fk FOREIGN KEY (category) REFERENCES category(name)
);

CREATE TABLE command_line (
	sale INT,
    product VARCHAR(10),
    quantity INT NOT NULL,
    CONSTRAINT command_line_pk PRIMARY KEY (sale, product),
    CONSTRAINT command_line_sale_fk FOREIGN KEY (sale) REFERENCES sale(id),
    CONSTRAINT command_line_product_fk FOREIGN KEY (product) REFERENCES product(id)
);

CREATE TABLE promotion (
	id VARCHAR(10),
    min_quantity INT NOT NULL,
    reduction_percentage INT NOT NULL,
    product VARCHAR(10) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CONSTRAINT promotion_pk PRIMARY KEY (id),
    CONSTRAINT promotion_product_fk FOREIGN KEY (product) REFERENCES product(id)
);

CREATE TABLE shelf (
	id INT,
    is_refrigirated BOOLEAN NOT NULL,
    CONSTRAINT shelf_pk PRIMARY KEY (id)
);

CREATE TABLE stock (
	shelf INT,
    shelf_level INT,
    product VARCHAR(10),
    quantity INT NOT NULL,
    CONSTRAINT stock_pk PRIMARY KEY (shelf, shelf_level, product),
    CONSTRAINT stock_shelf_fk FOREIGN KEY (shelf) REFERENCES shelf(id),
    CONSTRAINT stock_product_fk FOREIGN KEY (product) REFERENCES product(id)
);


-- pour tests création d objets client et produit
insert into category(name, description) values ("fruits", "c 1 froui");
insert into category(name, description) values ("légumes", "c 1 légum");
insert into locality(zip_code, name) values ("1340", "Ottignies");
insert into locality(zip_code, name) values ("5030", "Gembloux");
insert into type(name, description) values ("particulier", "client particulier sans numéro de tva");
insert into type(name, description) values ("professionnel", "client professionnel avec numéro de tva");