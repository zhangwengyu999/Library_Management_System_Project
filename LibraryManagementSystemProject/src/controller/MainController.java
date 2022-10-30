package controller;

import model.Book;
import model.RentBook;
import model.User;
import model.WantBook;
import view.MainView;
import java.util.*;

public class MainController {
    private Book[] books;
    private User[] users;
    private RentBook[] rentBooks;
    private WantBook[] wantBooks;
    private MainView mainView;
    private final int MAX_RENT_DAY = 14;
    private String today = "2022-10-30";


    // ------------------ Search ------------------


    public List<Book> searchBookByName(String inName) {
        List<Book> outBooks = new ArrayList<>();
        for (Book book: books) {
            if (book.getBookName().equals(inName)) {
                outBooks.add(book);
            }
        }
        if (!outBooks.isEmpty()) {
            return outBooks;
        }
        else {
            return null;
        }
    }

    public List<Book> searchBookByISBN(String inISBN) {
        List<Book> outBooks = new ArrayList<>();
        for (Book book: books) {
            if (book.getBookName().equals(inISBN)) {
                outBooks.add(book);
            }
        }
        if (!outBooks.isEmpty()) {
            return outBooks;
        }
        else {return null;}
    }

    public List<Book> searchBookByAuthor(String inAuthor) {
        List<Book> outBooks = new ArrayList<>();
        for (Book book: books) {
            if (book.getBookName().equals(inAuthor)) {
                outBooks.add(book);
            }
        }
        if (!outBooks.isEmpty()) {
            return outBooks;
        }
        else {return null;}
    }

    public List<Book> searchBookByCategory(String inCategory) {
        List<Book> outBooks = new ArrayList<>();
        for (Book book: books) {
            if (book.getBookName().equals(inCategory)) {
                outBooks.add(book);
            }
        }
        if (!outBooks.isEmpty()) {
            return outBooks;
        }
        else {return null;}
    }
    // -----------------------------------------------

    // ----------------- de/activate User -----------------

    public boolean deactivateUser(User inUser){
        if(inUser.getAccountStatus()) {
            inUser.setAccountStatus(false);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean activateUser(User inUser){
        if(!inUser.getAccountStatus()) {
            inUser.setAccountStatus(true);
            return true;
        }
        else {
            return false;
        }
    }
    // -----------------------------------------------


    // Reserve a book(ISBN) that is NOT available in the Library by a user,
    // put the pair of <User, ISBN> into wantBook
    public void reserveBook(String ISBN, Book book, WantBook wantbook, boolean[] status) {
        if(searchBookByISBN(ISBN) != null){
            if (!searchBookByISBN(ISBN).getStatus()[1]) {

            }
            ISBN = book.getISBN();
            status =book.getStatus();
            if (status[1]){
                wantbook.setWantDate(today);
            }
            else if (status[3]){

            }
        }
    }

    public void notificationToUser(String ISBN, Book book, String UserID, boolean[] status){
        ISBN = book.getISBN();
        status = book.getStatus();
        if(searchBookByISBN(ISBN) != null) {
            if () {

            }
        }
    }

    public void generateAnalysisReport(){
        
    }

    public Book[] getExpiredBooks() {
        for (Book book: rentBooks)
    }
    
    public void returnBookFromUser(String AccountID, String BookID, String ISBN) {

    }

    public void rentBookFromUser(String AccountID, String BookID, String ISBN) {

    }

    public void inputListener(){

    }

    public void timeJudge(String today, String startDate){
//         String[] startDateSplit = startDate.split("-");
//         String[] todaySplit = today.split("-");
//         if (startDateSplit[0].equals(todaySplit[0])){
//
//            if (startDateSplit[1].equals(todaySplit[1])){
//
//            }
//         }
//
//    }
}