package controller;

import model.*;

import java.util.*;

public class ModelController {

    private List<Book> books;
    private List<User> users;
    private List<RentBook> rentBooks;
    private List<WantBook> wantBooks;
    private List<PlacedBook> placedBooks;

    private HashMap<String, SQLModel> bookBuffer;
    private HashMap<String, SQLModel> userBuffer;
    private HashMap<String, RentBook> rentBookBuffer; // Key: bookId, value: RendBook record
    private HashMap<String, Queue<SQLModel>> wantBookBuffer; // key: ISBN, value: User Queue
    private HashMap<String, SQLModel> placedBookBuffer;
    // private HashMap<String, Queue<SQLModel>> wantBookBuffer2; // key: accountID, value: WantBook record

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
        placedBooks = new ArrayList<>();

        bookBuffer = new HashMap<>();
        userBuffer = new HashMap<>();
        rentBookBuffer = new HashMap<>();
        wantBookBuffer = new HashMap<>();
        placedBookBuffer = new HashMap<>();
    }


    // ----------------- Method on HashMap buffer -----------------

    /* Add Book record into bookBuffer and update to the DB
     * @param book: the book to add
     */
    public void addRecord(Book book) throws Exception {
        book.pushToDatabase();
        bookBuffer.put(book.getBookID(), book);
    }

    /* Add User record into userBuffer and update to the DB
     * @param user: the uer to add
     */
    public void addRecord(User user) throws Exception {
        user.pushToDatabase();
        bookBuffer.put(user.getAccountID(),user);
    }

    /* Add rentBook record into rentBookBuffer and update to the DB
     * @param rentBook: the rentBook to add
     */
    public void addRecord(RentBook rentBook) throws Exception {
        rentBook.pushToDatabase();
        rentBookBuffer.put(rentBook.getBookID(),rentBook);
    }

    /* Add wantBook record into wantBookBuffer and update to the DB
     * @param wantBook: the wantBook to add
     */
    public void addRecord(WantBook wantBook) throws Exception {
        wantBook.pushToDatabase();

        Queue<SQLModel> queue; // Queue of User
        if (wantBookBuffer.containsKey(wantBook.getWantISBNs())) {
            queue = wantBookBuffer.get(wantBook.getWantISBNs());
        }
        else {
            queue = new LinkedList<>();
        }
        // add the user to the queue with that ISBN
        queue.add(userBuffer.get(wantBook.getUserAccountID()));
        wantBookBuffer.put(wantBook.getWantISBNs(), queue);
    }

    public void addRecord(PlacedBook placedBook) throws Exception {
        placedBook.pushToDatabase();
        rentBookBuffer.put(placedBook.getBookID(),placedBook);
    }

    // Delete book from buffer and DB
    public void deleteBookRecord(String inBookID) throws Exception {
        bookBuffer.get(inBookID).deleteFromDatabase();
        bookBuffer.remove(inBookID);
    }

    // Delete user from buffer and DB
    public void deleteUserRecord(String inAccountID) throws Exception {
        userBuffer.get(inAccountID).deleteFromDatabase();
        userBuffer.remove(inAccountID);
    }

    // Delete want book from buffer and DB
    public void deleteWantBookRecord(String inISBN, String inAccountID) throws Exception {
        Queue<SQLModel> queue = wantBookBuffer.get(inISBN);
        Queue<SQLModel> out = new LinkedList<>();
        SQLModel targetUser;
        for (SQLModel user: queue){
            if (((User)user).getAccountID().equals(inAccountID)) {
                targetUser = user;
                targetUser.deleteFromDatabase();
            }
            else{
                out.add(user);
            }
        }
        wantBookBuffer.put(inISBN, out);
    }

    // Delete rent book from buffer and DB
    public void deleteRentBookRecord(String inBookID) throws Exception {
        rentBookBuffer.get(inBookID).deleteFromDatabase();
        rentBookBuffer.remove(inBookID);
    }


    // --------------- Search on Book ---------------

    /* Search book on name inName
     * @param inName: the name of the book
     * @return List<Book>: a list of book with the name inName
     * @throws Exception: if the book is not found
     * */
    public List<Book> searchBookOnBookName(String inName) throws Exception {
        List<Book> result = new ArrayList<>();
        for (SQLModel book : bookBuffer.values()) {
            if (((Book) book).getBookName().equals(inName)) {
                result.add((Book)book.pullFromDatabase());
            }
        }
        if (result.size() == 0) {
            throw new Exception("No book found!");
        }
        return result;
    }

    /*
     * Search book on ISBN
     * @param inISBN: the ISBN of the book
     * return List<Book>: a list of book with the id
     */
    public List<Book> searchBookOnBookID(String inBookID) throws Exception {
        List<Book> result = new ArrayList<>();
        if (bookBuffer.containsKey(inBookID)) {
            result.add((Book)bookBuffer.get(inBookID));
        }
        if (result.size() == 0) {
            throw new Exception("No book found!");
        }
        return result;
    }
    /*
     * Search book on Author
     * @param inAuthor: the author of the book
     * return List<Book>: a list of book with the author
     */
    public List<Book> searchBookOnBookAuthor(String inAuthor) throws Exception {
        List<Book> result = new ArrayList<>();
        for (SQLModel book : bookBuffer.values()) {
            if (((Book) book).getBookName().equals(inAuthor)) {
                result.add((Book)book.pullFromDatabase());
            }
        }
        if (result.size() == 0) {
            throw new Exception("No book found!");
        }
        return result;
    }
    /*
     * Search book on category
     * @param inCategory: the category of the book
     * return List<Book>: a list of book with the category
     */
    public List<Book> searchBookOnBookCategory(String inCategory) throws Exception {
        List<Book> result = new ArrayList<>();
        for (SQLModel book : bookBuffer.values()) {
            if (((Book) book).getBookName().equals(inCategory)) {
                result.add((Book)book.pullFromDatabase());
            }
        }
        if (result.size() == 0) {
            throw new Exception("No book found!");
        }
        return result;
    }

    /*
     * Search book on ISBN
     * @param inCategory: the ISBN of the book
     * return List<Book>: a list of book with the category
     */
    public List<Book> searchBookOnBookISBN(String inISBN) throws Exception {
        List<Book> result = new ArrayList<>();
        for (SQLModel book : bookBuffer.values()) {
            if (((Book) book).getISBN().equals(inISBN)) {
                result.add((Book)book.pullFromDatabase());
            }
        }
        if (result.size() == 0) {
            throw new Exception("No book found!");
        }
        return result;
    }

    // --------------- Search on Book End ---------------


    // --------------- Search on Rent Book ---------------
    public List<RentBook> searchRentBookOnBookID(String inBookID) throws Exception {
        List<RentBook> result = new ArrayList<>();
        if (rentBookBuffer.containsKey(inBookID)){
            result.add((RentBook)rentBookBuffer.get(inBookID).pullFromDatabase());
        }
        else{
            throw new Exception("This book is not rent by other.");
        }
        return result;
    }

    public List<RentBook> searchRentBookOnAccountID(String inAccountID) throws Exception {
        List<RentBook> result = new ArrayList<>();
        for (SQLModel rentBook : rentBookBuffer.values()) {
            if (((RentBook) rentBook).getAccountID().equals(inAccountID)) {
                result.add((RentBook) rentBook.pullFromDatabase());
            }
        }
        if (result.size() == 0) {
            throw new Exception("This user didn't rent any books.");
        }
        return result;
    }
    // --------------- Search on Rent Book Part End---------------


    // --------------- Search on Want Book ---------------
    public List<String> searchWantBookOnAccountID(String inAccountID) throws Exception {
        List<String> result = new ArrayList<>();
        for (String isbn : wantBookBuffer.keySet()) {
            if (wantBookBuffer.get(isbn).contains(userBuffer.get(inAccountID))) {
                result.add(isbn);
            }
        }
        if (result.size() == 0) {
            throw new Exception("This user didn't reserve any books.");
        }
        return result;
    }

    public List<User> searchUserFromWantBookOnISBN(String inISBN) throws Exception {
        List<User> result = new ArrayList<>();
        if (wantBookBuffer.containsKey(inISBN)){
            for (SQLModel user : wantBookBuffer.get(inISBN)){
                result.add((User)user.pullFromDatabase());
            }
        }
        else{
            throw new Exception("This book is not unwanted by any users.");
        }
        return result;
    }
    // --------------- Search on Want Book End ---------------


    // --------------- Search on Placed Book ---------------
    public List<PlacedBook> searchPlacedBookOnAccountID(String inAccountID) throws Exception {
        List<PlacedBook> result = new ArrayList<>();
        for (SQLModel placedBook : placedBookBuffer.values()) {
            if (((PlacedBook) placedBook).getAccountID().equals(inAccountID)) {
                result.add((PlacedBook) placedBook.pullFromDatabase());
            }
        }
        if (result.size() == 0) {
            throw new Exception("This user didn't placed any books.");
        }
        return result;
    }

    public List<PlacedBook> searchPlacedBookOnBookID(String inBookID) throws Exception {
        List<PlacedBook> result = new ArrayList<>();
        if (placedBookBuffer.containsKey(inBookID)){
            result.add((PlacedBook) placedBookBuffer.get(inBookID).pullFromDatabase());
        }
        else{
            throw new Exception("This book is not placed by any users.");
        }
        return result;
    }
    // --------------- Search on Want Book End ---------------


    // --------------- Search on User ---------------
    public List<User> searchUserOnAccountID(String inAccountID) throws Exception {
        List<User> result = new ArrayList<>();
        if (userBuffer.containsKey(inAccountID)){
            result.add((User)userBuffer.get(inAccountID).pullFromDatabase());
        }
        else{
            throw new Exception("No user found.");
        }
        return result;
    }


    // --------------- Search on User End ---------------



    // ------------------ Search ------------------

