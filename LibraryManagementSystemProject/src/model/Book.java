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
    private boolean[] status; // Length in 3, isRent, isAvailable, isPlaced, true for yes, false for no
    private String timeStamp;
    private String location;


    public Book() {}

    public Book(String bookID, String ISBN, String bookName, String publisher, String category, String timeStamp){
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.author = publisher;
        this.category = category;
        this.status = new boolean[]{false,true,false};
        this.timeStamp = timeStamp;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public boolean[] getStatus() {
        return status;
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

    public void setTimeStamp(String inTimeStamp) {
        this.timeStamp = inTimeStamp;
    }

    public void setRent(boolean inRent) {
        this.status[0] = inRent;
    }

    public void setAvailable(boolean inAvailable) {
        this.status[1] = inAvailable;
    }

    public void setPlaced(boolean inPlaced) {
        this.status[2] = inPlaced;
    }

    // for JDBC
    public SQLModel pullFromDatabase() throws SQLException {
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql = "SELECT * FROM BOOK WHERE bookID = " + bookID;

        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
                bookID = resultSet.getInt("bookID")+ "";
                ISBN = resultSet.getString("ISBN");
                bookName = resultSet.getString("bookName");
                author = resultSet.getString("author");
                category = resultSet.getString("bookCategory");
                //timeStamp = resultSet.getString("timeStamp");
                //location = resultSet.getString("location");
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
            String sql = "UPDATE BOOK SET ISBN = " + ISBN + ", bookName = " + bookName + ", author = " + author + ", bookCategory = " + category + " WHERE bookID = " + bookID;
            try {
                db.update(sql);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else {
            String sql = "INSERT INTO BOOK VALUES (" + bookID + ", " + ISBN + ", " + bookName + ", " + author + ", " + category + ", " + timeStamp + ", " + location + ")";
            try {
                db.inert(sql);
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
        try {
            db.query(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
