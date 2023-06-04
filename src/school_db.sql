create database ensao; 
use ensao;
CREATE TABLE students (
  id_student INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fname VARCHAR(45) NOT NULL,
  lname VARCHAR(45) NOT NULL,
  age INT NOT NULL,
  cin VARCHAR(45) NOT NULL UNIQUE,
  class_grade VARCHAR(200) NOT NULL,
  email VARCHAR(100) NOT NULL,
  infos VARCHAR(2000) NOT NULL,
  image VARCHAR(2000) NOT NULL
);

select * from students;

