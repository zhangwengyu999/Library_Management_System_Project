package controller;

import model.User;
import view.MainView;

import java.io.Console;

public class CentralController {
    private static final ModelController modelController = new ModelController();
    private static final MainView mainView = new MainView();

    public static String inputListener() {
        Console console = System.console();
        return console.readLine();
    }

    public static void main(String[] args) {
        mainView.welcomePage();
        String input = inputListener();
        if (input.equals("L")) {
            // modelController.login();
            System.out.println("Please input manager accountID: ");
            String managerAccountID = inputListener();
            mainView.mainPage(managerAccountID);
            String option = inputListener();
            if (option.equals("A")) {
                System.out.print("Please input User Account ID: ");
                String userAccountID = inputListener();
                User user = new User(userAccountID);
                if (user.getAccountStatus()) {
                    processInputOptionPage();
                } else {
                    mainPage(managerAccountID);
                }
            }
            else if (option.equals("R")) {
                System.out.print("Please input User Book ID: ");
                String bookID = inputListener();

            }
            else {}
        }
        else if (input.equals("E")) {
            System.exit(0);
        }
        else {
            System.out.println("Invalid input!");
        }

    }
}
