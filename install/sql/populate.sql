USE webweights;

DELETE FROM consumptiondetail;
DELETE FROM consumption;
DELETE FROM recipe_product_link;
DELETE FROM product;
DELETE FROM unit;
DELETE FROM user;

INSERT INTO user (username, updatedate, firstname, lastname, targetweight, daypoints, extrapoints) VALUES ('INGE', CURRENT_TIMESTAMP, 'Inge', 'Vanden Nest', 64, 28, 38);
INSERT INTO user (username, updatedate, firstname, lastname, targetweight, daypoints, extrapoints) VALUES ('HANS', CURRENT_TIMESTAMP, 'Hans', 'Fortemaison', 78, 32, 48);

# SCALE
INSERT INTO unit (name, description) VALUES ('ml', 'milliliter');
INSERT INTO unit (name, description) VALUES ('g', 'gramme');
INSERT INTO unit (name, description) VALUES ('klp', 'kleine lepel');
INSERT INTO unit (name, description) VALUES ('glp', 'groote lepel');
INSERT INTO unit (name, description) VALUES ('stuk', 'stuk of snede');
INSERT INTO unit (name, description) VALUES ('kop', 'coffie kop');

#PRODUCT CATEGORY
INSERT INTO productcategory (name) VALUE ('Fruit');
INSERT INTO productcategory (name) VALUE ('Groenten');
INSERT INTO productcategory (name) VALUE ('Snoep');
INSERT INTO productcategory (name) VALUE ('Vlees');
INSERT INTO productcategory (name) VALUE ('Drank');
INSERT INTO productcategory (name) VALUE ('Kaas');
INSERT INTO productcategory (name) VALUE ('Brood');
INSERT INTO productcategory (name) VALUE ('Beleg');

# PRODUCT
INSERT INTO product (name, unitid, amount, point, favorite, description, classtype, productcategoryid)
  VALUES ('light kaas', (SELECT
                           id
                         FROM unit
                         WHERE name = 'stuk'), 3.5, 2.0, TRUE, 'Maredsous light', 'P',
          (SELECT
             ID
           FROM productcategory
           WHERE name = 'Kaas'));

INSERT INTO product (name, unitid, amount, point, favorite, description, classtype, productcategoryid)
  VALUES ('boterham', (SELECT
                         id
                       FROM unit
                       WHERE name = 'stuk'), 1.0, 2.5, TRUE, 'Grijze brood', 'P',
          (SELECT
             ID
           FROM productcategory
           WHERE name = 'Brood')
  );

INSERT INTO product (name, unitid, amount, point, favorite, description, classtype, productcategoryid)
  VALUES ('croissant', (SELECT
                          id
                        FROM unit
                        WHERE name = 'stuk'), 1.0, 4.5, FALSE, 'Croissant', 'P',
          (SELECT
             ID
           FROM productcategory
           WHERE name = 'Brood'));

INSERT INTO product (name, unitid, amount, point, favorite, description, classtype, productcategoryid)
  VALUES ('confituur', (SELECT
                          id
                        FROM unit
                        WHERE name = 'klp'), 1.0, 1.5, FALSE, 'Light confituur', 'P',
          (SELECT
             ID
           FROM productcategory
           WHERE name = 'Beleg'));

INSERT INTO product (name, unitid, amount, point, favorite, description, classtype, productcategoryid)
  VALUES ('thee', (SELECT
                     id
                   FROM unit
                   WHERE name = 'ml'), 250.0, 0.5, FALSE, 'thee zonder suiker', 'P',
          (SELECT
             ID
           FROM productcategory
           WHERE name = 'Drank')
  );

# RECIPE
INSERT INTO product (name, favorite, point, description, classtype, unitid, amount, productcategoryid)
  VALUES ('croissant confituur', TRUE, 4, 'Croissant met light confituur', 'R',
          (SELECT
             ID
           FROM productcategory
           WHERE name = 'Brood'));

INSERT INTO product (name, favorite, point, description, classtype, unitid, amount, productcategoryid)
  VALUES ('boterham met kaas', TRUE, 4, 'Boterham met Mardesous', 'R',
          (SELECT
             ID
           FROM productcategory
           WHERE name = 'Brood'));


# LINK RECIPE - PRODUCT
INSERT INTO recipe_product_link (productid, recipeid, amount) VALUES
((SELECT
    id
  FROM product
  WHERE name = 'croissant'),
 (SELECT
    id
  FROM product
  WHERE name = 'croissant confituur'),
 1);

INSERT INTO recipe_product_link (productid, recipeid, amount) VALUES
((SELECT
    id
  FROM product
  WHERE name = 'confituur'),
 (SELECT
    id
  FROM product
  WHERE name = 'croissant confituur'),
 20);

INSERT INTO recipe_product_link (productid, recipeid, amount) VALUES
((SELECT
    id
  FROM product
  WHERE name = 'light kaas'),
 (SELECT
    id
  FROM product
  WHERE name = 'boterham met kaas'),
 40);

INSERT INTO recipe_product_link (productid, recipeid) VALUES
((SELECT
    id
  FROM product
  WHERE name = 'boterham'),
 (SELECT
    id
  FROM product
  WHERE name = 'boterham met kaas'));


INSERT INTO consumption (user, date) VALUES (1, CURRENT_DATE);

INSERT INTO consumptiondetail (type, amount, productid, consumptionid) VALUES
(
  1,
  2.0,
  (SELECT
     id
   FROM product
   WHERE name = 'croissant'),
  (SELECT
     id
   FROM consumption
   WHERE user = 1 AND date = CURRENT_DATE)
);

INSERT INTO consumptiondetail (type, amount, productid, consumptionid) VALUES
(
  3,
  2.0,
  (SELECT
     id
   FROM product
   WHERE name = 'boterham'),
  (SELECT
     id
   FROM consumption
   WHERE user = 1 AND date = CURRENT_DATE)
);

INSERT INTO consumptiondetail (type, amount, productid, consumptionid) VALUES
(
  3,
  2.0,
  (SELECT
     id
   FROM product
   WHERE name = 'boterham met kaas'),
  (SELECT
     id
   FROM consumption
   WHERE user = 1 AND date = CURRENT_DATE)
);