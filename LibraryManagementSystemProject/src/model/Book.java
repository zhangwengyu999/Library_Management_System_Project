package model;

import controller.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Book implements SQLModel {
    private String bookID;
    private String ISBN;
    private String bookName;
    private String author;
    private String category;
//    private boolean[] status; // Length in 3, isRent, isAvailable, isPlaced, true for yes, false for no
//    private String timeStamp;
    private String location;
//    private int year = 2022;
//    private int month = 11;
//    private int day = 1;


    public Book() {}

    public Book(String bookID, String ISBN, String bookName, String publisher, String category) {
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.author = publisher;
        this.category = category;
//        this.status = new boolean[]{false,true,false};
//        this.year = inYear;
//        this.month = inMonth;
//        this.day = inDay;
    }



    // getter
    public String getISBN() {
        return ISBN;
    }

    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

//    public String getTime() {
//        String yyyy = year+"";
//        String mm = month<10?"0"+month:month+"";
//        String dd = day<10?"0"+day:day+"";
//        return yyyy+"-"+mm+"-"+dd;
//    }

//    public boolean[] getStatus() {
//        return status;
//    }

    // show related information
    public String showInfo(Book book){
        return book.getISBN() + " " + book.getBookName() + " " + book.getAuthor() + " " + book.getCategory();
    }

    // setter
    public void setISBN(String inISBN) {
        this.ISBN = inISBN;
    }

    public void setBookID(String inBookID) {
        this.bookID = inBookID;
    }

    public void setBookName(String inBookName) {
        this.bookName = inBookName;
    }

    public void setPublisher(String inAuthor) {
        this.author = inAuthor;
    }

    public void setCategory(String inCategory) {
        this.category = inCategory;
    }

//    public void setTimeStamp(String inTimeStamp) {
//        this.timeStamp = inTimeStamp;
//    }


    // for JDBC
    public SQLModel pullFromDatabase() throws SQLException {
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql =
                    "SELECT bookID, ISBN, bookName, author, bookCategory" +
                    "FROM BOOK WHERE bookID = \'" + bookID+"\'";
        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
                bookID = resultSet.getInt("bookID")+ "";
                ISBN = resultSet.getString("ISBN").trim();
                bookName = resultSet.getString("bookName").trim();
                author = resultSet.getString("author").trim();
                category = resultSet.getString("bookCategory").trim();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }

    // for JDBC
    public SQLModel pushToDatabase() throws SQLException {
        DataBase db = DataBase.getDataBase();
        if (db.contains("BOOK", "bookID", bookID)){
            String sql = "UPDATE BOOK SET ISBN = \'" + ISBN + "\', bookName = \'" + bookName + "\', author = \'" + author +
                    "\', bookCategory = \'" + category +
                        "\' WHERE bookID = " + bookID;
//            String sql2 = "UPDATE BOOK_STATUS SET isRent = " + (status[0]?"T":"F") +
//                        ", isAvailable = " + (status[1]?"T":"F") + ", isPlaced = " + (status[2]?"T":"F") +
//                        " WHERE bookID = " + bookID;
            try {
                db.update(sql);
//                db.update(sql2);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else {
            String sql = "INSERT INTO BOOK VALUES (" + bookID + ", \'" + ISBN + "\', \'" + bookName + "\', \'" + author + "\', \'"
                        + category +"\')";
//            String sql2 = "INSERT INTO BOOK_STATUS VALUES (" + bookID + ", " +getTime() + ", "+ (status[0]?"T":"F") +
//                        ", " + (status[1]?"T":"F") + ", " + (status[2]?"T":"F") + ")";
            try {
                db.insert(sql);
//                db.insert(sql2);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return this;
    }

    public void deleteFromDatabase () throws SQLException {
        DataBase db = DataBase.getDataBase();
        String sql = "DELETE FROM BOOK WHERE bookID = " + bookID;
        String sql2 = "DELETE FROM BOOK_STATUS WHERE bookID = " + bookID;
        try {
            db.query(sql);
            db.query(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
