SPOOL project.out
SET ECHO ON
/*
CIS 353 - Database Design Project
Alfred Natzic
James Lund
Johnathon Killeen
Quinn Meagher
Zachary Thomas
*/
/*
< The SQL/DDL code that creates your schema goes here >
In the DDL, every IC must have a unique name; e.g. IC5, IC10, IC15, etc.
*/
--
-- Drop all tables in case changes are made
DROP TABLE Director CASCADE CONSTRAINTS;
DROP TABLE Rating_Agency CASCADE CONSTRAINTS;
DROP TABLE Revenue_History CASCADE CONSTRAINTS;
DROP TABLE Movie CASCADE CONSTRAINTS;
DROP TABLE Book CASCADE CONSTRAINTS;
DROP TABLE Actor CASCADE CONSTRAINTS;
DROP TABLE ActedIn CASCADE CONSTRAINTS;
DROP TABLE DirectedBy CASCADE CONSTRAINTS;
DROP TABLE Ratings CASCADE CONSTRAINTS;
DROP TABLE Genre CASCADE CONSTRAINTS;
--
-- These lines create the tables
CREATE TABLE Director (
	dID INTEGER PRIMARY KEY,
	dname CHAR(15)
);
--
CREATE TABLE Rating_Agency (
	agencyName char(15) PRIMARY KEY
);
--
CREATE TABLE Movie (
	mID INTEGER,
	title CHAR(35),
	genre CHAR(15),
	inCinema CHAR(3),
	year INTEGER,
	--IC1: Movie
	CONSTRAINT IC1 PRIMARY KEY (mID),
	--IC5: inCinema can only be 'yes' or 'no'
	CONSTRAINT IC5 CHECK(inCinema IN('yes', 'no')),
	--IC4: If a movie is in a inCinema, it needs to have been in the last 2 years
	CONSTRAINT IC4 CHECK(NOT (inCinema = 'no' AND (year > 2016)))
);
--
CREATE TABLE Revenue_History (
	rID INTEGER,
	revenue INTEGER,
	totalDaysInTheater INTEGER,
	--IC2: Revenue referencs movieID
	CONSTRAINT IC2 FOREIGN KEY (rID) REFERENCES Movie(mID)
);
--
CREATE TABLE Book (
	ISBN INTEGER,
	mID INTEGER,
	title CHAR(35),
	mainAuthor CHAR(15),
	CONSTRAINT IC16 PRIMARY KEY (ISBN, mID),
	CONSTRAINT IC6 FOREIGN KEY (mID) references Movie(mID)
);
--
CREATE TABLE Actor (
	aID INTEGER PRIMARY KEY,
	gender CHAR(1),
	name CHAR(15),
	--IC3: Actor is either a M/F
	CONSTRAINT IC3 CHECK (gender IN('M', 'F'))
);
--
CREATE TABLE ActedIn(
	mID INTEGER,
	aID INTEGER,
	paid INTEGER,
	role CHAR(15),
	nameInMovie CHAR(15),
	CONSTRAINT IC11 PRIMARY KEY (mID, aID),
	CONSTRAINT IC7 FOREIGN KEY (mID) references Movie(mID)
);
--
CREATE TABLE DirectedBy(
	dID INTEGER,
	mID INTEGER,
	salary INTEGER,
	CONSTRAINT IC12 PRIMARY KEY (dID, mID),
	CONSTRAINT IC8 FOREIGN KEY (mID) references Movie(mID)
);
--
CREATE TABLE Ratings(
	agencyName CHAR(15),
	mID INTEGER,
	rating NUMBER(2),
	CONSTRAINT IC13 PRIMARY KEY (agencyName, mID),
	CONSTRAINT IC9 FOREIGN KEY (agencyName) references Rating_Agency(agencyName),
	CONSTRAINT IC10 FOREIGN KEY (mID) references Movie(mID)
);
--
CREATE TABLE Genre(
	mID INTEGER,
	genre CHAR(15),
	CONSTRAINT IC14 PRIMARY KEY (mID, genre),
	CONSTRAINT IC15 FOREIGN KEY (mID) references Movie(mID)
);
--
--
--
SET FEEDBACK OFF
/*
INSERT statements goes here
Important: Keep the number of rows in each table small enough so that the results of your queries can be verified by hand. See the Sailors database as an example.
*/
--
-- Insert Director rows
-- dID, dname
INSERT INTO Director VALUES(40157, 'Mr Wison');
INSERT INTO Director VALUES(12345, 'Marc Webb');
INSERT INTO Director VALUES(58931, 'Nelly Horse');
INSERT INTO Director VALUES(34212, 'Wruce Billis');
--
-- Insert Rating_Agency rows
-- agencyName
INSERT INTO Rating_Agency VALUES('IMDB');
INSERT INTO Rating_Agency VALUES('Rotten Tomatoes');
--
-- Insert Movie rows
-- mID, title, genre, inCinema, year
INSERT INTO Movie VALUES(1234567, 'The Amazing Spider-Man', 'Action', 'no', '2012');
INSERT INTO Movie VALUES(12345, 'Cool title 2', 'Adventure', 'yes', '2018');
INSERT INTO Movie VALUES(99999, 'Dramatic Drama', 'Drama', 'no', '2010');
INSERT INTO Movie VALUES(86753, 'Pirate Movie', 'Adventure', 'no', '1998');
INSERT INTO Movie VALUES(00093, 'Too Fast Too Furious', 'Action', 'yes', '2303');
--
-- Insert Revenue_History rows
-- rID, revenue, totalDaysInTheater
INSERT INTO Revenue_History VALUES(1234567, 100000000, 300);
INSERT INTO Revenue_History VALUES(86753, 1345000, 189);
INSERT INTO Revenue_History VALUES(99999, 18375, 1002);
INSERT INTO Revenue_History VALUES(12345, 999999999, 34);
INSERT INTO Revenue_History VALUES(00093, 79870987, 1030);
--
-- Insert Book rows
-- ISBN, mID, title, mainAuthor
INSERT INTO Book VALUES(1334565435654, 1234567, 'What an Amazing Spiderman!', 'Pete Lunz');
INSERT INTO Book VALUES(8476039485762, 99999, 'Forbaden Love', 'Dr Pepper');
INSERT INTO Book VALUES(0987678987654, 00093, 'Need For Spood', 'Ricky Bobby');
---
--- Insert Actor rows
--- aID, gender, name
INSERT INTO Actor VALUES(892, 'M', 'Donny Jepp');
INSERT INTO Actor VALUES(745, 'F', 'Bella Button');
INSERT INTO Actor VALUES(137, 'M', 'Man Manson');
INSERT INTO Actor VALUES(871, 'M', 'Pan Handler');
INSERT INTO Actor VALUES(209, 'F', 'Naomi Bakon');
INSERT INTO Actor VALUES(152, 'F', 'Ingrid Fail');
INSERT INTO Actor VALUES(361, 'M', 'Jessie Jam');
INSERT INTO Actor VALUES(596, 'F', 'Lola Rat');
INSERT INTO Actor VALUES(222, 'M', 'Quarter Penny');
--
-- Insert ActedIn rows
-- mID, aID, paid, role, nameInMovie
INSERT INTO ActedIn VALUES(86753, 745, 59000, 'Side', 'Cat Fisher');
INSERT INTO ActedIn VALUES(86753, 892, 120000, 'Lead', 'John Crow');
INSERT INTO ActedIn VALUES(1234567, 127, 145000, 'Lead', 'Spoderman');
INSERT INTO ActedIn VALUES(1234567, 871, 78900, 'Antagonist', 'Blue Goblin');
INSERT INTO ActedIn VALUES(12345, 361, 182000, 'Lead', 'Cool Guy');
INSERT INTO ActedIn VALUES(12345, 152, 124000, 'Antagonist', 'The Uncooler');
INSERT INTO ActedIn VALUES(12345, 209, 90000, 'Sidekick', 'Extra Bacon');
INSERT INTO ActedIn VALUES(12345, 892, 90000, 'Minor Villan', 'Unnamed Extra');
INSERT INTO ActedIn VALUES(99999, 871, 50, 'Lead', 'Ryan Renolds');
INSERT INTO ActedIn VALUES(99999, 745, 300, 'Lead', 'Betty White');
INSERT INTO ActedIn VALUES(00093, 222, 555000, 'Lead', 'Optimal Fuel');
INSERT INTO ActedIn VALUES(00093, 596, 300000, 'Sidekick', 'Max Spood');
--
-- Insert DirectedBy rows
-- dID, mID, salary
INSERT INTO DirectedBy VALUES(40157, 86753, 75000);
INSERT INTO DirectedBy VALUES(40157, 1234567, 567000);
INSERT INTO DirectedBy VALUES(58931, 99999, 8000);
INSERT INTO DirectedBy VALUES(12345, 12345, 1239080);
INSERT INTO DirectedBy VALUES(34212, 00093, 130000);
---
--- Insert Ratings rows
--- agencyName, mID, rating
INSERT INTO Ratings VALUES('IMDB', 1234567, 3);
INSERT INTO Ratings VALUES('Rotten Tomatoes', 1234567, 5);
INSERT INTO Ratings VALUES('IMDB', 99999, 1);
INSERT INTO Ratings VALUES('Rotten Tomatoes', 99999, 2);
INSERT INTO Ratings VALUES('IMDB',86753, 9);
INSERT INTO Ratings VALUES('Rotten Tomatoes', 86753, 9);
INSERT INTO Ratings VALUES('IMDB',12345, 10);
INSERT INTO Ratings VALUES('Rotten Tomatoes', 12345, 10);
INSERT INTO Ratings VALUES('IMDB',00093, 7);
INSERT INTO Ratings VALUES('Rotten Tomatoes', 00093, 7);
--
-- Insert Genre rows
-- mID, genre
INSERT INTO Genre VALUES(1234567, 'Action');
INSERT INTO Genre VALUES(99999, 'Drama');
INSERT INTO Genre VALUES(00093, 'Action');
INSERT INTO Genre VALUES(12345, 'Adventure');
INSERT INTO Genre VALUES(86753, 'Adventure');
--
--
SET FEEDBACK ON
COMMIT;
--
/*
<One query (per table) of the form: SELECT * FROM table in order to print out your database >
*/
SELECT * FROM Director;
SELECT * FROM Rating_Agency;
SELECT * FROM Revenue_History;
SELECT * FROM Movie;
SELECT * FROM Book;
SELECT * FROM Actor;
SELECT * FROM ActedIn;
SELECT * FROM DirectedBy;
SELECT * FROM Ratings;
--
/*
<The SQL query Include the following for each query:
1. A comment line stating the query number and the feature(s) it demonstrates
2. A comment line stating the query in English.
3. The SQL code for the query
*/
--
--1. A join involving at least four relations.
--Lists all movies that have been based off of books, shows director and revenue
SELECT M.title, D.dname, B.title, R.revenue
FROM Movie M, Director D, Book B, Revenue_History R, DirectedBy DB
WHERE M.mID = B.mID AND DB.dID = D.dID AND R.rID = M.mID AND DB.mID = M.mID
ORDER BY M.title;
--
--2. A self-join.
--Finds pairs of actors that made the same amount of money on a movie
SELECT A.aID, A2.aID
FROM ActedIn A, ActedIn A2
WHERE A.paid = A2.paid AND
			A.mID = A2.mID AND
			A.aID < A2.aID;
