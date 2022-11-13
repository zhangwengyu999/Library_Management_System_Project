package controller;

import controller.database.DataBase;
import model.*;
import view.MainView;

import java.util.*;

public class LMSController {
    private static ModelController modelController = new ModelController();
    private static final MainView mainView = new MainView();
    private static DataProcess dataProcess = new DataProcess();

    public static String inputListener() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        while(true) {
            mainView.welcomePage();
            String input = inputListener();
            mainView.mainPageWelcome();

            // compulsory set date
            System.out.println("Please set today's date first");
            System.out.print(">>> Please enter the Year: ");
            String y = inputListener();
            System.out.print(">>> Please enter the Month: ");
            String m = inputListener();
            System.out.print(">>> Please enter the Day: ");
            String d = inputListener();
            modelController.setDate(y, m, d);
            // Set date done
            if (input.equals("L")) {
                while (true) {
                    mainView.mainPage();
                    String mainOption = inputListener();
                    if (mainOption.equals("A")) {
                        while (true) {
                            System.out.println("Please enter book ID (- [Back] for Back):");
                            String inBookID = inputListener();
                            if (inBookID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                            System.out.println("Please enter book ISBN (- [Back] for Back):");
                            String inBookISBN = inputListener();
                            if (inBookISBN.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                            System.out.println("Please enter book name (- [Back] for Back):");
                            String inBookName = inputListener();
                            if (inBookName.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                            System.out.println("Please enter book author (- [Back] for Back):");
                            String inBookAuthor = inputListener();
                            if (inBookAuthor.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                            System.out.println("Please enter book category (- [Back] for Back):");
                            String inBookCategory = inputListener();
                            if (inBookCategory.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                            Book book = new Book(inBookID, inBookISBN, inBookName, inBookAuthor, inBookCategory,0,0);
                            if (modelController.addRecord(book)) {
                                mainView.successPage();
                                mainView.linePage();
                                mainView.breakPointPage();
                                String breakPoint = inputListener();
                                break;
                            }
                            else {
                                mainView.unSuccessPage();
                            }
                        }
                    } else if (mainOption.equals("B")) {
                        while (true) {
                            mainView.inputUserPage();
                            String accountID = inputListener();
                            if (accountID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                            User user = new User(accountID, true, "Notification \n");
                            if (modelController.addRecord(user)) {
                                mainView.successPage();
                                mainView.linePage();
                                mainView.breakPointPage();
                                String breakPoint = inputListener();
                                break;
                            } else {
                                mainView.unSuccessPage();
                            }
                        }
                    } else if (mainOption.equals("C")) {
                        while (true) {
                            mainView.inputBookIDPage();
                            String bookID = inputListener();
                            if (bookID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                            if (modelController.deleteBookRecord(bookID)) {
                                mainView.successPage();
                                mainView.linePage();
                                mainView.breakPointPage();
                                String breakPoint = inputListener();
                                break;
                            } else {
                                mainView.unSuccessPage();
                            }
                        }
                    } else if (mainOption.equals("D")) {
                        while (true) {
                            mainView.inputUserPage();
                            String accountID = inputListener();
                            if (accountID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                            if (modelController.deleteUserRecord(accountID)) {
                                mainView.successPage();
                                mainView.linePage();
                                mainView.breakPointPage();
                                String breakPoint = inputListener();
                                break;
                            } else {
                                mainView.unSuccessPage();
                            }
                        }
                    } else if (mainOption.equals("E")) {
                        mainView.inputUserPage();
                        String userAccountID = inputListener();
                        User user = modelController.searchUserOnAccountID(userAccountID).get(0);
                        if (user.getAccountStatus()) {
                            while (true) {
                                mainView.processUserRentPage();
                                String inBookID = inputListener();
                                if (inBookID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                                if (modelController.rentBookFromUser(userAccountID, inBookID)) { //used rentBookFromUser return boolean
                                    mainView.successPage();
                                    mainView.linePage();
                                    mainView.breakPointPage();
                                    String breakPoint = inputListener();
                                    break;
                                } else {
                                    mainView.unSuccessPage();
                                }
                            }
                        } else {
                            mainView.accountBannedPage();
                        }
                    } else if (mainOption.equals("F")) {
                        mainView.inputUserPage();
                        String userAccountID = inputListener();
                        User user = modelController.searchUserOnAccountID(userAccountID).get(0);
                        if (user.getAccountStatus()) {
                            while (true) {
                                mainView.processUserRentPage();
                                String inBookID = inputListener();
                                if (inBookID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                                if (modelController.rentBookFromPlacedBook(userAccountID, inBookID)) { //used rentBookFromUser return boolean
                                    mainView.successPage();
                                    mainView.linePage();
                                    mainView.breakPointPage();
                                    String breakPoint = inputListener();
                                    break;
                                } else {
                                    mainView.unSuccessPage();
                                }
                            }
                        } else {
                            mainView.accountBannedPage();
                        }
                    } else if (mainOption.equals("G")) {
                        mainView.inputUserPage();
                        String userAccountID = inputListener();
                        User user = modelController.searchUserOnAccountID(userAccountID).get(0);
                        if (user.getAccountStatus()) {
                            while (true) {
                                mainView.processUserWantPage();
                                String inISBN = inputListener();
                                if (inISBN.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                                if (modelController.reserveBook(userAccountID, inISBN)) {
                                    mainView.successPage();
                                    mainView.linePage();
                                    mainView.breakPointPage();
                                    String breakPoint = inputListener();
                                    break;
                                } else {
                                    mainView.unSuccessPage();
                                }
                            }
                        } else {
                            mainView.accountBannedPage();
                        }
                    } else if (mainOption.equals("H")) {
                        mainView.inputUserPage();
                        String userAccountID = inputListener();
                        User user = modelController.searchUserOnAccountID(userAccountID).get(0);
                        if (user.getAccountStatus()) {
                            while (true) {
                                mainView.processUserCancelReservePage();
                                String inISBN = inputListener();
                                if (inISBN.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                                if (modelController.cancelReservedBook(inISBN,userAccountID)) {
                                    mainView.successPage();
                                    mainView.linePage();
                                    mainView.breakPointPage();
                                    String breakPoint = inputListener();
                                    break;
                                } else {
                                    mainView.unSuccessPage();
                                }
                            }
                        } else {
                            mainView.unSuccessPage();
                        }
                    } else if (mainOption.equals("I")) {
                        mainView.inputUserPage();
                        String userAccountID = inputListener();
                        User user = modelController.searchUserOnAccountID(userAccountID).get(0);
                        if (user.getAccountStatus()) {
                            while (true) {
                                mainView.processUserCancelPlacedPage();
                                String inBookID = inputListener();
                                if (inBookID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                                if (modelController.cancelPlacedBook(inBookID,userAccountID)) {
                                    mainView.successPage();
                                    mainView.linePage();
                                    mainView.breakPointPage();
                                    String breakPoint = inputListener();
                                    break;
                                } else {
                                    mainView.unSuccessPage();
                                }
                            }
                        } else {
                            mainView.unSuccessPage();
                        }
                    } else if (mainOption.equals("J")) {
                        while (true) {
                            mainView.processUserSearchPage();
                            String searchOption = inputListener();
                            if (searchOption.equals("A")) { // ISBN
                                mainView.inputISBNPage();
                                String inISBN = inputListener();
                                try {
                                    if (modelController.searchBookOnBookISBN(inISBN).size() == 0)
                                        mainView.emptyPage();
                                    mainView.showListPage();
                                    mainView.showTitle();
                                    System.out.println("book(s) search on ISBN:");
                                    for (Book book : modelController.searchBookOnBookISBN(inISBN)) {
                                        System.out.println(book.showInfo());
                                    }
                                    mainView.linePage();
                                    mainView.breakPointPage();
                                    String breakPoint = inputListener();
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("B")) { // name
                                mainView.inputNamePage();
                                String inName = inputListener();
                                try {
                                    if (modelController.searchBookOnBookName(inName).size() == 0)
                                        mainView.emptyPage();
                                    mainView.showListPage();
                                    mainView.showTitle();
                                    System.out.println("book(s) search on book name:");
                                    for (Book book : modelController.searchBookOnBookName(inName)) {
                                        System.out.println(book.showInfo());
                                    }
                                    mainView.linePage();
                                    mainView.breakPointPage();
                                    String breakPoint = inputListener();
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("C")) { // author
                                mainView.inputAuthorPage();
                                String inAuthor = inputListener();
                                try {
                                    if (modelController.searchBookOnBookAuthor(inAuthor).size() == 0)
                                        mainView.emptyPage();
                                    mainView.showListPage();
                                    mainView.showTitle();
                                    System.out.println("book(s) search on author name:");
                                    for (Book book : modelController.searchBookOnBookAuthor(inAuthor)) {
                                        System.out.println(book.showInfo());
                                    }
                                    mainView.linePage();
                                    mainView.breakPointPage();
                                    String breakPoint = inputListener();
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("D")) { // category
                                mainView.inputCategoryPage();
                                String inCategory = inputListener();
                                try {
                                    if (modelController.searchBookOnBookCategory(inCategory).size() == 0)
                                        mainView.emptyPage();
                                    mainView.showListPage();
                                    mainView.showTitle();
                                    System.out.println("book(s) search on category:");
                                    for (Book book : modelController.searchBookOnBookCategory(inCategory)) {
                                        System.out.println(book.showInfo());
                                    }
                                    mainView.linePage();
                                    mainView.breakPointPage();
                                    String breakPoint = inputListener();
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("Back")) { // back to previous page (assumption: there is no input value named "Back")
                                break;
                            } else {
                                mainView.errorPage();
                            }
                        }
                    } else if (mainOption.equals("K")) {
                        while (true) {
                            mainView.processUserReturnPage();
                            String inBookID = inputListener();
                            if (inBookID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                            if (modelController.returnBookFromUser(inBookID)) {
                                mainView.successPage();
                                mainView.linePage();
                                mainView.breakPointPage();
                                String breakPoint = inputListener();
                                break;
                            } else {
                                mainView.unSuccessPage();
                            }
                        }
                    } else if (mainOption.equals("L")) {
                        mainView.inputUserPage();
                        String inAccountID = inputListener();
                        try {
                            if (modelController.searchUserOnAccountID(inAccountID).size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            mainView.showTitle();
                            System.out.println("book(s) search on account ID:");
                            for (User user : modelController.searchUserOnAccountID(inAccountID)) {
                                System.out.println(user.showInfo());
                            }
                            mainView.linePage();
                            mainView.breakPointPage();
                            String breakPoint = inputListener();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("M")) {
                        modelController.refreshExpiredPlacedBook();
                        mainView.successPage();
                        mainView.linePage();
                        mainView.breakPointPage();
                        String breakPoint = inputListener();
                    } else if (mainOption.equals("N")) {
                        try {
                            if (modelController.deactivateUserForExpiredRentBook().size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            mainView.showTitle();
                            System.out.println("expired rent book(s):");
                            for (User user : modelController.deactivateUserForExpiredRentBook()) {
                                System.out.println(user.showInfo());
                            }
                            mainView.linePage();
                            mainView.breakPointPage();
                            String breakPoint = inputListener();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("O")) {
                        try {
                            if (modelController.getAllBooks().size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            mainView.showTitle();
                            System.out.println("all book(s):");
                            for (Book book : modelController.getAllBooks()) {
                                System.out.println(book.showInfo());
                            }
                            mainView.linePage();
                            mainView.breakPointPage();
                            String breakPoint = inputListener();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("P")) {
                        try {
                            if (modelController.getAllRentBooks().size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            mainView.showTitle();
                            System.out.println("all rent book(s):");
                            for (RentBook rentBook : modelController.getAllRentBooks()) {
                                System.out.println(rentBook.showInfo());
                            }
                            mainView.linePage();
                            mainView.breakPointPage();
                            String breakPoint = inputListener();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("Q")) {
                        try {
                            if (modelController.getAllWantBooks().size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            mainView.showTitle();
                            System.out.println("all want book(s):");
                            for (WantBook wantBook : modelController.getAllWantBooks()) {
                                System.out.println(wantBook.showInfo());
                            }
                            mainView.linePage();
                            mainView.breakPointPage();
                            String breakPoint = inputListener();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("R")) {
                        try {
                            if (modelController.getAllPlacedBooks().size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            mainView.showTitle();
                            System.out.println("all placed book(s):");
                            for (PlacedBook placedBook : modelController.getAllPlacedBooks()) {
                                System.out.println(placedBook.showInfo());
                            }
                            mainView.linePage();
                            mainView.breakPointPage();
                            String breakPoint = inputListener();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("S")) {
                        try {
                            int totalBookNumber = dataProcess.getTotalBookNumber();
                            int totalRentBookNumber = dataProcess.getTotalRentBook();
                            List<Book> mostRentBook = dataProcess.getMostRentBookID();
                            List<Book> leastRentBook = dataProcess.getLeastRentBookID();
                            List<Book> mostWantBook = dataProcess.getMostWantBookID();
                            List<Book> leastWantBook = dataProcess.getLeastWantBookID();
                            List<Book> mostRentBookByCategory = dataProcess.getMostRentBookByCategory();
                            List<Book> mostRentBookByAuthor = dataProcess.getMostRentBookByAuthor();
                            mainView.analysisReportPage(totalBookNumber, totalRentBookNumber, mostRentBook, leastRentBook,
                                    mostWantBook, leastWantBook, mostRentBookByCategory, mostRentBookByAuthor);
                            mainView.linePage();
                            mainView.breakPointPage();
                            String breakPoint = inputListener();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("T")) {
                        System.out.print(">>> Please enter the Year: ");
                        String yy = inputListener();
                        System.out.print(">>> Please enter the Month: ");
                        String mm = inputListener();
                        System.out.print(">>> Please enter the Day: ");
                        String dd = inputListener();
                        modelController.setDate(yy, mm, dd);
                        mainView.successPage();
                        mainView.linePage();
                        mainView.breakPointPage();
                        String breakPoint = inputListener();
                    } else if (mainOption.equals("X")) {
                        modelController = new ModelController();
                        mainView.linePage();
                        mainView.breakPointPage();
                        String breakPoint = inputListener();
                    } else if (mainOption.equals("-1")) { // exit
                        modelController.closeDB();
                        System.exit(0);
                    } else {
                        mainView.errorPage();
                    }
                }
            } else if (input.equals("-1")) {
                modelController.closeDB();
                System.exit(0);
            } else {
                mainView.errorPage();
            }
        }
    }
}
