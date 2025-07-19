CREATE DATABASE HibernateDB;
USE HibernateDB;

CREATE TABLE IF NOT EXISTS Student(
	rollNo INT PRIMARY KEY,
    name VARCHAR(35),
    marks DOUBLE
);

SELECT * FROM Stlaptopudent;
SELECT * FROM Laptop; 	

DELETE FROM Student WHERE rollNo = 103;

UPDATE Student SET marks = 100 WHERE rollNo = 106;