--3. UNION, INTERSECT, and/or MINUS.
--Find the movie ID and title of all movies that have revenue over $200,000 and have less then 1000 days in Theatre.
SELECT M.mID, M.title
FROM Movie M, Revenue_History RA
WHERE RA.revenue > 200000
MINUS
SELECT M.mID, M.title
FROM Movie M, Revenue_History RA
WHERE M.mID = RA.rID AND RA.totalDaysInTheater > 1000;
--
--4. SUM, AVG, MAX, and/or MIN.
-- Max amount of revenue made by a movie in our database
SELECT MAX(R.revenue)
FROM Revenue_History R;

--5. GROUP BY, HAVING, and ORDER BY, all appearing in the same query
--List all Directors who have made more than one movie
SELECT D.dname, COUNT(*)
FROM Director D, DirectedBy DB
WHERE D.dID = DB.dID
GROUP BY D.dname
HAVING COUNT(*) > 1
ORDER BY D.dname;
--
--6. A correlated subquery.
--Finds all movies who made more than average despite running for less than the
--average days in theater
SELECT M.title
FROM Movie M, Revenue_History R
WHERE M.mID = R.rID AND R.revenue >
	(SELECT AVG(revenue)
	 FROM Revenue_History
 	 WHERE totalDaysInTheater < 200);
