package controller;

import model.Book;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelControllerTest {
    ModelController modelController = new ModelController();


    @BeforeEach
    void refreshBuffers() {
        modelController.refreshBuffers();
    }

    @Disabled
    void addBookRecordTest() {
        Book book = new Book("2","1010","MyBook","Publisher","Category",0,0);
        modelController.addRecord(book);
    }

    @Disabled
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

    @Test
    void testAddRecord1() {
    }

    @Test
    void testAddRecord2() {
    }

    @Test
    void testAddRecord3() {
    }

    @Disabled
    void deleteBookRecord() {
        assertTrue(modelController.deleteBookRecord("2"));
    }

    @Disabled
    void deleteUserRecord() {
        assertTrue(modelController.deleteUserRecord("1"));
    }

    @Test
    void deleteWantBookRecord() {
    }

    @Test
    void deleteRentBookRecord() {
    }

    @Test
    void deletePlacedBookRecord() {
    }

    @Test
    void searchBookOnBookName() {
    }

    @Test
    void searchBookOnBookID() {
    }

    @Test
    void searchBookOnBookAuthor() {
    }

    @Test
    void searchBookOnBookCategory() {
    }

    @Test
    void searchBookOnBookISBN() {
    }

    @Test
    void searchRentBookOnBookID() {
    }

    @Test
    void searchRentBookOnAccountID() {
    }

    @Test
    void searchWantBookOnAccountID() {
    }

    @Test
    void searchUserFromWantBookOnISBN() {
    }

    @Test
    void searchPlacedBookOnAccountID() {
    }

    @Test
    void searchPlacedBookOnBookID() {
    }

    @Test
    void searchUserOnAccountID() {
    }

    @Test
    void deactivateUser() {
    }

    @Test
    void activateUser() {
    }

    @Test
    void reserveBook() {
    }

    @Test
    void cancelReservedBook() {
    }

    @Test
    void cancelPlacedBook() {
    }

    @Test
    void refreshExpiredPlacedBook() {
    }

    @Test
    void getExpiredRentBook() {
    }

    @Test
    void testGetExpiredRentBook() {
    }

    @Test
    void rentBookFromUser() {
    }

    @Test
    void returnBookFromUser() {
    }

    @Test
    void notificationToUser() {
    }

    @Test
    void generateAnalysisReport() {
    }
}