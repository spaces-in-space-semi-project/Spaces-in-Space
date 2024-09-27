-- root 계정으로 로그인 후 실행 : 

use mysql;

CREATE DATABASE spacedb;
SHOW DATABASES;

CREATE USER 'space'@'%' IDENTIFIED BY  'space';
SELECT * FROM userfile:/C:/mySIS/Spaces-in-Space/src/main/resources/sql/spacedb_build_script.sql;

GRANT ALL PRIVILEGES ON spacedb.* TO 'space'@'%';
SHOW GRANTS FOR 'space'@'%';

use spacedb;