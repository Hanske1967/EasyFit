USE easyfit;

DELETE FROM consumptiondetail;
DELETE FROM consumption;
DELETE FROM recipe_product_link;
DELETE FROM product;
DELETE FROM productcategory;
DELETE FROM unit;
DELETE FROM user;

INSERT INTO user (id, username, updatedate, firstname, lastname, targetweight, daypoints, extrapoints) VALUES (1, 'INGE', '2013-03-07 19:08:57', 'Inge', 'Vanden Nest', 64, 28, 38);
INSERT INTO user (id, username, updatedate, firstname, lastname, targetweight, daypoints, extrapoints) VALUES (2, 'HANS', '2013-03-07 19:08:57', 'Hans', 'Fortemaison', 78, 32, 48);
INSERT INTO user (id, username, updatedate, firstname, lastname, targetweight, daypoints, extrapoints) VALUES (3, 'THAIS', '2013-03-11 00:00:00', 'Thaïs', 'Fortemaison', 66, 35, 40);

# UNIT
INSERT INTO unit (id, name, description) VALUES (13, 'ml', 'milliliter');
INSERT INTO unit (id, name, description) VALUES (14, 'g', 'gramme');
INSERT INTO unit (id, name, description) VALUES (15, 'klp', 'kleine lepel');
INSERT INTO unit (id, name, description) VALUES (16, 'glp', 'groote lepel');
INSERT INTO unit (id, name, description) VALUES (17, 'stuk', 'stuk of snede');
INSERT INTO unit (id, name, description) VALUES (18, 'kop', 'coffie kop');


#PRODUCT CATEGORY
INSERT INTO productcategory (id, name) VALUES (8, 'Beleg');
INSERT INTO productcategory (id, name) VALUES (7, 'Brood');
INSERT INTO productcategory (id, name) VALUES (5, 'Drank');
INSERT INTO productcategory (id, name) VALUES (12, 'Flakes');
INSERT INTO productcategory (id, name) VALUES (1, 'Fruit');
INSERT INTO productcategory (id, name) VALUES (2, 'Groenten');
INSERT INTO productcategory (id, name) VALUES (6, 'Kaas');
INSERT INTO productcategory (id, name) VALUES (10, 'Pasta & rijst');
INSERT INTO productcategory (id, name) VALUES (11, 'Sauces');
INSERT INTO productcategory (id, name) VALUES (3, 'Snoep');
INSERT INTO productcategory (id, name) VALUES (4, 'Vlees');
INSERT INTO productcategory (id, name) VALUES (9, 'Zuivelproducten');


# PRODUCT
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (12, 'light kaas', 17, 3.5, 2, 'Maredsous light', FALSE, 'P', 8, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (13, 'boterham', 17, 1, 2.5, 'Grijze brood', FALSE, 'P', 7, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (14, 'croissant', 17, 1, 4.5, 'Croissant', FALSE, 'P', 7, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (15, 'confituur', 15, 1, 1.5, 'Light confituur', FALSE, 'P', 8, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (16, 'thee', 13, 250, 0.5, 'thee zonder suiker', TRUE, 'P', 5, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (17, 'croissant confituur', 17, 1, 7.5, 'Croissant met light confituur', TRUE, 'R', 7, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (18, 'boterham met kaas', 17, 1, 4.21, 'Boterham met Mardesous', TRUE, 'R', 7, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (20, 'yaourth', 13, 20, 2, 'Yaourth Bio', FALSE, 'P', 6, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (21, 'Sandwich bio', 17, 1, 5.07, 'Sand Bio', FALSE, 'R', 7, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (22, 'Ham', 17, 1, 2, 'Snijt ham', FALSE, 'P', 8, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (25, 'bolognese sauce', 13, 500, 14.2, 'Bolognese sauce', FALSE, 'R', 11, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (26, 'concasse tomate', 13, 250, 3, 'Boite de concassé de tomates', FALSE, 'P', 2, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (27, 'ajuin', 17, 1, 0.5, 'Ajuin', FALSE, 'P', 2, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (28, 'Gehackt', 14, 500, 8, 'Gehackt vark', FALSE, 'P', 4, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (29, 'Rode wijn', 13, 50, 4, 'Kleine fless rode wijn', FALSE, 'P', 5, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (32, 'café', 13, 20, 0, 'Café noir', FALSE, 'P', 5, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (33, 'Lait écrémé', 13, 20, 2, 'Lait écrémé delhaize', FALSE, 'P', 5, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (34, 'Café lait', 13, 25, 0.5, 'café lait', FALSE, 'R', 5, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (37, 'San JF', 17, 1, 7.12, 'Sandwich Jambon Fromage', FALSE, 'R', 2, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (38, 'Appel', 17, 1, 0, 'Appel', FALSE, 'P', 1, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (39, 'Riz', 14, 100, 3, 'Riz complet', FALSE, 'P', 10, 7);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (40, 'Flakes', 14, 25, 4, 'Flakes choco', FALSE, 'P', 12, null);
INSERT INTO product (id, name, unitid, amount, points, description, favorite, classtype, productcategoryid, maxpoints) VALUES (41, 'Sinaasappelsap', 13, 25, 2, 'Sinaasappelsap blik', FALSE, 'P', 1, null);

# RECIPE
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (1, 17, 14, 1);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (6, 21, 13, 1);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (7, 21, 22, 1);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (8, 21, 12, 1);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (11, 25, 26, 500);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (12, 34, 32, 25);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (13, 34, 33, 5);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (14, 25, 27, 2);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (15, 25, 28, 450);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (20, 18, 12, 3);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (24, 37, 22, 1);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (25, 37, 13, 2);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (26, 37, 26, 10);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (27, 37, 38, 2);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (28, 18, 13, 1);
INSERT INTO recipe_product_link (id, recipeid, productid, amount) VALUES (29, 17, 15, 2);
