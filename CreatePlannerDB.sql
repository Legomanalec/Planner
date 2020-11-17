DROP DATABASE IF EXISTS PlannerDB;
CREATE DATABASE PlannerDB;
USE PlannerDB;
CREATE TABLE CellData(
cid int NOT NULL,
cellText text,
cellDay int, 
cellMonth int, 
cellYear int,
isComplete bool,
PRIMARY KEY(cid)
);

DROP USER IF EXISTS 'planneruser';
FLUSH PRIVILEGES;
CREATE USER 'planneruser' IDENTIFIED BY 'planner1234';
GRANT ALL PRIVILEGES ON * . * TO 'planneruser';