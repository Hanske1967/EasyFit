CREATE DATABASE easyfit;

USE easyfit;

CREATE TABLE user
(
  id           INT PRIMARY KEY                     NOT NULL AUTO_INCREMENT,
  username     CHAR(20)                            NOT NULL,
  password     CHAR(28),
  firstname    CHAR(30),
  lastname     CHAR(100),
  targetweight INT,
  daypoints    INT,
  extrapoints  INT DEFAULT 0                       NOT NULL,
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20),
  updatedate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser   CHAR(20)
);
CREATE UNIQUE INDEX UK_user_username ON user (username);

CREATE TABLE unit
(
  id           INT PRIMARY KEY                     NOT NULL AUTO_INCREMENT,
  name         CHAR(5)                             NOT NULL,
  description  VARCHAR(255),
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20),
  updatedate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser   CHAR(20)
);

CREATE TABLE productcategory
(
  id           INT PRIMARY KEY                     NOT NULL AUTO_INCREMENT,
  name         CHAR(50)                            NOT NULL,
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20),
  updatedate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser   CHAR(20)
);
CREATE UNIQUE INDEX name_UNIQUE ON productcategory (name);

CREATE TABLE product
(
  id                INT PRIMARY KEY                     NOT NULL AUTO_INCREMENT,
  name              CHAR(255)                           NOT NULL,
  unitid            INT                                 NOT NULL,
  amount            DECIMAL(10, 2),
  points            DECIMAL(10, 2),
  maxpoints         INT,
  description       VARCHAR(255),
  favorite          BIT DEFAULT b'0',
  classtype         CHAR(1) DEFAULT 'P'                 NOT NULL,
  productcategoryid INT,
  creationdate      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser      CHAR(20),
  updatedate        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser        CHAR(20),
  FOREIGN KEY (unitid) REFERENCES unit (id),
  FOREIGN KEY (productcategoryid) REFERENCES productcategory (id)
);

CREATE TABLE recipe_product_link
(
  id           INT PRIMARY KEY                     NOT NULL AUTO_INCREMENT,
  recipeid     INT                                 NOT NULL,
  productid    INT                                 NOT NULL,
  amount       DECIMAL(10, 2),
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20),
  updatedate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser   CHAR(20),
  FOREIGN KEY (productid) REFERENCES product (id),
  FOREIGN KEY (recipeid) REFERENCES product (id)
);

CREATE TABLE consumption
(
  id           INT PRIMARY KEY                     NOT NULL AUTO_INCREMENT,
  userid       INT,
  date         DATE                                NOT NULL,
  points       DECIMAL(10, 2),
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20),
  updatedate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser   CHAR(20),
  FOREIGN KEY (userid) REFERENCES user (id)
);


CREATE TABLE consumptiondetail
(
  id            INT PRIMARY KEY                     NOT NULL AUTO_INCREMENT,
  type          SMALLINT DEFAULT 0                  NOT NULL,
  amount        DOUBLE,
  productid     INT                                 NOT NULL,
  consumptionid INT                                 NOT NULL,
  creationdate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser  CHAR(20),
  updatedate    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser    CHAR(20),
  FOREIGN KEY (consumptionid) REFERENCES consumption (id),
  FOREIGN KEY (productid) REFERENCES product (id)
);


