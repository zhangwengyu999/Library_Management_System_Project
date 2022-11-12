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
    private String location;
    private int bookRentNum = 0;
    private int bookWantNum = 0;

    public Book() {}

    public Book(String bookID, String ISBN, String bookName, String author, String category, int bookRentNum, int bookWantNum) {
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.bookRentNum = bookRentNum;
        this.bookWantNum = bookWantNum;
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

    public int getBookRentNum(){return bookRentNum;}

    public int getBookWantNum(){return bookWantNum;}

    //new
    public void addRentBookCount() {
        bookRentNum++;
    }
    //new
    public void addWantBookCount() {
        bookWantNum++;
    }

    public void deleteWantBookCount() {bookWantNum--;}

//    public String getTime() {
//        String yyyy = year+"";
//        String mm = month<10?"0"+month:month+"";
//        String dd = day<10?"0"+day:day+"";
//        return yyyy+"-"+mm+"-"+dd;
//    }

    // show related information
    public String showInfo(){
        return "[Book ID]:"+bookID+ " [Book ISBN]: " + ISBN + " [Book Name]: " + bookName + " [Author]: " + author + " [Category]: " + category + " [Time of Rent]: " + bookRentNum + " [Time of Want]: " + getBookWantNum();
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


    // for JDBC
    public SQLModel pullFromDatabase() throws SQLException {
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql =
                    "SELECT bookID, ISBN, bookName, author, bookCategory, bookRentNum, bookWantNum" +
                    "FROM BOOK " +
                    "WHERE bookID = " + bookID;
        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
                bookID = resultSet.getInt("bookID")+ "";
                ISBN = resultSet.getString("ISBN").trim();
                bookName = resultSet.getString("bookName").trim();
                author = resultSet.getString("author").trim();
                category = resultSet.getString("bookCategory").trim();
                bookRentNum = resultSet.getInt("bookRentNum");
                bookWantNum = resultSet.getInt("bookWantNum");
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
        if (db.contains("BOOK", "bookID", Integer.parseInt(bookID))){
            String sql = "UPDATE BOOK SET ISBN = \'" + ISBN + "\', bookName = \'" + bookName + "\', author = \'" + author +
                    "\', bookCategory = \'" + category + "\', bookRentNum = \'" + bookRentNum + "\', bookWantNum = \'" + bookWantNum +
                        "\' WHERE bookID = " + bookID;
            try {
                db.update(sql);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else {
            String sql = "INSERT INTO BOOK VALUES (" + bookID + ", \'" + ISBN + "\', \'" + bookName + "\', \'" + author + "\', \'"
                        + category +"\'," + bookRentNum + ", " + bookWantNum + ")";
            try {
                db.insert(sql);
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
