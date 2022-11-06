package view;

import java.text.SimpleDateFormat;

public class MainView {
    String managerAccountID;
    int countBookNum;
    int countPeopleBorrowed;
    int countBorrowedBook;
    int countPeopleWanted;
    int countWantedBook;
    int countDeactivatedAccount;
    int countAccount;

    public void welcomePage() {
        System.out.println("Welcome to the Library Management System(LMS)!");
        System.out.println("Please select the following options in [x]:");
        System.out.println("- [L] Manager Login");
        System.out.println("- [E] Exit");

    }
    public void mainPage() {
        System.out.println("Welcome Manager [" + managerAccountID + "]");
        System.out.println("Please select the following options in [x]:");
        System.out.println("- [A] Account Status");
        System.out.println("- [S] Search a Book");
        System.out.println("- [R] Return a Book");
        System.out.println("- [E] Exit");
    }

    public void processInputOptionPage() {
        System.out.println("Please select the following options in [x]:");
        System.out.println("- [R] Rent a Book");
        System.out.println("- [W] Want a Book");
        System.out.println("- [B] Back to previous page");

    }

    public void processUserSearchPage() {
        System.out.println("Please select the following options in [x]:");
        System.out.println("- [I] Search a Book by ISBN");
        System.out.println("- [N] Search a Book by Name");
        System.out.println("- [A] Search a Book by Author");
        System.out.println("- [C] Search a Book by Category");
        System.out.println("- [B] Back to previous page");
    }

    public void processUserRentPage() {
        System.out.println("\"B\" Back to previous page");
        System.out.println("Please input the book ID which want to rent:");
    }

    public void processUserReturnPage() {
        System.out.println("\"B\" Back to previous page");
        System.out.println("Please input the return Book ID:");
    }

    public void processUserWantPage() {
        System.out.println("\"B\" Back to previous page");
        System.out.println("Please input ISBN:");
    }

//    public void noticePage() {
//        System.out.println("Successful!");
//    }

    public void analysisReportPage() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

        System.out.println("-------------------------------------");
        System.out.println("           " + "ANALYSIS REPORT");
        System.out.println("  " + "Create Time: " + timeStamp);
        System.out.println("-------------------------------------");
        System.out.println("      " + "Books Status Information");
        System.out.println("-------------------------------------");
        System.out.println("Number of Books: " +  countBookNum);
        System.out.println("Number of People Borrowed Books: " + countPeopleBorrowed);
        System.out.println("Number of Borrowed Books: " + countBorrowedBook);
        System.out.println("Number of User who has Wanted Books: " + countPeopleWanted);
        System.out.println("Number of Wanted Books: " + countWantedBook);
        System.out.println("-------------------------------------");
        System.out.println("      " + "Accounts Status Analysis");
        System.out.println("-------------------------------------");
        System.out.println("Number of Accounts: " + countAccount);
        System.out.println("Number of Deactivated Accounts: " + countDeactivatedAccount);
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
    }

    public void errorPage() {
        System.out.println("Wrong option! Please enter again!");
    }

    public void accountBannedPage() {
        System.out.println("Sorry, this account has been banned and cannot be operated");
    }


}
