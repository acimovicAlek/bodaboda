CREATE DATABASE db;
GRANT ALL PRIVILEGES ON db.* TO 'user'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON db.* TO 'user'@'localhost' IDENTIFIED BY 'password';
USE mydb
