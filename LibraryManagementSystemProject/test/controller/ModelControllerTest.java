package controller;

import model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelControllerTest {
    ModelController modelController = new ModelController();

    /**
     * add book data to the DB
     * */
    @Test
    void addBookRecordTest() {
        Book book1 = new Book("1", "0-01", "The Lord of the Rings","J.R.R. Tolkien", "Fantasy",1,0);
        Book book2 = new Book("2","0-02","Harry Potter and the Philosopher Stone","J.K. Rowling","Fantasy",0,1);
        Book book3 = new Book("3","0-03", "The Hobbit", "J.R.R. Tolkien", "Fantasy",0,0);
        Book book4 = new Book("4", "0-04", "The Chronicles of Narnia", "C.S. Lewis", "Fantasy",1,0);
        Book book5 = new Book("5", "0-05", "The Lion, the Witch and the Wardrobe", "C.S. Lewis", "Fantasy",1,1);
        Book book6 = new Book("6", "0-06", "The Little Prince", "Antoine de Saint-Exupéry", "Fantasy",0,0);
        Book book7 = new Book("7","0-03", "The Hobbit", "J.R.R. Tolkien", "Fantasy",1,0);
        modelController.addRecord(book1);
        modelController.addRecord(book2);
        modelController.addRecord(book3);
        modelController.addRecord(book4);
        modelController.addRecord(book5);
        modelController.addRecord(book6);
        modelController.addRecord(book7);
    }

    /**
     * add user data to the DB
     * */
    @Test
    void addUserRecord() {
        User user1 = new User("1",true,"Notification \n");
        User user2 = new User("2",true,"Notification \n");
        User user3 = new User("3",true,"Notification \n");
        User user4 = new User("4",true,"Notification \n");
        User user5 = new User("5",true,"Notification \n");
        User user6 = new User("6",false,"Notification \n");
        User user7 = new User("7",false,"Notification \n");
        modelController.addRecord(user1);
        modelController.addRecord(user2);
        modelController.addRecord(user3);
        modelController.addRecord(user4);
        modelController.addRecord(user5);
        modelController.addRecord(user6);
        modelController.addRecord(user7);
    }

    /**
     * add rent book data to the DB
     * */
    @Test
    void addRentBookRecord() {
        RentBook rentBook1 = new RentBook("1","1",2022,10,30);
        RentBook rentBook2 = new RentBook("4","7",2022,11,2);
        RentBook rentBook3 = new RentBook("2","4",2022,11,5);
        modelController.addRecord(rentBook1);
        modelController.addRecord(rentBook2);
        modelController.addRecord(rentBook3);
    }

    /**
     * add want book data to the DB
     * */
    @Test
    void addWantBookRecord() {
        WantBook wantBook1 = new WantBook("1","0-05",2022, 11, 1);
        WantBook wantBook2 = new WantBook("3","0-05",2022, 10, 30);
        WantBook wantBook3 = new WantBook("1","0-04",2022, 11, 1);
        WantBook wantBook4 = new WantBook("2","0-04",2022, 10, 30);
        modelController.addRecord(wantBook1);
        modelController.addRecord(wantBook2);
        modelController.addRecord(wantBook3);
        modelController.addRecord(wantBook4);
    }

    /**
     * add placed book data to the DB
     * */
    @Test
    void addPlacedBookRecord() {
        PlacedBook placedBook1 = new PlacedBook("3","2",2022,11,1);
        PlacedBook placedBook2 = new PlacedBook("5","5",2022,11,3);
        modelController.addRecord(placedBook1);
        modelController.addRecord(placedBook2);
    }

    @Disabled
    void deleteBookRecord() {
        assertTrue(modelController.deleteBookRecord("2"));
    }

    @Disabled
    void deleteUserRecord() {
        assertTrue(modelController.deleteUserRecord("1"));
    }

//    @Test
//    void deleteWantBookRecord() {
//    }
//
//    @Test
//    void deleteRentBookRecord() {
//    }
//
//    @Test
//    void deletePlacedBookRecord() {
//    }
//
//    @Test
//    void searchBookOnBookName() {
//    }
//
//    @Test
//    void searchBookOnBookID() {
//    }
//
//    @Test
//    void searchBookOnBookAuthor() {
//    }
//
//    @Test
//    void searchBookOnBookCategory() {
//    }
//
//    @Test
//    void searchBookOnBookISBN() {
//    }
//
//    @Test
//    void searchRentBookOnBookID() {
//    }
//
//    @Test
//    void searchRentBookOnAccountID() {
//    }
//
//    @Test
//    void searchWantBookOnAccountID() {
//    }
//
//    @Test
//    void searchUserFromWantBookOnISBN() {
//    }
//
//    @Test
//    void searchPlacedBookOnAccountID() {
//    }
//
//    @Test
//    void searchPlacedBookOnBookID() {
//    }
//
//    @Test
//    void searchUserOnAccountID() {
//    }
//
//    @Test
//    void deactivateUser() {
//    }
//
//    @Test
//    void activateUser() {
//    }
//
//    @Test
//    void reserveBook() {
//    }
//
//    @Test
//    void cancelReservedBook() {
//    }
//
//    @Test
//    void cancelPlacedBook() {
//    }
//
//    @Test
//    void refreshExpiredPlacedBook() {
//    }
//
//    @Test
//    void getExpiredRentBook() {
//    }
//
//    @Test
//    void testGetExpiredRentBook() {
//    }
//
//    @Test
//    void rentBookFromUser() {
//    }
//
//    @Test
//    void returnBookFromUser() {
//    }
//
//    @Test
//    void notificationToUser() {
//    }
//
//    @Test
//    void generateAnalysisReport() {
//    }
}