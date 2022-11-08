package controller;

import model.*;

import java.sql.SQLException;
import java.util.*;

public class ModelController {

    private List<Book> books;
    private List<User> users;
    private List<RentBook> rentBooks;
    private List<WantBook> wantBooks;
    private List<PlacedBook> placedBooks;

    private HashMap<String, Book> bookBuffer; // key: bookId, value: Book object
    private HashMap<String, User> userBuffer; // key: accountID, value: User object
    private HashMap<String, RentBook> rentBookBuffer; // Key: bookId, value: RendBook record
    private HashMap<String, Queue<User>> wantBookBuffer; // key: ISBN, value: User Queue
    private HashMap<String, PlacedBook> placedBookBuffer; // key: bookId, value: PlacedBook record

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


    // ----------------- Add on HashMap buffer -----------------
    /**
     * Add Book record into bookBuffer and update to the DB
     * @param book: the book to add
     */
    public void addRecord(Book book) {
        try{
            book.pushToDatabase();
        }
        catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        bookBuffer.put(book.getBookID(), book);
        System.out.println("Book added to database successfully");
    }

    /**
     * Add User record into userBuffer and update to the DB
     * @param user: the uer to add
     */
    public void addRecord(User user) throws Exception {
        user.pushToDatabase();
        userBuffer.put(user.getAccountID(),user);
    }

    /**
     * Add rentBook record into rentBookBuffer and update to the DB
     * @param rentBook: the rentBook to add
     */
    public void addRecord(RentBook rentBook) throws Exception {
        rentBook.pushToDatabase();
        rentBookBuffer.put(rentBook.getBookID(),rentBook);
    }

