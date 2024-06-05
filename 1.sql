SELECT NOW();

SHOW VARIABLES LIKE 'c%';

ALTER TABLE tbl_todo CONVERT TO CHARACTER SET UTF8;
SET CHARACTER SET UTF8;
SET SESSION character_set_database=UTF8;

SELECT SCHEMA_NAME, DEFAULT_character_set_name FROM information_schema.schemata;

CREATE TABLE tbl_member(
	MID VARCHAR(50) PRIMARY KEY,
	mpw VARCHAR(50) NOT NULL ,
	mname VARCHAR(50) NOT NULL
);
INSERT INTO tbl_member VALUES
('user00', '1111', '사용자0'),
('user01', '1111', '사용자1'),
('user02', '1111', '사용자2');



SELECT * FROM tbl_member;


SELECT * FROM tbl_todo;


SELECT * FROM board;

SELECT * FROM notice;