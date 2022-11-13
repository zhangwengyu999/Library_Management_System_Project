package view;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;

public class MainView {
    int countBookNum;
    int countPeopleBorrowed;
    int countBorrowedBook;
    int countPeopleWanted;
    int countWantedBook;
    int countDeactivatedAccount;
    int countAccount;

    public void welcomePage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Welcome to the Library Management System(LMS)!");
        System.out.println();
        System.out.println("Please select the following options in [x]:");
        System.out.println("- [L] Manager Login");
        System.out.println("- [-1] Exit System");

    }
    public void mainPage() {
        System.out.println("Welcome Manager");
        System.out.println("Please select the following options in [x]:");
        System.out.println("- Operation on System");
        System.out.println("    - [A] Add Book");
        System.out.println("    - [B] Add User");
        System.out.println("    - [C] Delete Book");
        System.out.println("    - [D] Delete User");
        System.out.println("- Operation on User");
        System.out.println("    - [E] Rent a Book from Library");
        System.out.println("    - [F] Rent a Book from Placed");
        System.out.println("    - [G] Want a Book");
        System.out.println("    - [H] Cancel a Reserve Book");
        System.out.println("    - [I] Cancel a Placed book");
        System.out.println("    - [J] Search a Book");
        System.out.println("    - [K] Return a Book");
        System.out.println("    - [L] Search a User");
        System.out.println("- Data Refresh");
        System.out.println("    - [M] Refresh Expired Placed Book");
        System.out.println("    - [N] Refresh Deactivate User");
        System.out.println("- Data View");
        System.out.println("    - [O] View All Book");
        System.out.println("    - [P] View All Rent Book");
        System.out.println("    - [Q] View All Want Book");
        System.out.println("    - [R] View All Placed Book");
        System.out.println("- [X] Reset from Database");
        System.out.println("- [-1] Exit System");
    }

    public void processUserSearchPage() {
        System.out.println("Please select the following options in [x]:");
        System.out.println("- [A] Search a Book by ISBN");
        System.out.println("- [B] Search a Book by Name");
        System.out.println("- [C] Search a Book by Author");
        System.out.println("- [D] Search a Book by Category");
        System.out.println("- [Back] Back to previous page");
    }

    public void processUserRentPage() {
        System.out.println("- [Back] Back to previous page");
        System.out.println("Please input the book ID which want to rent:");
    }

    public void processUserReturnPage() {
        System.out.println("- [Back] Back to previous page");
        System.out.println("Please input the return Book ID:");
    }

    public void processUserWantPage() {
        System.out.println("- [Back] Back to previous page");
        System.out.println("Please input ISBN:");
    }

    public void processUserCancelReservePage() {
        System.out.println("Please input ISBN:");
    }

    public void processUserCancelPlacedPage() {
        System.out.println("Please input bookID:");
    }

    public void errorPage() {
        System.out.println("Wrong option! Please enter again!");
    }

    public void accountBannedPage() {
        System.out.println("Sorry, this account has been banned and cannot be operated");
    }
    
    public void linePage() {
        System.out.println("----------------------------------------------------------------\n" +
                "----------------------------------------------------------------");
    }

    public void successPage() {
        System.out.println("Execute successfully");
    }

    public void unSuccessPage() {
        System.out.println("Execute unsuccessfully");
    }

    public void addBookPage(){
        System.out.println("- [Back] Back to previous page");
        System.out.println("Please enter book ID:");
        System.out.println("Please enter book ISBN:");
        System.out.println("Please enter book name:");
        System.out.println("Please enter book publisher:");
        System.out.println("Please enter book category:");
    }

    public void inputUserPage() {
        System.out.println("- [Back] Back to previous page");
        System.out.println("Please enter accountID");
    }

    public void inputISBNPage() {
        System.out.println("- [Back] Back to previous page");
        System.out.println("Please enter the book ISBN");
    }

    public void inputNamePage() {
        System.out.println("- [Back] Back to previous page");
        System.out.println("Please enter the book name");
    }

    public void inputAuthorPage() {
        System.out.println("- [Back] Back to previous page");
        System.out.println("Please enter the author name");
    }

    public void inputCategoryPage() {
        System.out.println("- [Back] Back to previous page");
        System.out.println("Please enter the book category");
    }

    public void inputBookIDPage() {
        System.out.println("- [Back] Back to previous page");
        System.out.println("Please enter the bookID");
    }

    // when the book return by a user and the isbn is somebody want, notify the person who want the book
    public void canBeRentNotification(String ISBN, String accountID) {
        System.out.println("Dear" +accountID);
        System.out.println("The book (ISBN:"+ISBN+") you want now is available, you can get it in library by yourself!");
        System.out.println("Remember the MAX_PLACED_DAY is 7 day, you have better to get it in time");
        System.out.println("Or you are supposed to reserve this book by another application!");
    }

    // A book can be rent for up to 14 day
    public void outOfMaxRentDayNotification(String bookID, String accountID) {
        System.out.println("Dear" +accountID);
        System.out.println("The book (ID: "+bookID+") has been rent is out of the MAX_RENT_DAY!");
        System.out.println("Your account will be banned until you return the book!");
    }

    // A book can be reserved for up to 7 days
    public void outOfMaxPlacedDayNotification(String bookID, String accountID) {
        System.out.println("Dear" +accountID);
        System.out.println("The book (ID: "+bookID+") has been placed on library out of the MAX_PLACED_DAY!");
        System.out.println("You are supposed to reserve this book by another application!");
    }

    public void emptyPage() {
        System.out.println("The return list is empty");
    }

    public void showListPage() {
        System.out.println("The return list:");
    }

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
}
