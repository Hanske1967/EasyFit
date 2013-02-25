USE webweights;

INSERT INTO user (username, updatedate) VALUES ('INGE', CURRENT_TIMESTAMP);
INSERT INTO user (username, updatedate) VALUES ('HANS', CURRENT_TIMESTAMP);

# SCALE
INSERT INTO unit (name, description) VALUES ('ml', 'milliliter');
INSERT INTO unit (name, description) VALUES ('g', 'gramme');
INSERT INTO unit (name, description) VALUES ('klp', 'kleine lepel');
INSERT INTO unit (name, description) VALUES ('glp', 'groote lepel');
INSERT INTO unit (name, description) VALUES ('stuk', 'stuk of snede');
INSERT INTO unit (name, description) VALUES ('kop', 'coffie kop');

# PRODUCT
INSERT INTO product (name, unitid, amount, point, favorite, description)
  VALUES ('light kaas', (SELECT
                           id
                         FROM unit
                         WHERE name = 'stuk'), 3.5, 2.0, TRUE, 'Maredsous light');

INSERT INTO product (name, unitid, amount, point, favorite, description)
  VALUES ('boterham', (SELECT
                         id
                       FROM unit
                       WHERE name = 'stuk'), 1.0, 2.5, TRUE, 'Grijze brood');

INSERT INTO product (name, unitid, amount, point, favorite, description)
  VALUES ('croissant', (SELECT
                          id
                        FROM unit
                        WHERE name = 'stuk'), 1.0, 4.5, FALSE, 'Croissant');

INSERT INTO product (name, unitid, amount, point, favorite, description)
  VALUES ('confituur', (SELECT
                          id
                        FROM unit
                        WHERE name = 'klp'), 1.0, 1.5, FALSE, 'Light confituur');

INSERT INTO product (name, unitid, amount, point, favorite, description)
  VALUES ('thee', (SELECT
                     id
                   FROM unit
                   WHERE name = 'ml'), 250.0, 0.5, FALSE, 'thee zonder suiker');

# RECIPE
INSERT INTO recipe (name, favorite, point, description) VALUES ('croissant confituur', TRUE, 4, 'Croissant met light confituur');
INSERT INTO recipe (name, favorite, point, description) VALUES ('boterham met kaas', TRUE, 4, 'Boterham met Mardesous');

# LINK RECIPE - PRODUCT
INSERT INTO recipe_product_link (productid, recipeid, amount) VALUES
((SELECT
    id
  FROM product
  WHERE name = 'croissant'),
 (SELECT
    id
  FROM recipe
  WHERE name = 'croissant confituur'),
 1);

INSERT INTO recipe_product_link (productid, recipeid, amount) VALUES
((SELECT
    id
  FROM product
  WHERE name = 'confituur'),
 (SELECT
    id
  FROM recipe
  WHERE name = 'croissant confituur'),
 20);

INSERT INTO recipe_product_link (productid, recipeid, amount) VALUES
((SELECT
    id
  FROM product
  WHERE name = 'light kaas'),
 (SELECT
    id
  FROM recipe
  WHERE name = 'boterham met kaas'),
 40);

INSERT INTO recipe_product_link (productid, recipeid) VALUES
((SELECT
    id
  FROM product
  WHERE name = 'boterham'),
 (SELECT
    id
  FROM recipe
  WHERE name = 'boterham met kaas'));


INSERT INTO consumption (user, date) VALUES ('INGE', CURRENT_DATE);

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
   WHERE user = 'INGE' AND date = CURRENT_DATE)
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
   WHERE user = 'INGE' AND date = CURRENT_DATE)
);