//    public List<Book> searchBookOnBookName(String inName) {
//        List<Book> outBooks = new ArrayList<>();
//        for (Book book : books) {
//            if (book.getBookName().equals(inName)) {
//                outBooks.add(book);
//            }
//        }
//        if (!outBooks.isEmpty()) {
//            return outBooks;
//        } else {
//            return null;
//        }
//    }

//    public List<Book> searchBookOnBookISBN(String inISBN) {
//        List<Book> outBooks = new ArrayList<>();
//        for (Book book : books) {
//            if (book.getBookName().equals(inISBN)) {
//                outBooks.add(book);
//            }
//        }
//        if (!outBooks.isEmpty()) {
//            return outBooks;
//        } else {
//            return null;
//        }
//    }

//    public List<Book> searchBookOnBookAuthor(String inAuthor) {
//        List<Book> outBooks = new ArrayList<>();
//        for (Book book : books) {
//            if (book.getBookName().equals(inAuthor)) {
//                outBooks.add(book);
//            }
//        }
//        if (!outBooks.isEmpty()) {
//            return outBooks;
//        } else {
//            return null;
//        }
//    }

//    public List<Book> searchBookOnBookCategory(String inCategory) {
//        List<Book> outBooks = new ArrayList<>();
//        for (Book book : books) {
//            if (book.getBookName().equals(inCategory)) {
//                outBooks.add(book);
//            }
//        }
//        if (!outBooks.isEmpty()) {
//            return outBooks;
//        } else {
//            return null;
//        }
//    }

