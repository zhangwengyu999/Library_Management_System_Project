### COMP2411 Database Systems 
### Library Management System (LMS) Group Project 
### First Stage 

#### Group 4 

- CHEN Derun (21098424d)
- JIANG Guanlin (21093962d)
- KWOK Hin Chi (20060241d) 
- LIU Minghao (21096308d)
- YE Haowen (21098829d)
- ZHANG Wengyu (21098431d) 

---

##### ER Diagram

<center><img src="ER.png" width="80%"></img></center>

*Assumptions*: 

- The user who has not rented any book will not be listed in the HAS_RENT relation table; 

- Each user is allowed to reserve at most eight books in the system, and each reserved book must have a unique Want_ISBN; 

- Each book has its unique Book_ID to distinct books. And the ISBN is not unique because the same ISBN is used to distinguish the book with the same name and publisher but a different Book_ID; 

- Each book has a Book_Status, including, Is_rent, Is_available, and Is_placed, with a value in either true or false. At a particular point in time, each book is allowed to have one and only one status in true, with the other two statuses in false. 

---

##### Relational Schema

<center><img src="tables.png" width="80%"></img></center>

---

##### Project Plan

***User Requirement Specification***

1. LMS Features Extraction (2022-09-25) 
   1. **Rent Function**: LMS will provide an independent Account_ID for each user to rent books from the library. Users could rent proper books with Name, Publisher, Book_ID, ISBN, and Category search through the system. The system with time-counting starts will record each book-rent history. 
   2. **Return Function**: Users could use Account_ID to return the book to the library, and the system with time-counting ends will record each book-return history. 
   3. **Account Deactivation Function**: System will detect according to each book’s status to judge whether the user has already reached the return deadline without a return. Furthermore, it will give the corresponding Account_ID a banned status that users cannot rent any books after returning this book for several days as punishment. 
   4. **Reservation Function**: The system provides a list to store the book that exists someone would prefer to reserve (Books still not returned will be put into the Want_Book list, books still in the library will be rented freely; if the book which already been reserved by other users is returned, it will be placed into Is_placed list for the corresponding user to rent). Users should confirm the Account_ID and Want_ISBN about the book to store the record in the system for the reservation part. 
   5. **Notification Function**: The system provide functions to remained users about the books reservation and book return alert by sending messages to users’ account. 

 

***System Modelling***
   1. ER Model Design (2022-10-04 ~ 2022-10-08) 
   2. ER Model Construction (2022-10-04 ~ 2022-10-08) 
   3. Relational Schema Building (2022-10-08) 

 
***Implementation***
   1. Detailed Implementation Time Slot

<center><img src="timeline.png" width="90%"></img></center>


   2. Task Distribution (2022-10-29 ~ 2022-11-05) 
      1. ZHANG and YE are responsible for constructing the books database for the library. 
      2. LIU and JIANG are responsible for constructing the user database for the rent-books function. 
      3. KWOK and CHEN are responsible for coding tests for the entire system operation. 
   3. Source Code Implementation (2022-10-29 ~ 2022-11-05) 

 
***Test Plan (Validation)***
   1. User Test (2022-11-05 ~ 2022-11-12) 
   2. System Test (2022-11-05 ~ 2022-11-12) 

 
***Project Report***
   1. Writing Project Report (2022-11-12 ~ 2022-11-19) 

***Presentation*** 
   1. Produce Demonstration Video (2022-11-12 ~ 2022-11-19) 

---


*End of the COMP2411 Group Project First Stage*
