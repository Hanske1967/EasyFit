CREATE TABLE weigth
(
  id           INT PRIMARY KEY                     NOT NULL AUTO_INCREMENT,
  userid       INT                                 NOT NULL,
  date         DATE                                NOT NULL,
  weight       DOUBLE                              NOT NULL,
  creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  creationuser CHAR(20)                            NOT NULL,
  updatedate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updateuser   CHAR(20)                            NOT NULL,
  FOREIGN KEY (userid) REFERENCES user (id)
);