--
--7. A non-correlated subquery.
--Finds all movies that are not based off of books
SELECT M.title
FROM Movie M
WHERE M.mID NOT IN (SELECT B.mID
										FROM Book B);
--
--8. A relational DIVISION query.
--
SELECT M.title
FROM Movie M
WHERE NOT EXISTS((SELECT G.genre
 FROM Genre G
 WHERE G.genre = 'Action')
 MINUS
 (SELECT G.genre
 FROM Genre G
 WHERE G.genre = 'Action' AND
			 G.mID = M.mID));
--
--9. An outer join query.
--Joins the Movie and Revenue_History tables
SELECT M.title, R.revenue
FROM Movie M
FULL OUTER JOIN Revenue_History R ON M.mID = R.rID;
--
--10. A RANK query.
--Display the rank of movies with rating 10
SELECT RANK(10) WITHIN GROUP
				(ORDER BY RA.rating) "Rank of rating 10"
FROM Ratings RA;
--11. A Top-N query.
--List the movie ID, title of the four highest revenue movies.
SELECT  mID, title
FROM (SELECT * FROM Movie M, Revenue_History R WHERE M.mID = R.rID ORDER BY R.revenue)
WHERE ROWNUM < 5;
--
--
/*
The insert/delete/update statements to test the enforcement of ICs
Include the following items for every IC that you test (Important: see the next section titled Submit a final report regarding which ICs to test)

--
- A comment line stating: Testing: <IC name>
- A SQL INSERT, DELETE, or UPDATE that will test the IC.
*/
COMMIT;
--
SPOOL OFF
