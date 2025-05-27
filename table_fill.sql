insert into category(name, description)
values
    ('fruits', 'fruits frais'),
    ('légumes', 'légumes frais'),
    ('boissons', 'boissons non alcoolisées'),
    ('produits ménagers', 'produits pour l’entretien de la maison'),
    ('produits laitiers', 'produits à base de lait'),
    ('boulangerie', 'pains et viennoiseries'),
    ('épicerie', 'produits alimentaires de base');

insert into locality(zip_code, name)
values
    (1340, 'Ottignies'),
    (5030, 'Gembloux'),
    (1000, 'Bruxelles'),
    (4000, 'Liège'),
    (7000, 'Mons'),
    (6000, 'Charleroi'),
    (5000, 'Namur');

insert into type(name, description)
values
    ('particulier', 'client particulier sans numéro de tva'),
    ('professionnel', 'client professionnel avec numéro de tva');

insert into address(locality_zip_code, locality_name, street, house_number, postal_box_number)
values
    (1340, 'Ottignies', 'Rue du bouleau', '19A', NULL),
    (5030, 'Gembloux', 'Rue du chêne', '7', 5),
    (5030, 'Gembloux', 'Avenue du rouge gorge', '4', 9),
    (1000, 'Bruxelles', 'Avenue Louise', '10', NULL),
    (4000, 'Liège', 'Rue Saint-Gilles', '22', NULL),
    (7000, 'Mons', 'Boulevard Dolez', '5', NULL),
    (6000, 'Charleroi', 'Rue de la Montagne', '18', NULL),
    (5000, 'Namur', 'Rue de Fer', '30', NULL),
    (1340, 'Ottignies', 'Avenue des Combattants', '12', NULL),
    (5030, 'Gembloux', 'Rue Chapelle-Dieu', '3', NULL),
    (1000, 'Bruxelles', 'Rue Neuve', '45', NULL),
    (4000, 'Liège', 'Quai de la Batte', '7', NULL),
    (7000, 'Mons', 'Rue de Nimy', '14', NULL),
    (4000, 'Liège', 'Avenue de la Gare', '12', NULL),
    (1340, 'Ottignies', 'Rue des Fleurs', '9B', NULL),
    (1000, 'Bruxelles', 'Boulevard Anspach', '21', NULL),
    (7000, 'Mons', 'Place du Marché', '3', NULL),
    (6000, 'Charleroi', 'Rue de la Montagne', '15', NULL);

insert into customer(id, last_name, first_name, birthdate, email, phone,is_subscribed_to_newsletter, vat_number, address_locality_zip_code, address_locality_name, address_street, address_house_number, type)
values
    (1, 'Locht', 'Julien', '2005-10-04', null, null, FALSE, null, '1340', 'Ottignies', 'Rue du bouleau', '19A', 'particulier'),
    (2, 'Carton de Tournai', 'Martin', '2005-10-04', null, null, TRUE, '23869357964', 5030, 'Gembloux', 'Avenue du rouge gorge', '4', 'professionnel'),
    (3, 'Dupont', 'Alice', '1990-01-15', 'alice.dupont@example.com', '0478123456', TRUE, NULL, 1000, 'Bruxelles', 'Avenue Louise', '10', 'particulier'),
    (4, 'Martin', 'Jean', '1985-03-22', 'jean.martin@example.com', '0478123457', FALSE, 'BE0123456789', 4000, 'Liège', 'Rue Saint-Gilles', '22', 'professionnel'),
    (5, 'Lefevre', 'Sophie', '1992-07-08', 'sophie.lefevre@example.com', '0478123458', TRUE, NULL, 7000, 'Mons', 'Boulevard Dolez', '5', 'particulier'),
    (6, 'Dubois', 'Marc', '1980-11-30', 'marc.dubois@example.com', '0478123459', FALSE, 'BE9876543210', 6000, 'Charleroi', 'Rue de la Montagne', '18', 'professionnel'),
    (7, 'Lambert', 'Claire', '1995-05-12', 'claire.lambert@example.com', '0478123460', TRUE, NULL, 5000, 'Namur', 'Rue de Fer', '30', 'particulier'),
    (8, 'Moreau', 'Luc', '1988-09-17', 'luc.moreau@example.com', '0478123461', FALSE, 'BE1234567890', 1340, 'Ottignies', 'Avenue des Combattants', '12', 'professionnel'),
    (9, 'Simon', 'Julie', '1993-12-05', 'julie.simon@example.com', '0478123462', TRUE, NULL, 5030, 'Gembloux', 'Rue Chapelle-Dieu', '3', 'particulier'),
    (10, 'Michel', 'Paul', '1979-06-25', 'paul.michel@example.com', '0478123463', FALSE, 'BE0987654321', 1000, 'Bruxelles', 'Rue Neuve', '45', 'professionnel'),
    (11, 'Bernard', 'Emma', '1991-08-14', 'emma.bernard@example.com', '0478123464', TRUE, NULL, 4000, 'Liège', 'Quai de la Batte', '7', 'particulier'),
    (12, 'Roux', 'Thomas', '1983-02-19', 'thomas.roux@example.com', '0478123465', FALSE, 'BE1122334455', 7000, 'Mons', 'Rue de Nimy', '14', 'professionnel');

