DROP TABLE agree_tb CASCADE CONSTRAINTS;

DROP TABLE answer_tb CASCADE CONSTRAINTS;

DROP TABLE category_tb CASCADE CONSTRAINTS;

DROP TABLE comment_tb CASCADE CONSTRAINTS;

DROP TABLE favorite_tb CASCADE CONSTRAINTS;

DROP TABLE question_tb CASCADE CONSTRAINTS;

DROP TABLE user_tb CASCADE CONSTRAINTS;

CREATE TABLE agree_tb (
  id    VARCHAR2(32 CHAR) NOT NULL,
  point NUMBER(1, 0),
  aid   VARCHAR2(32 CHAR),
  usid  VARCHAR2(32 CHAR),
  PRIMARY KEY (id)
);

CREATE TABLE answer_tb (
  id          VARCHAR2(32 CHAR) NOT NULL,
  agree       NUMBER(10, 0),
  content     CLOB,
  create_time TIMESTAMP,
  disagree    NUMBER(10, 0),
  status      NUMBER(5, 0),
  qid         VARCHAR2(32 CHAR),
  usid        VARCHAR2(32 CHAR),
  PRIMARY KEY (id)
);

CREATE TABLE category_tb (
  id       VARCHAR2(32 CHAR) NOT NULL,
  category VARCHAR2(200 CHAR),
  PRIMARY KEY (id)
);

CREATE TABLE comment_tb (
  id          VARCHAR2(32 CHAR) NOT NULL,
  agree       NUMBER(10, 0),
  content     VARCHAR2(2000 CHAR),
  create_time TIMESTAMP,
  aid         VARCHAR2(32 CHAR),
  qid         VARCHAR2(32 CHAR),
  usid        VARCHAR2(32 CHAR),
  PRIMARY KEY (id)
);

CREATE TABLE favorite_tb (
  id          VARCHAR2(32 CHAR) NOT NULL,
  create_time TIMESTAMP,
  aid         VARCHAR2(32 CHAR),
  qid         VARCHAR2(32 CHAR),
  usid        VARCHAR2(32 CHAR),
  PRIMARY KEY (id)
);

CREATE TABLE question_tb (
  id          VARCHAR2(32 CHAR) NOT NULL,
  content     CLOB,
  create_time TIMESTAMP,
  edit_time   TIMESTAMP,
  status      NUMBER(5, 0),
  title       VARCHAR2(200 CHAR),
  cid         VARCHAR2(32 CHAR),
  usid        VARCHAR2(32 CHAR),
  PRIMARY KEY (id)
);