use easyfit;

ALTER TABLE consumption ADD excercisepoints DECIMAL(10,2) NULL;

INSERT INTO easyfit.product (name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationuser, updateuser) VALUES ('Oefeningen - Medium', 20, 1, -4, null, '', true, 'E', 31, 'HANS', 'HANS');
INSERT INTO easyfit.product (name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationuser, updateuser) VALUES ('Oefeningen - Light', 20, 1, -3, null, '', true, 'E', 31, 'HANS', 'HANS');
INSERT INTO easyfit.product (name, unitid, amount, points, maxpoints, description, shared, classtype, productcategoryid, creationuser, updateuser) VALUES ('Oefeningen - High', 20, 1, -11, null, '', true, 'E', 31, 'HANS', 'HANS');