insert into loyalty_card(number, total_points, is_valid, customer)
values
    (1, 500, true, 1),
    (2, 875, true, 2),
    (3, 0, true, 3),
    (4, 0, true, 4),
    (5, 0, true, 5),
    (6, 0, true, 6),
    (7, 0, true, 7),
    (8, 0, true, 8),
    (9, 0, true, 9),
    (10, 0, true, 10),
    (11, 0, true, 11),
    (12, 0, true, 12);

insert into employee(id, last_name, first_name, address_locality_zip_code, address_locality_name, address_street, address_house_number)
values
    (1, 'Van der Cuylen', 'Mathias', '5030', 'Gembloux', 'Rue du chêne', '7'),
    (2, 'Dupuis', 'Élodie', '4000', 'Liège', 'Avenue de la Gare', '12'),
    (3, 'Lemoine', 'Sébastien', '1340', 'Ottignies', 'Rue des Fleurs', '9B'),
    (4, 'Durand', 'Camille', '1000', 'Bruxelles', 'Boulevard Anspach', '21'),
    (5, 'Moreau', 'Lucas', '7000', 'Mons', 'Place du Marché', '3'),
    (6, 'Petit', 'Anaïs', '6000', 'Charleroi', 'Rue de la Montagne', '15');

insert into sale(id, customer, date, employee)
values
    (1, 1, '2024-05-09', 1),
    (2, 1, '2025-05-09', 1),
    (3, 1, '2024-12-01', 1);

