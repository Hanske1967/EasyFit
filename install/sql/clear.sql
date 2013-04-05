USE easyfit;

DELETE FROM consumptiondetail;
DELETE FROM consumption;
DELETE FROM favoriteproduct;
DELETE FROM recipe_product_link;
DELETE FROM product;

ALTER TABLE productcategory DROP FOREIGN KEY FK_productcategory_productcategory_id;
DELETE FROM productcategory;
ALTER TABLE productcategory ADD CONSTRAINT FK_productcategory_productcategory_id FOREIGN KEY (parentid) REFERENCES productcategory (id)
  ON DELETE RESTRICT;

DELETE FROM unit;
DELETE FROM user;
