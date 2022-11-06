DROP TABLE USER_ACCOUNT;
DROP TABLE BOOK;
DROP TABLE WANT_BOOK;
DROP TABLE HAS_RENT;
DROP TABLE BOOK_STATUS;
DROP TABLE USER_NOTIFICATION;

CREATE TABLE USER_ACCOUNT(
    accountID NUMBER(10) NOT NULL,
    accountStatus CHAR(1) CHECK (accountStatus in ( 'T', 'F' )) NOT NULL,
    PRIMARY KEY (accountID)
);

CREATE TABLE BOOK(
    bookID NUMBER(15) NOT NULL, 
    ISBN VARCHAR (15) NOT NULL, 
    bookName VARCHAR(100) NOT NULL, 
    author VARCHAR(100) NOT NULL,
    bookCategory VARCHAR(100) NOT NULL,
    PRIMARY KEY (bookID)
);

CREATE TABLE WANT_BOOK(
    accountID NUMBER(10) NOT NULL,
    ISBN VARCHAR (15) NOT NULL, 
    wantTime VARCHAR(10) NOT NULL,
    PRIMARY KEY (ISBN,accountID),
    FOREIGN KEY (accountID) REFERENCES USER_ACCOUNT(accountID)
);

CREATE TABLE HAS_RENT(
    accountID NUMBER(10) NOT NULL,
    bookID NUMBER(15) NOT NULL, 
    PRIMARY KEY (bookID,accountID),
    FOREIGN KEY (bookID) REFERENCES BOOK(bookID),
    FOREIGN KEY (accountID) REFERENCES USER_ACCOUNT(accountID)
);

CREATE TABLE HAS_PLACED(
    accountID NUMBER(10) NOT NULL,
    bookID NUMBER(15) NOT NULL, 
    PRIMARY KEY (bookID,accountID),
    FOREIGN KEY (bookID) REFERENCES BOOK(bookID),
    FOREIGN KEY (accountID) REFERENCES USER_ACCOUNT(accountID)
);

CREATE TABLE BOOK_STATUS(
    bookID NUMBER(15) NOT NULL, 
    statusTime VARCHAR(10) NOT NULL,
    isRent CHAR(1) CHECK (isRent in ( 'T', 'F' )) NOT NULL,
    isAvailable CHAR(1) CHECK (isAvailable in ( 'T', 'F' )) NOT NULL,
    isPlaced CHAR(1) CHECK (isPlaced in ( 'T', 'F' )) NOT NULL,
    FOREIGN KEY (bookID) REFERENCES BOOK(bookID)
);

CREATE TABLE USER_NOTIFICATION(
    noticeID NUMBER(10) NOT NULL,
    accountID NUMBER(10) NOT NULL,
    bookID NUMBER(15), 
    notificationTime VARCHAR(10) NOT NULL,
    userMessage VARCHAR(100) NOT NULL,
    PRIMARY KEY (noticeID),
    FOREIGN KEY (bookID) REFERENCES BOOK(bookID),
    FOREIGN KEY (accountID) REFERENCES USER_ACCOUNT(accountID)
);

INSERT INTO USER_ACCOUNT VALUES (1, 'T');
INSERT INTO USER_ACCOUNT VALUES (2, 'T');
INSERT INTO USER_ACCOUNT VALUES (3, 'T');
INSERT INTO USER_ACCOUNT VALUES (4, 'T');
INSERT INTO USER_ACCOUNT VALUES (5, 'T');
INSERT INTO USER_ACCOUNT VALUES (6, 'F');
INSERT INTO USER_ACCOUNT VALUES (7, 'F');

INSERT INTO BOOK VALUES (1, '0-01', 'The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy');
-- rent
INSERT INTO BOOK VALUES (2, '0-02', 'Harry Potter and the Philosopher Stone', 'J.K. Rowling', 'Fantasy');   
-- placed
INSERT INTO BOOK VALUES (3, '0-03', 'The Hobbit', 'J.R.R. Tolkien', 'Fantasy');
-- available
INSERT INTO BOOK VALUES (4, '0-04', 'The Chronicles of Narnia', 'C.S. Lewis', 'Fantasy');
-- rent
INSERT INTO BOOK VALUES (5, '0-05', 'The Lion, the Witch and the Wardrobe', 'C.S. Lewis', 'Fantasy');
-- placed
INSERT INTO BOOK VALUES (6, '0-06', 'The Little Prince', 'Antoine de Saint-Exupéry', 'Fantasy');
-- available
INSERT INTO BOOK VALUES (7, '0-03', 'The Hobbit', 'J.R.R. Tolkien', 'Fantasy');
-- rent

INSERT INTO WANT_BOOK VALUES (1, '0-05', '2022-11-01'); 
-- 1 want 0-05
INSERT INTO WANT_BOOK VALUES (3, '0-05', '2022-10-30'); 
-- 3 want 0-05
INSERT INTO WANT_BOOK VALUES (1, '0-04', '2022-11-01'); 
-- 1 want 0-04
INSERT INTO WANT_BOOK VALUES (2, '0-04', '2022-10-30'); 
-- 2 want 0-04

INSERT INTO HAS_RENT VALUES (1, 1); 
-- 1 rent 1
INSERT INTO HAS_RENT VALUES (4, 7); 
-- 4 rent 7
INSERT INTO HAS_RENT VALUES (2, 4); 
-- 2 rent 4

