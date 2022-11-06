package controller;

import model.*;

import java.util.*;

public class ModelController {

    private List<Book> books;
    private List<User> users;
    private List<RentBook> rentBooks;
    private List<WantBook> wantBooks;

    private HashMap<String, SQLModel> bookBuffer;
    private HashMap<String, SQLModel> userBuffer;
    private HashMap<String, List<SQLModel>> rentBookBuffer;
    private HashMap<String, List<SQLModel>> wantBookBuffer;

    private final int MAX_RENT_DAY = 14;
    private final int MAX_PLACED_DAY = 7;
    private final int MAX_WANT_BOOK = 8;
    private int year = 2022;
    private int month = 11;
    private int day = 1;

    public ModelController() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        rentBooks = new ArrayList<>();
        wantBooks = new ArrayList<>();

        bookBuffer = new HashMap<>();
        userBuffer = new HashMap<>();
        rentBookBuffer = new HashMap<>();
        wantBookBuffer = new HashMap<>();
    }


    // ----------------- Method for HashMap buffer -----------------


    // Add item into buffer
    public void addToBuffer(Book book) throws Exception {
        book.pushToDatabase();
        bookBuffer.put(book.getBookID(), book);
    }

    public void addToBuffer(User user) throws Exception {
//        book.pushToDatabase();
//        bookBuffer.put(book.getBookID(),book);
    }

    public void addToBuffer(RentBook book) throws Exception {
        // ...
    }

    public void addToBuffer(WantBook book) throws Exception {
        // ...
    }


    // Delete item from buffer
    public void deleteFromBuffer(String bookID) throws Exception {
        bookBuffer.get(bookID).deleteFromDatabase();
        bookBuffer.remove(bookID);
    }
    // ...


    // Search book
    public List<SQLModel> searchBookOnName(String inName) throws Exception {
        List<SQLModel> result = new ArrayList<>();
        for (SQLModel book : bookBuffer.values()) {
            if (((Book) book).getBookName().equals(inName)) {
                result.add(book.pullFromDatabase());
            }
        }
        return result;
    }

    // ----------------- Method for HashMap buffer End -----------------


    // ------------------ Search ------------------

    public List<Book> searchBookByNameFromBooks(String inName) {
        List<Book> outBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookName().equals(inName)) {
                outBooks.add(book);
            }
        }
        if (!outBooks.isEmpty()) {
            return outBooks;
        } else {
            return null;
        }
    }

    public List<Book> searchBookByISBNFromBooks(String inISBN) {
        List<Book> outBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookName().equals(inISBN)) {
                outBooks.add(book);
            }
        }
        if (!outBooks.isEmpty()) {
            return outBooks;
        } else {
            return null;
        }
    }

    public List<Book> searchBookByAuthorFromBooks(String inAuthor) {
        List<Book> outBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookName().equals(inAuthor)) {
                outBooks.add(book);
            }
        }
        if (!outBooks.isEmpty()) {
            return outBooks;
        } else {
            return null;
        }
    }

    public List<Book> searchBookByCategoryFromBooks(String inCategory) {
        List<Book> outBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookName().equals(inCategory)) {
                outBooks.add(book);
            }
        }
        if (!outBooks.isEmpty()) {
            return outBooks;
        } else {
            return null;
        }
    }

    public List<Book> searchBookByBookID(String inBookID) {
        List<Book> outBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookName().equals(inBookID)) {
                outBooks.add(book);
                break;
            }
        }
        if (!outBooks.isEmpty()) {
            return outBooks;
        } else {
            return null;
        }
    }
    // -----------------------------------------------

    // ----------------- de/activate User -----------------

    public boolean deactivateUser(User inUser) {
        if (inUser.getAccountStatus()) {
            inUser.setAccountStatus(false);
            return true;
        } else {
            return false;
        }
    }

    public boolean activateUser(User inUser) {
        if (!inUser.getAccountStatus()) {
            inUser.setAccountStatus(true);
            return true;
        } else {
            return false;
        }
    }
    // -----------------------------------------------


    // Reserve a book with a ISBN whether it is available in the Library or not,
    // if not available, put the pair of <User ID, ISBN> object into wantBook array;
    // if available, tell the user to go to the library to find it.
    public boolean reserveBook(String inISBN, String inAccountID) {
        // The book is in the library for sure
        List<Book> foundBooks = searchBookByISBNFromBooks(inISBN);
        boolean canBeReserved = true;
        for (Book book : foundBooks) {
            if (book.getStatus()[1]) {
                canBeReserved = false;
                break;
            }
        }
        if (canBeReserved) {
            WantBook wantBook = new WantBook(inAccountID, inISBN, year, month, day);
            wantBooks.add(wantBook);
            return true;
        } else {
            // ...
            System.out.println("The book is not available now, please find it in the library.");
            return false;
        }
    }


    // 这个人借的书已变成isPlaced，要对wantBookList对应的<ID,ISBN>和（对于下一个想要这个ISBN的人的更新或书籍状态更新）
    // 这个人借的书已变成isPlaced且超出期限，如果有人想要进行借阅，则更新wantBookList对应的<ID,ISBN>和对于下一个想要这个ISBN的人，否则则改变为isAvailable

    public void cancelReservedBook(String inISBN, User inUser) {
        // Only a reserved book can be cancelled
        if (!reserveBook(inISBN, inUser.getAccountID())) return;
        List<Book> foundBooks = searchBookByISBNFromBooks(inISBN);
        boolean isAllRent = true;
        for (Book book : foundBooks) {
            if (!book.getStatus()[0]) {
                isAllRent = false;
            }
        }
        //
        if (isAllRent) {
            // cancel reserve of inUser and inISBN
            for (WantBook wantBook : wantBooks) {
                if (inUser.getAccountID().equals(wantBook.getUserAccountID()) && inISBN.equals(wantBook.getWantISBNs())) {
                    wantBooks.remove(wantBook);
                }
            }
        } else {
            // cancel reserve of inUser and inISBN
            for (WantBook wantBook : wantBooks) {
                if (inUser.getAccountID().equals(wantBook.getUserAccountID()) && inISBN.equals(wantBook.getWantISBNs())) {
                    wantBooks.remove(wantBook);
                }
                // check whether there exists the next user in wantBook after inUser
                if (inISBN.equals(wantBook.getWantISBNs())) {
                    // if any, notify to the next user and update the day (+7)


                } else {
                    // if not, reset book status to isAvailable
                }
            }
        }
        // 如果大于7天期限，强制取消

    }

    public void notificationToUser(String ISBN, User inUser) {

//        ISBN = book.getISBN();
//        status = book.getStatus();
        if (searchBookByISBNFromBooks(ISBN) != null) {

        }
    }

    public void generateAnalysisReport() {

    }

    public List<RentBook> getExpiredRentBookAndUser() {
        List<RentBook> output = new ArrayList<>();
        for (int i = 0; i < rentBooks.size(); i++) {
            RentBook rentBook = rentBooks.get(i);
            int year = rentBook.getDate()[0];
            int month = rentBook.getDate()[1];
            int day = rentBook.getDate()[2];
            int rentDays = DayCalculator.dayApart(year, month, day, this.year, this.month, this.day);
            if (rentDays > MAX_RENT_DAY) {
                output.add(rentBook);
            }
        }
        return output;
    }


    public void rentBookFromUser(String accountID, String bookID) {
        List<Book> searchRentBooks = searchBookByBookID(bookID);
        for (Book book : searchRentBooks) {
            if (book.getStatus()[2]) {
                RentBook rentBook = new RentBook(accountID, bookID, year, month, day);
                book.setRent(true);
                book.setAvailable(false);
                rentBooks.add(rentBook);
                break;
            } else {
                System.out.println("This book is not available for rent.");
            }
        }
    }

    public void returnBookFromUser(String bookID) {
        for (RentBook rentbook : rentBooks) {
            if (bookID.equals(rentbook.getBook())) {
//                if(accountID.equals(rentbook.getUser())){
                List<Book> searchReturnBooks = searchBookByBookID(bookID);
                for (Book book : searchReturnBooks) {
                    for (WantBook wantbook : wantBooks) {
                        book.setRent(false);
                        List<Book> searchReturnBooks1 = searchBookByISBNFromBooks(bookID);
                        for (Book book1 : searchReturnBooks1) {
                            if (wantbook.getWantISBNs().equals(book1.getISBN())) {
                                book.setPlaced(true);
                                // notificationToUser();
                                break;
                            } else {
                                book.setAvailable(true);
                                break;
                            }
                        }
                    }
                    rentBooks.remove(rentbook);
                    break;
                }
//                }

            }
        }

    }
}

//    public void returnBookFromUser(String accountID, String bookID){
//        for (RentBook rentbook:rentBooks){
//            if (bookID.equals(rentbook.getBook())){
////                if(accountID.equals(rentbook.getUser())){
//                    List<Book> searchReturnBooks = searchBookByBookID(bookID);
//                    for (Book book: searchReturnBooks){
//                        for (WantBook wantbook: wantBooks){
//                            book.setRent(false);
//                            List<Book> searchReturnBooks1 = searchBookByISBNFromBooks(bookID);
//                            for (Book book1: searchReturnBooks1){
//                                if (wantbook.getWantISBNs().equals(book1.getISBN())){
//                                    book.setPlaced(true);
//                                    // notificationToUser();
//                                    break;
//                                }
//                                else{
//                                    book.setAvailable(true);
//                                    break;
//                                }
//                            }
//                        }
//                        rentBooks.remove(rentbook);
//                        break;
//                    }
////                }
//
//            }
//        }
//
//    }
//
//}
