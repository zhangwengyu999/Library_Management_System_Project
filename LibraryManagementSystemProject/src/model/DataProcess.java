package model;

import controller.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataProcess {

    public SQLModel getTotalBookNumber() throws SQLException {
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

    public SQLModel getMostRentBook() throws SQLException {



        return (SQLModel) this;
    }
    public SQLModel getLeastRentBook() throws SQLException {
        return (SQLModel) this;
    }
    public SQLModel getAverageRentBook() throws SQLException {
        return (SQLModel) this;
    }
    public SQLModel getBorrowedBook() throws SQLException {
        return (SQLModel) this;
    }
//    public SQLModel getMostRentBook() throws SQLException {
//        return (SQLModel) this;
//    }
//    public SQLModel getMostRentBook() throws SQLException {
//        return (SQLModel) this;
//    }


    //

    /*
    BOOK
    1. Total number of books
    2. Most rented books (favourite)
    3. Least rented books (least favourite)
    4. Average number of books borrowed per day/week/month/year
    5.  Borrowed books
    6. Most borrowed books by category
    7. Most borrowed books by author

    */
}
