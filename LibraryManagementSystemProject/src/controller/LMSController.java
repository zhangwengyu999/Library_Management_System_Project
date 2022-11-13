package controller;

import model.*;
import view.MainView;

import java.util.ArrayList;
import java.util.Scanner;

public class LMSController {
    private static ModelController modelController = new ModelController();
    private static final MainView mainView = new MainView();

    public static String inputListener() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        while(true) {
            mainView.welcomePage();
            mainView.linePage();
            String input = inputListener();
            if (input.equals("L")) {
                while (true) {
                    mainView.mainPage();
                    mainView.linePage();
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
                                mainView.linePage();
                                String inBookID = inputListener();
                                if (inBookID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                                if (modelController.rentBookFromUser(userAccountID, inBookID)) { //used rentBookFromUser return boolean
                                    mainView.successPage();
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
                                mainView.linePage();
                                String inBookID = inputListener();
                                if (inBookID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                                if (modelController.rentBookFromPlacedBook(userAccountID, inBookID)) { //used rentBookFromUser return boolean
                                    mainView.successPage();
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
                            mainView.linePage();
                            String searchOption = inputListener();
                            if (searchOption.equals("A")) { // ISBN
                                mainView.inputISBNPage();
                                String inISBN = inputListener();
                                try {
                                    if (modelController.searchBookOnBookISBN(inISBN).size() == 0)
                                        mainView.emptyPage();
                                    mainView.showListPage();
                                    for (Book book : modelController.searchBookOnBookISBN(inISBN)) {
                                        System.out.println(book.showInfo());
                                    }
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
                                    for (Book book : modelController.searchBookOnBookName(inName)) {
                                        System.out.println(book.showInfo());
                                    }
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
                                    for (Book book : modelController.searchBookOnBookAuthor(inAuthor)) {
                                        System.out.println(book.showInfo());
                                    }
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
                                    for (Book book : modelController.searchBookOnBookCategory(inCategory)) {
                                        System.out.println(book.showInfo());
                                    }
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
                            mainView.linePage();
                            String inBookID = inputListener();
                            if (inBookID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
                            if (modelController.returnBookFromUser(inBookID)) {
                                mainView.successPage();
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
                            for (User user : modelController.searchUserOnAccountID(inAccountID)) {
                                System.out.println(user.showInfo());
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("M")) {
                        modelController.refreshExpiredPlacedBook();
                        mainView.successPage();
                    } else if (mainOption.equals("N")) {
                        try {
                            if (modelController.deactivateUserForExpiredRentBook().size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            for (User user : modelController.deactivateUserForExpiredRentBook()) {
                                System.out.println(user.showInfo());
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("O")) {
                        try {
                            if (modelController.getAllBooks().size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            for (Book book : modelController.getAllBooks()) {
                                System.out.println(book.showInfo());
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("P")) {
                        try {
                            if (modelController.getAllRentBooks().size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            for (RentBook rentBook : modelController.getAllRentBooks()) {
                                System.out.println(rentBook.showInfo());
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("Q")) {
                        try {
                            if (modelController.getAllWantBooks().size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            for (WantBook wantBook : modelController.getAllWantBooks()) {
                                System.out.println(wantBook.showInfo());
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("R")) {
                        try {
                            if (modelController.getAllPlacedBooks().size() == 0)
                                mainView.emptyPage();
                            mainView.showListPage();
                            for (PlacedBook placedBook : modelController.getAllPlacedBooks()) {
                                System.out.println(placedBook.showInfo());
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("X")) { // back to previous
                        modelController = new ModelController();
                    }
                    else if (mainOption.equals("-1")) { // back to previous
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
