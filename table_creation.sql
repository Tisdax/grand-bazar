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
    house_number VARCHAR(5),
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
    address_house_number VARCHAR(5) NOT NULL,
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
    address_house_number VARCHAR(5) NOT NULL,
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