USE webweights;

DELETE FROM consumptiondetail;
DELETE FROM consumption;
DELETE FROM recipe_product_link;
DELETE FROM product;
DELETE FROM unit;
DELETE FROM user;


INSERT INTO user (username, updatedate) VALUES (1, CURRENT_TIMESTAMP);
INSERT INTO user (username, updatedate) VALUES ('HANS', CURRENT_TIMESTAMP);

# SCALE
INSERT INTO unit (name, description) VALUES ('ml', 'milliliter');
INSERT INTO unit (name, description) VALUES ('g', 'gramme');
INSERT INTO unit (name, description) VALUES ('klp', 'kleine lepel');
INSERT INTO unit (name, description) VALUES ('glp', 'groote lepel');
INSERT INTO unit (name, description) VALUES ('stuk', 'stuk of snede');
INSERT INTO unit (name, description) VALUES ('kop', 'coffie kop');

# PRODUCT
INSERT INTO product (name, unitid, amount, point, favorite, description, classtype)
  VALUES ('light kaas', (SELECT
                           id
                         FROM unit
                         WHERE name = 'stuk'), 3.5, 2.0, TRUE, 'Maredsous light', 'P');

INSERT INTO product (name, unitid, amount, point, favorite, description, classtype)
  VALUES ('boterham', (SELECT
                         id
                       FROM unit
                       WHERE name = 'stuk'), 1.0, 2.5, TRUE, 'Grijze brood', 'P');

INSERT INTO product (name, unitid, amount, point, favorite, description, classtype)
  VALUES ('croissant', (SELECT
                          id
                        FROM unit
                        WHERE name = 'stuk'), 1.0, 4.5, FALSE, 'Croissant', 'P');

INSERT INTO product (name, unitid, amount, point, favorite, description, classtype)
  VALUES ('confituur', (SELECT
                          id
                        FROM unit
                        WHERE name = 'klp'), 1.0, 1.5, FALSE, 'Light confituur', 'P');

INSERT INTO product (name, unitid, amount, point, favorite, description, classtype)
  VALUES ('thee', (SELECT
                     id
                   FROM unit
                   WHERE name = 'ml'), 250.0, 0.5, FALSE, 'thee zonder suiker', 'P');

# RECIPE
INSERT INTO product (name, favorite, point, description, classtype, unitid, amount) VALUES ('croissant confituur', TRUE, 4, 'Croissant met light confituur', 'R', (SELECT
                                                                                                                                                                     id
                                                                                                                                                                   FROM
                                                                                                                                                                     unit
                                                                                                                                                                   WHERE
                                                                                                                                                                     name
                                                                                                                                                                     =
                                                                                                                                                                     'stuk'), 1.0);
INSERT INTO product (name, favorite, point, description, classtype, unitid, amount) VALUES ('boterham met kaas', TRUE, 4, 'Boterham met Mardesous', 'R', (SELECT
                                                                                                                                                            id
                                                                                                                                                          FROM
                                                                                                                                                            unit
                                                                                                                                                          WHERE
                                                                                                                                                            name
                                                                                                                                                            =
                                                                                                                                                            'ml'), 1.0);

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