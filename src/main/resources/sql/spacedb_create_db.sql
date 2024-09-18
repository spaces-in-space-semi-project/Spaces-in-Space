-- root 계정으로 로그인 후 실행 : 

use mysql;

CREATE DATABASE spacedb;
SHOW DATABASES;

CREATE USER 'space'@'%' IDENTIFIED BY  'space';
SELECT * FROM user;

GRANT ALL PRIVILEGES ON spacedb.* TO 'space'@'%';
SHOW GRANTS FOR 'space'@'%';

use spacedb;