package model;

import controller.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataProcess {

    public SQLModel getBookCount() throws SQLException {
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql =
                "SELECT COUNT(b.bookID) FROM BOOK b";
        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
//                bookID = resultSet.getInt("bookID") + "";
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return (SQLModel) this;
    }

    //

    /*
    BOOK
    1. Total number of books
    2. Most rented books (favourite)
    3. Least rented books (least favourite)
    4. Average number of books borrowed per day/week/month/year
    5. Average number of books borrowed per user
    6. Borrowed books
    7. Overdue books
    8. Most borrowed books by category
    9. Most borrowed books by author
    */
    
    /*
    ACCOUNT
    1. Number of visitors
    2. Most active users (borrowed most books)
    3. New members
     */



}
