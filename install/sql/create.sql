USE webweight;

CREATE TABLE user
(
  username   CHAR(20) PRIMARY KEY NOT NULL,
  updatedate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX username ON user (username);

CREATE TABLE unit
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name        CHAR(5)         NOT NULL,
  description VARCHAR(255)
);

CREATE TABLE product
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name        CHAR(255)       NOT NULL,
  unitid      INT             NOT NULL,
  amount      DECIMAL(10, 2),
  point       DECIMAL(10, 2),
  description VARCHAR(255),
  favorite    BIT DEFAULT 0,

  FOREIGN KEY (unitid) REFERENCES unit (id)
);


CREATE TABLE recipe
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name        CHAR(255)       NOT NULL,
  point       DECIMAL(10, 2),
  favorite    BIT DEFAULT 0   NOT NULL,
  description VARCHAR(255)
);

CREATE TABLE recipe_product_link
(
  id        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  recipeid  INT             NOT NULL,
  productid INT             NOT NULL,
  amount    DECIMAL(10, 2),
  PRIMARY KEY (id),
  FOREIGN KEY (productid) REFERENCES product (id),
  FOREIGN KEY (recipeid) REFERENCES recipe (id)
);

CREATE TABLE consumption
(
  id     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user   CHAR(20),
  date   DATE            NOT NULL,
  points DECIMAL(10, 2),
  FOREIGN KEY (user) REFERENCES user (username)
);

CREATE TABLE consumptiondetail
(
  id            INT PRIMARY KEY    NOT NULL AUTO_INCREMENT,
  type          SMALLINT DEFAULT 0 NOT NULL,
  amount        DOUBLE,
  productid     INT                NOT NULL,
  consumptionid INT                NOT NULL,
  FOREIGN KEY (consumptionid) REFERENCES consumption (id),
  FOREIGN KEY (productid) REFERENCES product (id)
);