//    public List<Book> searchBookOnBookID(String inBookID) {
//        List<Book> outBooks = new ArrayList<>();
//        for (Book book : books) {
//            if (book.getBookName().equals(inBookID)) {
//                outBooks.add(book);
//                break;
//            }
//        }
//        if (!outBooks.isEmpty()) {
//            return outBooks;
//        } else {
//            return null;
//        }
//    }
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
    public boolean reserveBook(String inAccountID, String inISBN) {
        // The book is in the library for sure
        List<Book> foundBooks;
        try{
            foundBooks = searchBookOnBookISBN(inISBN);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

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
        List<Book> foundBooks;
        try{
            foundBooks = searchBookOnBookISBN(inISBN);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
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
//        if (searchBookOnBookISBN(ISBN) != null) {

//        }
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
        try{
            List<Book> searchRentBooks = searchBookOnBookID(bookID);
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
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void returnBookFromUser(String bookID) {
        try{
            for (RentBook rentbook : rentBooks) {
                if (bookID.equals(rentbook.getBookID())) {
//                if(accountID.equals(rentbook.getUser())){
                    List<Book> searchReturnBooks = searchBookOnBookID(bookID);
                    for (Book book : searchReturnBooks) {
                        for (WantBook wantbook : wantBooks) {
                            book.setRent(false);
                            List<Book> searchReturnBooks1 = searchBookOnBookISBN(bookID);
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
        catch (Exception e) {
            e.printStackTrace();
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
