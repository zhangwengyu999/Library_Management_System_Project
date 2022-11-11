package controller;

import model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Disabled
    void deleteWantBookRecord() {
        assertTrue(modelController.deleteWantBookRecord("0-05","1"));
        assertFalse(modelController.deleteWantBookRecord("0-05","1"));
    }

    @Disabled
    void deleteRentBookRecord() {
        assertTrue(modelController.deleteRentBookRecord("1"));
        assertFalse(modelController.deleteRentBookRecord("1"));
    }

    @Disabled
    void deletePlacedBookRecord() {
        assertTrue(modelController.deletePlacedBookRecord("2"));
        assertFalse(modelController.deletePlacedBookRecord("2"));
    }

    @Test
    void searchBookOnBookName() {
        try {
            List<Book> books = modelController.searchBookOnBookName("The Hobbit");
            assertEquals(2,books.size());
            assertEquals("The Hobbit", books.get(0).getBookName());
            assertEquals("The Hobbit", books.get(1).getBookName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchBookOnBookID() {
        try {
            List<Book> books = modelController.searchBookOnBookID("6");
            assertEquals("The Little Prince", books.get(0).getBookName());
            assertEquals("6", books.get(0).getBookID());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchBookOnBookAuthor() {
        try {
            List<Book> books = modelController.searchBookOnBookAuthor("J.R.R. Tolkien");
            assertEquals(3,books.size());
            for (Book book : books) {
                System.out.println(book.getAuthor());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchBookOnBookCategory() {
        try {
            List<Book> books = modelController.searchBookOnBookCategory("Fantasy");
            assertEquals(7,books.size());
            for (Book book : books) {
                System.out.println(book.getCategory());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchBookOnBookISBN() {
        try {
            List<Book> books = modelController.searchBookOnBookISBN("0-03");
            assertEquals(2,books.size());
            for (Book book : books) {
                assertEquals("The Hobbit", book.getBookName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchRentBookOnBookID() {
        try {
            List<RentBook> rentBooks = modelController.searchRentBookOnBookID("7");
            assertEquals(1,rentBooks.size());
            for (RentBook rentBook : rentBooks) {
                assertEquals("7", rentBook.getBookID());
                assertEquals("4", rentBook.getAccountID());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchRentBookOnAccountID() {
        try {
            List<RentBook> rentBooks = modelController.searchRentBookOnAccountID("2");
            assertEquals(1,rentBooks.size());
            for (RentBook rentBook : rentBooks) {
                assertEquals("2", rentBook.getAccountID());
                assertEquals("4", rentBook.getBookID());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchWantBookOnAccountID() {
        try {
            List<String> wantBooks = modelController.searchWantBookOnAccountID("1");
            assertEquals(2,wantBooks.size());
            for (String wantBook : wantBooks) {
                System.out.println(wantBook);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchUserFromWantBookOnISBN() {
        try {
            List<User> wantBooks = modelController.searchUserFromWantBookOnISBN("0-05");
            assertEquals(2,wantBooks.size());
            for (User wantBook : wantBooks) {
                System.out.println(wantBook.getAccountID());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchPlacedBookOnAccountID() {
        try {
            List<PlacedBook> placedBooks = modelController.searchPlacedBookOnAccountID("3");
            assertEquals(1,placedBooks.size());
            for (PlacedBook placedBook : placedBooks) {
                assertEquals("2", placedBook.getBookID());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchPlacedBookOnBookID() {
        try {
            List<PlacedBook> placedBooks = modelController.searchPlacedBookOnBookID("5");
            assertEquals(1,placedBooks.size());
            for (PlacedBook placedBook : placedBooks) {
                assertEquals("5", placedBook.getAccountID());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void searchUserOnAccountID() {
        try {
            List<User> users = modelController.searchUserOnAccountID("1");
            assertEquals(1,users.size());
            for (User user : users) {
                assertEquals("1", user.getAccountID());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void deactivateUser() {
        modelController.deactivateUser("1");
        try{
            assertFalse(modelController.searchUserOnAccountID("1").get(0).getAccountStatus());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void activateUser() {
        modelController.activateUser("1");
        try{
            assertTrue(modelController.searchUserOnAccountID("1").get(0).getAccountStatus());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
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