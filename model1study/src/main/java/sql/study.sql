-- /src/java/sql/study.sql

CREATE TABLE MEMBER (
	id VARCHAR(20) PRIMARY KEY,
	pass VARCHAR(20),
	NAME VARCHAR(20),
	gender INT(1),
	tel VARCHAR(15),
	email VARCHAR(50),
	picture VARCHAR(200)
)

create table book (
   no int AUTO_INCREMENT primary key,
   writer varchar(50),
   title varchar(100),
   content varchar(2000),
   regdate datetime
)