INSERT INTO product (id, name, net_price, vat_percentage, loyalty_points_nb, is_edible, min_quantity, sale_date, time_before_removing, category)
VALUES
    ('P001', 'Pomme', 1.20, 6, 10, TRUE, 100, '2025-05-29', 10, 'fruits'),
    ('P002', 'Banane', 0.90, 6, 8, TRUE, 200, '2025-07-02', 7, 'fruits'),
    ('P003', 'Carotte', 0.60, 6, 5, TRUE, 100, '2025-08-03', 12, 'légumes'),
    ('P004', 'Tomate', 1.10, 6, 6, TRUE, 50, '2024-05-04', 8, 'légumes'),
    ('P005', 'Poire', 1.30, 6, 9, TRUE, 60, '2025-05-05', 9, 'fruits'),
    ('P006', 'Jus d\'orange', 2.50, 12, 12, TRUE, 20, '2025-02-20', 10, 'boissons'),
    ('P007', 'Nettoyant multi-surfaces', 3.75, 21, 15, FALSE, 65, '2025-05-06', 30, 'produits ménagers'),
    ('P008', 'Lait demi-écrémé', 1.10, 6, 5, TRUE, 25, '2025-04-10', 10, 'produits laitiers'),
    ('P009', 'Yaourt nature', 0.80, 6, 3, TRUE, 80, '2025-03-15', 7, 'produits laitiers'),
    ('P010', 'Camembert', 2.50, 6, 7, TRUE, 150, '2025-02-25', 15, 'produits laitiers'),
    ('P011', 'Pain complet', 1.30, 6, 4, TRUE, 160, '2025-01-20', 3, 'boulangerie'),
    ('P012', 'Baguette', 1.00, 6, 3, TRUE, 25, '2025-05-01', 2, 'boulangerie'),
    ('P013', 'Croissant', 1.20, 6, 5, TRUE, 90, '2025-04-28', 2, 'boulangerie'),
    ('P014', 'Farine de blé', 0.95, 6, 2, TRUE, 100, '2025-03-01', 20, 'épicerie'),
    ('P015', 'Sucre en poudre', 1.10, 6, 2, TRUE, 225, '2025-03-05', 20, 'épicerie'),
    ('P016', 'Sel fin', 0.60, 6, 1, TRUE, 50, '2025-02-10', 30, 'épicerie'),
    ('P017', 'Huile d\'olive', 4.00, 12, 10, TRUE, 70, '2025-01-01', 60, 'épicerie'),
    ('P018', 'Beurre doux', 2.20, 6, 4, TRUE, 60, '2025-04-15', 14, 'produits laitiers'),
    ('P019', 'Fromage râpé', 2.80, 6, 6, TRUE, 50, '2025-03-10', 10, 'produits laitiers'),
    ('P020', 'Pain de mie', 1.50, 6, 4, TRUE, 20, '2025-02-20', 5, 'boulangerie'),
    ('P021', 'Confiture fraise', 2.70, 6, 5, TRUE, 25, '2025-05-05', 50, 'épicerie'),
    ('P022', 'Miel', 3.50, 6, 6, TRUE, 60, '2025-01-15', 40, 'épicerie'),
    ('P023', 'Lait d\'amande', 2.90, 6, 6, TRUE, 75, '2025-04-05', 20, 'produits laitiers'),
    ('P024', 'Biscottes', 1.90, 6, 3, TRUE, 25, '2025-03-20', 25, 'boulangerie'),
    ('P025', 'Céréales', 3.10, 6, 8, TRUE, 45, '2025-04-12', 45, 'épicerie'),
    ('P026', 'Lait chocolaté', 1.80, 6, 5, TRUE, 25, '2025-05-10', 10, 'produits laitiers'),
    ('P027', 'Brioche', 2.00, 6, 4, TRUE, 20, '2025-03-30', 5, 'boulangerie');

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
    (1, 1, 'P001', 10),
    (4, 2, 'P001', 14),
    (2, 1, 'P002', 29),
    (5, 3, 'P002', 15),
    (3, 2, 'P003', 17),
    (6, 1, 'P003', 70),
    (1, 3, 'P004', 12),
    (4, 1, 'P004', 22),
    (2, 2, 'P005', 45),
    (5, 1, 'P005', 55),
    (3, 3, 'P006', 75),
    (6, 2, 'P006', 25),
    (1, 1, 'P007', 3),
    (4, 3, 'P007', 40),
    (2, 2, 'P008', 12),
    (5, 2, 'P008', 14),
    (3, 1, 'P009', 5),
    (6, 3, 'P009', 20),
    (1, 2, 'P010', 55),
    (4, 2, 'P010', 35),
    (2, 3, 'P011', 23),
    (5, 3, 'P011', 45),
    (3, 1, 'P012', 50),
    (6, 2, 'P012', 50),
    (1, 3, 'P013', 60),
    (4, 1, 'P013', 40),
    (2, 1, 'P014', 70),
    (5, 2, 'P014', 30),
    (3, 3, 'P015', 80),
    (6, 1, 'P015', 20),
    (1, 1, 'P016', 90),
    (4, 3, 'P016', 10),
    (2, 2, 'P017', 55),
    (5, 1, 'P017', 45),
    (3, 2, 'P018', 60),
    (6, 3, 'P018', 40),
    (1, 3, 'P019', 50),
    (4, 2, 'P019', 50),
    (2, 1, 'P020', 40),
    (5, 3, 'P020', 60),
    (3, 1, 'P021', 30),
    (6, 2, 'P021', 70),
    (1, 2, 'P022', 45),
    (4, 1, 'P022', 55),
    (2, 3, 'P023', 60),
    (5, 2, 'P023', 40),
    (3, 1, 'P024', 50),
    (6, 3, 'P024', 50),
    (1, 1, 'P025', 55),
    (4, 3, 'P025', 45),
    (2, 2, 'P026', 70),
    (5, 1, 'P026', 30),
    (3, 3, 'P027', 80),
    (6, 2, 'P027', 20);

INSERT INTO promotion (id, min_quantity, reduction_percentage, product, start_date, end_date)
VALUES
    ('PROMO1', 3, 10, 'P001', '2025-05-05', '2025-05-15'),
    ('PROMO2', 2, 15, 'P002', '2025-05-07', '2025-05-20'),
    ('PROMO3', 2, 5, 'P004', '2025-05-08', '2025-05-18');