INSERT INTO BOOK_STATUS VALUES (1, '2022-11-01', 'T', 'F', 'F');
INSERT INTO BOOK_STATUS VALUES (2, '2022-10-03', 'F', 'F', 'T');
INSERT INTO BOOK_STATUS VALUES (3, '2022-10-20', 'F', 'T', 'F');
INSERT INTO BOOK_STATUS VALUES (4, '2022-10-31', 'T', 'F', 'F');
INSERT INTO BOOK_STATUS VALUES (5, '2022-11-01', 'F', 'F', 'T');
INSERT INTO BOOK_STATUS VALUES (6, '2022-10-28', 'F', 'T', 'F');
INSERT INTO BOOK_STATUS VALUES (7, '2022-11-01', 'T', 'F', 'F');

INSERT INTO USER_NOTIFICATION VALUES (1, 1, 1, '2022-11-01', 'Your book is not available now');
INSERT INTO USER_NOTIFICATION VALUES (2, 1, 2, '2022-10-01', 'Your book is placed now');
INSERT INTO USER_NOTIFICATION VALUES (3, 2, 3, '2022-11-30', 'Your book is available now');

SELECT * FROM USER_ACCOUNT;
SELECT * FROM BOOK;
SELECT * FROM WANT_BOOK;
SELECT * FROM HAS_RENT;
SELECT * FROM BOOK_STATUS;
SELECT * FROM USER_NOTIFICATION;

-- Find the record of the RENT BOOK from bookID is ?
SELECT *
FROM HAS_RENT
WHERE bookID = ?


-- Find the record of the RENT BOOK from accountID is ?
SELECT *
FROM HAS_RENT
WHERE accountID = ?


-- Find the record of the BOOK from ISBN is ?
SELECT *
FROM BOOK
WHERE ISBN = ?

-- Find the record of the BOOK from bookID is ?
SELECT *
FROM BOOK
WHERE bookID = ?

-- Find the record of the BOOK from bookName is ?
SELECT *
FROM BOOK
WHERE bookName = ?

-- Find the record of the BOOK from catogory is ?
SELECT *
FROM BOOK
WHERE category = ?

-- Find the record of the BOOK from the author is ?
SELECT *
FROM BOOK
WHERE author = ?

-- FInd the record of the WANT BOOK from the accountID is ?
SELECT *
FROM WANT_BOOK
WHERE accountID = ?

-- Find the record of the WANT BOOK from the ISBN is ?
SELECT *
FROM WANT_BOOK
WHERE ISBN = ?

-- Find the record of the BOOK STATUS from the bookID is ?
SELECT *
FROM BOOK_STATUS
WHERE bookID = ?

-- Find the record of book reserved(WANT_BOOK) by a user(accountID ?) is already placed on hold(BOOK, BOOK_STATUS) in the library
SELECT *
FROM BOOK_STATUS, WANT_BOOK, BOOK
WHERE BOOK.ISBN = WANT_BOOK.ISBN 
    AND BOOK_STATUS.bookID = BOOK.bookID 
    AND BOOK_STATUS.isPlaced = 'T' 
    AND WANT_BOOK.accountID = ?;


-- -- Find the record of book reserved by a user(accountID) is available in the library
-- SELECT *
-- FROM BOOK_STATUS, WANT_BOOK, BOOK
-- WHERE BOOK.ISBN = WANT_BOOK.ISBN 
--     AND BOOK_STATUS.bookID = BOOK.bookID 
--     AND BOOK_STATUS.isAvailable = 'T' 
--     AND WANT_BOOK.accountID = ?;

-- -- Find the record of book reserved by a user is not available in the library
-- SELECT *
-- FROM BOOK_STATUS, WANT_BOOK, BOOK
-- WHERE BOOK.ISBN = WANT_BOOK.ISBN 
--     AND BOOK_STATUS.bookID = BOOK.bookID 
--     AND BOOK_STATUS.isAvailable = 'F' 
--     AND WANT_BOOK.accountID = ?;

-- -- Find the record of book reserved by a user is not placed on hold in the library
-- SELECT *
-- FROM BOOK_STATUS, WANT_BOOK, BOOK
-- WHERE BOOK.ISBN = WANT_BOOK.ISBN 
--     AND BOOK_STATUS.bookID = BOOK.bookID 
--     AND BOOK_STATUS.isPlaced = 'F' 
--     AND WANT_BOOK.accountID = ?;

-- -- Find the record of book reserved by a user is not rented by a user
-- SELECT *
-- FROM BOOK_STATUS, WANT_BOOK, BOOK
-- WHERE BOOK.ISBN = WANT_BOOK.ISBN 
--     AND BOOK_STATUS.bookID = BOOK.bookID 
--     AND BOOK_STATUS.isRented = 'F' 
--     AND WANT_BOOK.accountID = ?;

-- -- Find the record of book reserved by a user is rented by a user
-- SELECT *
-- FROM BOOK_STATUS, WANT_BOOK, BOOK
-- WHERE BOOK.ISBN = WANT_BOOK.ISBN 
--     AND BOOK_STATUS.bookID = BOOK.bookID 
--     AND BOOK_STATUS.isRented = 'T' 
--     AND WANT_BOOK.accountID = ?;

-- -- Find the record of book reserved by a user is not returned by a user
-- SELECT *
-- FROM BOOK_STATUS, WANT_BOOK, BOOK
-- WHERE BOOK.ISBN = WANT_BOOK.ISBN 
--     AND BOOK_STATUS.bookID = BOOK.bookID 
--     AND BOOK_STATUS.isReturned = 'F' 
--     AND WANT_BOOK.accountID = ?;
