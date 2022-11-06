package controller;

import model.User;
import model.Book;
import view.MainView;
import java.util.Scanner;

public class CentralController {
    private static final ModelController modelController = new ModelController();
    private static final MainView mainView = new MainView();
//    private static final System.console() System.console() = System.System.console()();

    public static String inputListener() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        while(true) {
            mainView.welcomePage();
            String input =inputListener();
            if (input.equals("L")) {
                while (true) {
                    mainView.mainPage();
                    String mainOption = inputListener();
                    if (mainOption.equals("A")) { // account status for search
                        System.out.println("Please input User Account ID:");
                        String userAccountID = inputListener();
                        User user = new User(userAccountID);
                        if (user.getAccountStatus()) {
                            while (true) {
                                mainView.processInputOptionPage();
                                String inputOption = inputListener();
                                if (inputOption.equals("R")) {
                                    while (true) {
                                        mainView.processUserRentPage();
                                        String inBookID = inputListener();
                                        if (inBookID.equals("B")) break; // back to previous
                                        modelController.rentBookFromUser(userAccountID, inBookID);
                                    }
                                } else if (inputOption.equals("W")) {
                                    while (true) {
                                        mainView.processUserWantPage();
                                        String inISBN = inputListener();
                                        if (inISBN.equals("B")) break; // back to previous
                                        modelController.reserveBook(userAccountID, inISBN);
                                    }
                                } else if (inputOption.equals("B")) { // back to previous
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
                            String searchOption = inputListener();
                            if (searchOption.equals("I")) { // ISBN
                                System.out.println("Please enter the book ISBN:");
                                String inISBN = inputListener();
                                try {
                                    for (Book book : modelController.searchBookOnBookISBN(inISBN)) {
                                        System.out.println(book.showInfo(book));
                                    }
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("N")) { // name
                                System.out.println("Please enter the book Name:");
                                String inName = inputListener();
                                try {
                                    for (Book book : modelController.searchBookOnBookName(inName)) {
                                        System.out.println(book.showInfo(book));
                                    }
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("A")) { // author
                                System.out.println("Please enter the book Author Name:");
                                String inAuthor = inputListener();
                                try {
                                    for (Book book : modelController.searchBookOnBookAuthor(inAuthor)) {
                                        System.out.println(book.showInfo(book));
                                    }
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("C")) { // category
                                System.out.println("Please enter the book Category:");
                                String inCategory = inputListener();
                                try {
                                    for (Book book : modelController.searchBookOnBookCategory(inCategory)) {
                                        System.out.println(book.showInfo(book));
                                    }
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            } else if (searchOption.equals("B")) { // back to previous
                                break;
                            } else {
                                mainView.errorPage();
                            }
                        }
                    } else if (mainOption.equals("R")) { // return book
                        while (true) {
                            mainView.processUserReturnPage();
                            String inBookID = inputListener();
                            if (inBookID.equals("B")) break; // back to previous
                            modelController.returnBookFromUser(inBookID);
                        }
                    } else if (mainOption.equals("E")) { // exit
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
