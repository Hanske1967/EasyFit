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
INSERT INTO unit (id, name, shared, description, creationdate, creationuser, updatedate, updateuser) VALUES (13, 'ml', TRUE, 'milliliter', '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO unit (id, name, shared, description, creationdate, creationuser, updatedate, updateuser) VALUES (14, 'g', TRUE, 'gramme', '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO unit (id, name, shared, description, creationdate, creationuser, updatedate, updateuser) VALUES (15, 'klp', TRUE, 'kleine lepel', '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO unit (id, name, shared, description, creationdate, creationuser, updatedate, updateuser) VALUES (16, 'glp', TRUE, 'groote lepel', '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO unit (id, name, shared, description, creationdate, creationuser, updatedate, updateuser) VALUES (17, 'stuk', TRUE, 'stuk of snede', '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO unit (id, name, shared, description, creationdate, creationuser, updatedate, updateuser) VALUES (18, 'kop', TRUE, 'coffie kop', '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);


#PRODUCT CATEGORY
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (1, 'Fruit', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (2, 'Groenten', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (3, 'Snoep', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (4, 'Vlees', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (5, 'Drank', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (6, 'Kaas', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (7, 'Brood', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (8, 'Beleg', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (9, 'Zuivelproducten', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (10, 'Pasta & rijst', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (11, 'Sauces', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (12, 'Flakes', TRUE, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO productcategory (id, name, shared, creationdate, creationuser, updatedate, updateuser) VALUES (13, 'Vis', TRUE, '2013-03-13 21:41:44', 'INGE', '2013-03-13 21:41:44', 'INGE');



# PRODUCT
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (12, 'light kaas', 17, 3.5, 2, null, 'Maredsous light', TRUE, 'P', 8, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (13, 'boterham', 17, 1, 2.5, null, 'Grijze brood', TRUE, 'P', 7, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (14, 'croissant', 17, 1, 4.5, null, 'Croissant', TRUE, 'P', 7, '2013-03-12 22:44:37', null, '2013-03-12 22:44:37', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (15, 'confituur', 15, 1, 1.5, null, 'Light confituur', TRUE, 'P', 8, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (16, 'thee', 13, 250, 0.5, null, 'thee zonder suiker', TRUE, 'P', 5, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (17, 'croissant confituur', 17, 1, 7.5, null, 'Croissant met light confituur', TRUE, 'R', 7, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (18, 'boterham met kaas', 17, 1, 4.21, null, 'Boterham met Mardesous', TRUE, 'R', 7, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (20, 'yaourth', 13, 20, 2, null, 'Yaourth Bio', TRUE, 'P', 6, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (21, 'Sandwich bio', 17, 1, 5.07, null, 'Sand Bio', TRUE, 'R', 7, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (22, 'Ham', 17, 1, 2, null, 'Snijt ham', TRUE, 'P', 8, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (25, 'bolognese sauce', 13, 500, 14.2, null, 'Bolognese sauce', TRUE, 'R', 11, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (26, 'concasse tomate', 13, 250, 3, null, 'Boite de concassé de tomates', TRUE, 'P', 2, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (27, 'ajuin', 17, 1, 0.5, null, 'Ajuin', TRUE, 'P', 2, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (28, 'Gehackt', 14, 500, 8, null, 'Gehackt vark', TRUE, 'P', 4, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (29, 'Rode wijn', 13, 50, 4, null, 'Kleine fless rode wijn', TRUE, 'P', 5, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (32, 'café', 13, 20, 0, null, 'Café noir', TRUE, 'P', 5, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (33, 'Lait écrémé', 13, 20, 2, null, 'Lait écrémé delhaize', TRUE, 'P', 5, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (34, 'Café lait', 13, 25, 0.5, null, 'café lait', TRUE, 'R', 5, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (37, 'San JF', 17, 1, 7.12, null, 'Sandwich Jambon Fromage', TRUE, 'R', 2, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (38, 'Appel', 17, 1, 0, null, 'Appel', TRUE, 'P', 1, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (39, 'Riz', 14, 100, 3, 7, 'Riz complet', TRUE, 'P', 10, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (40, 'Flakes', 14, 25, 4, null, 'Flakes choco', TRUE, 'P', 12, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (41, 'Sinaasappelsap', 13, 25, 2, null, 'Sinaasappelsap blik', TRUE, 'P', 1, '2013-03-12 22:44:38', null, '2013-03-12 22:44:38', null);
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (42, 'testpro', 15, 12, 10, null, 'testpro20', TRUE, 'P', 8, '2013-03-13 21:22:05', null, '2013-03-13 21:21:59', 'INGE');
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (43, 'Sandwich thon', 17, 1, 17.67, null, 'Sandwich au thon', TRUE, 'R', 7, '2013-03-13 21:40:42', 'INGE', '2013-03-13 21:43:05', 'INGE');
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (44, 'Thon', 14, 30, 2, null, 'Thon nature', TRUE, 'P', 13, '2013-03-13 21:42:03', 'INGE', '2013-03-13 21:42:03', 'INGE');
INSERT INTO product (id, name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationdate, creationuser, updatedate, updateuser) VALUES (45, 'Mayonnaise', 15, 1, 1, null, '1 klp mayonnaise', TRUE, 'P', 11, '2013-03-13 21:42:34', 'INGE', '2013-03-13 21:42:34', 'INGE');

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

#FAVORITES
INSERT INTO FAVORITEPRODUCT (userid, productid) VALUES (1, 16);
INSERT INTO FAVORITEPRODUCT (userid, productid) VALUES (1, 17);
INSERT INTO FAVORITEPRODUCT (userid, productid) VALUES (1, 18);
INSERT INTO FAVORITEPRODUCT (userid, productid) VALUES (2, 40);
INSERT INTO FAVORITEPRODUCT (userid, productid) VALUES (2, 43);
INSERT INTO FAVORITEPRODUCT (userid, productid) VALUES (2, 34);