    /**
     * Add wantBook record into wantBookBuffer and update to the DB
     * @param wantBook the wantBook to add
     */
    public void addRecord(WantBook wantBook) throws Exception {
        wantBook.pushToDatabase();

        Queue<User> queue; // Queue of User
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

    /**
     * Add placedBook record into placedBookBuffer and update to the DB
     * @param placedBook: the placedBook to add
     */
    public void addRecord(PlacedBook placedBook) throws Exception {
        placedBook.pushToDatabase();
        rentBookBuffer.put(placedBook.getBookID(),placedBook);
    }


    // ----------------- Delete on HashMap buffer -----------------
    /**
     * Delete Book record from bookBuffer and update to the DB
     * @param inBookID: the book to remove
     */
    public void deleteBookRecord(String inBookID) throws Exception {
        bookBuffer.get(inBookID).deleteFromDatabase();
        bookBuffer.remove(inBookID);
    }

    /**
     * Delete User record from userBuffer and update to the DB
     * @param inAccountID: the user to remove
     */
    public void deleteUserRecord(String inAccountID) throws Exception {
        userBuffer.get(inAccountID).deleteFromDatabase();
        userBuffer.remove(inAccountID);
    }

    /**
     * Delete rentBook record from rentBookBuffer and update to the DB
     * @param inISBN: the want book with the ISBN
     * @param inAccountID: the want user with the accountID
     */
    public void deleteWantBookRecord(String inISBN, String inAccountID) throws Exception {
        Queue<User> queue = wantBookBuffer.get(inISBN);
        Queue<User> out = new LinkedList<>();
        SQLModel targetUser;
        for (User user: queue){
            if ((user).getAccountID().equals(inAccountID)) {
                targetUser = user;
                targetUser.deleteFromDatabase();
            }
            else{
                out.add(user);
            }
        }
        wantBookBuffer.put(inISBN, out);
    }

    /**
     * Delete rent book from buffer and DB
     * @param inBookID: the placed book with the bookID
     */
    public void deleteRentBookRecord(String inBookID) throws Exception {
        rentBookBuffer.get(inBookID).deleteFromDatabase();
        rentBookBuffer.remove(inBookID);
    }


    // --------------- Search on Book ---------------
    /**
     * Search book on name inName
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

    /**
     * Search book on ISBN
     * @param inBookID: the ISBN of the book
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

    /**
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
    /**
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

    /**
     * Search book on ISBN
     * @param inISBN: the ISBN of the book
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


    // --------------- Search on Rent Book ---------------
    /**
     * Search rent book on bookID
     * @param inBookID the bookID of the rent book
     * @return List<RentBook>: a list of rent book with the bookID
     * @throws Exception if the rent book is not found
     */
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

    /**
     * Search rent book on accountID
     * @param inAccountID the accountID of the rent book
     * @return List<RentBook>: a list of rent book with the accountID
     * @throws Exception if the rent book is not found
     */
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

    // --------------- Search on Want Book ---------------

    /**
     * Search want book on accountID
     * @param inAccountID the accountID of the user want the book
     * @return List<WantBook>: a list of want book ISBN
     * @throws Exception if the want book is not found
     */
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

    /**
     * Search want user form want book ISBN
     * @param inISBN the ISBN of the book
     * @return List<User>: a list of user want the book
     * @throws Exception if the want book is not found
     */
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


    // --------------- Search on Placed Book ---------------

    /**
     * Search placed book on accountID
     * @param inAccountID the accountID of the user that placed the book for
     * @return List<PlacedBook>: a list of placed book with the accountID
     * @throws Exception
     */
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

    /**
     * Search placed book on bookID
     * @param inBookID the bookID of the placed book
     * @return List<PlacedBook>: a list of placed book with the bookID
     * @throws Exception
     */
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


    // --------------- Search on User ---------------

    /**
     * Search user on accountID
     * @param inAccountID the accountID of the user
     * @return List<User>: the user with the accountID
     * @throws Exception
     */
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


    // ----------------- de/activate User -----------------

    /**
     * deactivates a user by accountID
     * @param inAccountID: the accountID of the user to be deactivated
     * @return boolean: true if the user is successfully deactivated, false if the user is already deactivated
     */
    public boolean deactivateUser(String inAccountID) {
        if (userBuffer.containsKey(inAccountID)) {
            try{
                User user = (User) userBuffer.get(inAccountID).pullFromDatabase();
                if (user.getAccountStatus()) {
                    user.setAccountStatus(false);
                    user.pushToDatabase();
                    return true;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * activates a user by accountID
     * @param inAccountID: the accountID of the user to be activated
     * @return boolean: true if the user is successfully activated, false if the user is already activated
     */

    public boolean activateUser(String inAccountID) {
        if (userBuffer.containsKey(inAccountID)) {
            try{
                User user = (User) userBuffer.get(inAccountID).pullFromDatabase();
                if (!user.getAccountStatus()) {
                    user.setAccountStatus(true);
                    user.pushToDatabase();
                    return true;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }


    // ---------------- reverse book -----------
    /**
     * Reserve a book with a ISBN whether it is available in the Library or not,
     * if not available, put the pair of <User ID, ISBN> object into wantBook array;
     * if available, tell the user to go to the library to find it.
     * @param inAccountID: the user ID of the user who wants to reserve a book
     * @param inISBN: the ISBN of the book the user wants to reserve
     * @return boolean: true if the book is successfully reserved, false if the book is not available
     */
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
            try{
                addRecord(wantBook);
            }
            catch (Exception e){
                e.printStackTrace();
                return false;
            }
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

    /**
    * check the expired rent books and return the list of expired rent books
    * @return List<RentBook> expired rent Book and User
    * @author Mike
    * */
    public List<RentBook> getExpiredRentBook() {
        List<RentBook> output = new ArrayList<>();
        for (RentBook rentBook: rentBookBuffer.values()) {
            try {
                rentBook = (RentBook) rentBook.pullFromDatabase();
                int year = rentBook.getDate()[0];
                int month = rentBook.getDate()[1];
                int day = rentBook.getDate()[2];
                int rentDays = DayCalculator.dayApart(year, month, day, this.year, this.month, this.day);
                if (rentDays > MAX_RENT_DAY) {
                    output.add(rentBook);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    /**
     * check the expired rent books of a user and return the list of expired rent books
     * @param inAccountID: the user who wants to check the expired rent books
     * @return List<RentBook> expired rent Book of this user
     */
    public List<RentBook> getExpiredRentBook(String inAccountID) {
        return null;
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
