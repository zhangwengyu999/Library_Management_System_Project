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
    void addRecord() {
        Book book = new Book("2","1010","MyBook","Publisher","Category",0,0);
//        User user = new User();
        modelController.addRecord(book);
    }

    @Test
    void testAddRecord() {

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

    @Test
    void deleteBookRecord() {
        assertTrue(modelController.deleteBookRecord("2"));
    }

    @Test
    void deleteUserRecord() {
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