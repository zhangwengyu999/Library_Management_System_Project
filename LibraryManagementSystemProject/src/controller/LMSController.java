package controller;

import model.*;
import view.MainView;

import java.util.ArrayList;
import java.util.Scanner;

public class LMSController {
    private static final ModelController modelController = new ModelController();
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
                            mainView.addBookPage();
                            String inBookID = inputListener();
                            String inBookISBN = inputListener();
                            String inBookName = inputListener();
                            String inBookAuthor = inputListener();
                            String inBookCategory = inputListener();
                            if (inBookID.equals("Back")) break; // back to previous page (assumption: there is no input value named "Back")
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
                            User user = new User(accountID, true, "");
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
                        User user = new User(userAccountID);
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
                        User user = new User(userAccountID);
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
                    } else if (mainOption.equals("G")) {
                        mainView.inputUserPage();
                        String userAccountID = inputListener();
                        User user = new User(userAccountID);
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
                    } else if (mainOption.equals("H")) {
                        mainView.inputUserPage();
                        String userAccountID = inputListener();
                        User user = new User(userAccountID);
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
                    } else if (mainOption.equals("I")) {
                        while (true) {
                            mainView.processUserSearchPage();
                            mainView.linePage();
                            String searchOption = inputListener();
                            if (searchOption.equals("A")) { // ISBN
                                mainView.inputISBNPage();
                                String inISBN = inputListener();
                                try {
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
                    } else if (mainOption.equals("J")) {
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
                    } else if (mainOption.equals("K")) {
                        mainView.inputUserPage();
                        String inAccountID = inputListener();
                        
                        modelController.searchUserOnAccountID(inAccountID);
                    } else if (mainOption.equals("L")) {

                    } else if (mainOption.equals("M")) {

                    } else if (mainOption.equals("N")) {
                        try {
                            if (modelController.getAllBooks().size() == 0)
                                mainView.emptyPage();
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
                            for (PlacedBook placedBook : modelController.getAllPlacedBooks()) {
                                System.out.println(placedBook.showInfo());
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (mainOption.equals("-1")) { // back to previous
                        System.exit(0);
                    } else {
                        mainView.errorPage();
                    }
                }
            } else if (input.equals("E")) {
                System.exit(0);
            } else {
                mainView.errorPage();
            }
        }
    }
}
