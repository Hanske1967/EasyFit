DROP TABLE IF EXISTS "weigth";
DROP TABLE IF EXISTS "consumptiondetail";
DROP TABLE IF EXISTS "consumption";
DROP TABLE IF EXISTS "recipe_product_link";
DROP TABLE IF EXISTS "favoriteproduct";
DROP TABLE IF EXISTS "product";
DROP TABLE IF EXISTS "productcategory";
DROP TABLE IF EXISTS "unit";
DROP TABLE IF EXISTS "users";

CREATE TABLE "users"
(
  id SERIAL PRIMARY KEY,
  username CHAR(20) NOT NULL UNIQUE,
  password CHAR(28),
  firstname CHAR(30),
  lastname CHAR(100),
  targetweight INT,
  daypoints INT,
  extrapoints INT DEFAULT 0 NOT NULL,
  language CHAR(2),
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20),
  updatedate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser CHAR(20)
);


CREATE TABLE "unit"
(
  id           SERIAL                              PRIMARY KEY,
  name         CHAR(5)                             NOT NULL,
  shared       BOOLEAN DEFAULT TRUE                NOT NULL,
  description  VARCHAR(255),
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20),
  updatedate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser   CHAR(20)
);

CREATE TABLE "productcategory"
(
  id           SERIAL                              PRIMARY KEY,
  name         CHAR(50)                            NOT NULL UNIQUE,
  shared       BOOLEAN DEFAULT TRUE                NOT NULL,
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20),
  updatedate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser   CHAR(20)
);

CREATE TABLE "product"
(
  id           SERIAL                              PRIMARY KEY,
  name              CHAR(255)                           NOT NULL,
  unitid            INTEGER       NOT NULL,
  amount            DECIMAL(10, 2),
  points            DECIMAL(10, 2),
  maxpoints         INT,
  description       VARCHAR(255),
  shared            BOOLEAN DEFAULT TRUE                NOT NULL,
  classtype         CHAR(1) DEFAULT 'P'                 NOT NULL,
  productcategoryid INTEGER        NOT NULL,
  creationdate      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser      CHAR(20),
  updatedate        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser        CHAR(20),
  FOREIGN KEY (unitid) REFERENCES unit (id),
  FOREIGN KEY (productcategoryid) REFERENCES productcategory (id)
);

CREATE TABLE favoriteproduct
(
  id           SERIAL                              PRIMARY KEY,
  productid INT              NOT NULL,
  userid    INT              NOT NULL,
  FOREIGN KEY (productid) REFERENCES product (id),
  FOREIGN KEY (userid) REFERENCES "users" (id)
);
CREATE UNIQUE INDEX UK_favoriteproduct ON favoriteproduct (productid, userid);

CREATE TABLE recipe_product_link
(
  id           SERIAL                              PRIMARY KEY,
  recipeid     INTEGER                                 NOT NULL,
  productid    INTEGER                                 NOT NULL,
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
  id           SERIAL                              PRIMARY KEY,
  userid INTEGER,
  date DATE NOT NULL,
  points DECIMAL(10,2),
  excercisepoints DECIMAL(10,2),
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20),
  updatedate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser CHAR(20),
  FOREIGN KEY ( userid ) REFERENCES users ( id )
);

CREATE TABLE consumptiondetail
(
  id           SERIAL                              PRIMARY KEY,
  type          SMALLINT DEFAULT 0                  NOT NULL,
  amount        NUMERIC,
  productid     INTEGER                                 NOT NULL,
  consumptionid INTEGER                                 NOT NULL,
  creationdate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser  CHAR(20),
  updatedate    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser    CHAR(20),
  FOREIGN KEY (consumptionid) REFERENCES consumption (id),
  FOREIGN KEY (productid) REFERENCES product (id)
);

CREATE TABLE weight
(
  id           SERIAL                              PRIMARY KEY,
  userid       INTEGER                                 NOT NULL,
  date         DATE                                NOT NULL,
  weight       NUMERIC                              NOT NULL,
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20)                            NOT NULL,
  updatedate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser   CHAR(20)                            NOT NULL,
  FOREIGN KEY (userid) REFERENCES users (id)
);
