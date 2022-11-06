package view;

import model.User;

import java.util.Scanner;

public class MainView {
    Scanner input = new Scanner(System.in);
    String managerAccountID;

    public void welcomePage() {
        System.out.println("Welcome to the Library Management System(LMS)!");
        System.out.println("Please select the following options in [x]:");
        System.out.println("- [L] Manager Login");
        System.out.println("- [E] Exit");
        System.out.print("Input: ");
    }
    public void mainPage() {
        System.out.println("Welcome Manager [" + managerAccountID + "]");
        System.out.println("Please select the following options in [x]:");
        System.out.println("- [A] Account Status");
        System.out.println("- [R] Return a Book");
        System.out.print("Input: ");

        // quit

    }

    public void processInputOptionPage(){
        System.out.println("Please select the following options in [x]:");
        System.out.println("- [S] Search a Book");
        System.out.println("- [R] Rent a Book");
        System.out.println("- [W] Want a Book");
        System.out.println("- [B] Back to previous page");
        System.out.print("Input: ");
        String option = input.nextLine();
        if (option.equals("S")) {
            processUserSearchPage();
        } else if (option.equals("R")) {
            processUserRentPage();
        } else if (option.equals("W")) {
            processUserWantPage();
        } else if (option.equals("B")) {
            mainPage();
        }
    }

    public void processUserSearchPage() {
        // ...
    }
    public void processUserRentPage() {
        // ...
    }
    public void processUserReturnPage() {
        // ...
    }
    public void processUserWantPage() {
        // ...
    }
    public void noticePage() {
        // ...
    }

    public void analysisReportPage(){
        // ...
    }

}
