package controller;

import model.User;
import model.Book;
import view.MainView;
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
                    if (mainOption.equals("O")) {
                        mainView.DBOperationSelectPage();
                        mainView.linePage();
                        while (true) {
                            if (inputListener().equals("A")) {
                                mainView.DBAddOperationPage();
                                mainView.linePage();
                                while (true) {
                                    if (inputListener().equals("B")) {
                                        mainView.addBookPage();
                                        mainView.enterPage();
                                        String bookID = inputListener();
                                        String bookISBN = inputListener();
                                        String bookName = inputListener();
                                        String bookPublisher = inputListener();
                                        String bookCategory = inputListener();
//                                        int year = modelController.getYear();
//                                        int month = modelController.getMonth();
//                                        int day = modelController.getDay();
                                        Book book = new Book(bookID, bookISBN, bookName, bookPublisher, bookCategory,0,0);

                                        if (modelController.addRecord(book)) {
                                            mainView.successPage();
                                            break;
                                        }
                                        else {
                                            mainView.unSuccessPage();
                                        }
                                    } else if (inputListener().equals("U")) {
                                        mainView.inputUserPage();
                                        String accountID = inputListener();
                                        User user = new User(accountID,true,"");
                                        if (modelController.addRecord(user)) {
                                            mainView.successPage();
                                            break;
                                        } else {
                                            mainView.unSuccessPage();
                                        }
                                    } else if (inputListener().equals("Back")) {
                                        break;
                                    } else {
                                        mainView.errorPage();
                                    }
                                }
                            } else if (inputListener().equals("D")) {
                                mainView.DBDeleteOperationPage();
                                mainView.linePage();
                                while (true) {
                                    if (inputListener().equals("B")){
                                        mainView.inputBookIDPage();
                                        mainView.enterPage();
                                        String bookID = inputListener();
                                        if (modelController.deleteBookRecord(bookID)) {
                                            mainView.successPage();
                                            break;
                                        }
                                        else {
                                            mainView.unSuccessPage();
                                        }
                                    } else if (inputListener().equals("U")) {
                                        mainView.inputUserPage();
                                        mainView.enterPage();
                                        String accountID = inputListener();
                                        if (modelController.deleteUserRecord(accountID)) {
                                            mainView.successPage();
                                            break;
                                        }
                                        else {
                                            mainView.unSuccessPage();
                                        }
                                    } else if (inputListener().equals("Back")) {
                                        break;
                                    } else {
                                        mainView.errorPage();
                                    }
                                }
                            } else if (inputListener().equals("Back")) {
                                break;
                            } else {
                                mainView.errorPage();
                            }
                        }
                    }
                    else if (mainOption.equals("U")) { // User operation: Want, Rent, Cancel
                        mainView.inputUserPage();
                        String userAccountID = inputListener();
                        User user = new User(userAccountID);
                        if (user.getAccountStatus()) {
                            while (true) {
                                mainView.processUserOptionPage();
                                mainView.linePage();
                                String inputOption = inputListener();
                                if (inputOption.equals("R")) {
                                    while (true) {
                                        mainView.processUserRentPage();
                                        mainView.linePage();
                                        String inBookID = inputListener();
                                        if (inBookID.equals("Back")) break; // back to previous
                                        if (modelController.rentBookFromUser(userAccountID, inBookID)) { //used rentBookFromUser return boolean
                                            mainView.successPage();
                                            break;
                                        } else {
                                            mainView.unSuccessPage();
                                        }
                                    }
                                } else if (inputOption.equals("W")) {
                                    while (true) {
                                        mainView.processUserWantPage();
                                        String inISBN = inputListener();
                                        if (inISBN.equals("Back")) break; // back to previous
                                        if (modelController.reserveBook(userAccountID, inISBN)) {
                                            mainView.successPage();
                                            break;
                                        }
                                        else{
                                            mainView.unSuccessPage();
                                        }
                                    }
                                } else if (inputListener().equals("C")) {
                                    while (true) {
                                        mainView.processUserCancelBook();
                                        if (inputListener().equals("R")) {
                                            mainView.processUserCancelReservePage();
                                            String inISBN = inputListener();
                                            if (modelController.cancelReservedBook(inISBN,userAccountID)) {
                                                mainView.successPage();
                                                break;
                                            } else {
                                                mainView.unSuccessPage();
                                            }
                                        } else if (inputListener().equals("P")) {
                                            mainView.processUserCancelPlacedPage();
                                            String inBookID = inputListener();
                                            if (modelController.cancelPlacedBook(inBookID,userAccountID)) {
                                                mainView.successPage();
                                                break;
                                            } else {
                                                mainView.unSuccessPage();
                                            }
                                        } else if (inputOption.equals("Back")) { // back to previous
                                            break;
                                        } else {
                                            mainView.errorPage();
                                        }
                                    }
                                } else if (inputOption.equals("Back")) { // back to previous
                                    break;
                                } else {
                                    mainView.errorPage();
                                }
                            }
                        } else {
                            mainView.accountBannedPage();
                        }
                    } else if (mainOption.equals("S")) { // search
                        while (true) {
                            mainView.processUserSearchPage();
                            mainView.linePage();
                            String searchOption = inputListener();
                            if (searchOption.equals("I")) { // ISBN
                                mainView.inputISBNPage();
                                String inISBN = inputListener();
                                try {
                                    for (Book book : modelController.searchBookOnBookISBN(inISBN)) {
                                        System.out.println(book.showInfo(book));
                                    }
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("N")) { // name
                                mainView.inputNamePage();
                                String inName = inputListener();
                                try {
                                    for (Book book : modelController.searchBookOnBookName(inName)) {
                                        System.out.println(book.showInfo(book));
                                    }
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("A")) { // author
                                mainView.inputAuthorPage();
                                String inAuthor = inputListener();
                                try {
                                    for (Book book : modelController.searchBookOnBookAuthor(inAuthor)) {
                                        System.out.println(book.showInfo(book));
                                    }
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("C")) { // category
                                mainView.inputCategoryPage();
                                String inCategory = inputListener();
                                try {
                                    for (Book book : modelController.searchBookOnBookCategory(inCategory)) {
                                        System.out.println(book.showInfo(book));
                                    }
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("Back")) { // back to previous
                                break;
                            } else {
                                mainView.errorPage();
                            }
                        }
                    } else if (mainOption.equals("R")) { // return book
                        while (true) {
                            mainView.processUserReturnPage();
                            mainView.linePage();
                            String inBookID = inputListener();
                            if (inBookID.equals("Back")) break; // back to previous
                            if (modelController.returnBookFromUser(inBookID)) {
                                mainView.successPage();
                            } else {
                                mainView.unSuccessPage();
                            }
                        }
                    } else if (mainOption.equals("B")) { // back to previous
                        break;
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
