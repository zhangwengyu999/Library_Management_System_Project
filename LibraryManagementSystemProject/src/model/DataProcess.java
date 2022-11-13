package model;

import controller.database.DataBase;

//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataProcess {

    public int getTotalBookNumber() throws SQLException {

        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql =
                "SELECT COUNT(b.bookID) FROM BOOK b";
        try{
            resultSet = db.query(sql);
            return resultSet.getInt(1);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> getMostRentBook() throws SQLException {
        List<Book> result = new ArrayList<>();
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql =
                "SELECT bookName FROM Book GROUP BY ISBN WHERE SUM(bookRentNum) >= ALL(SELECT SUM(bookRentNum) FROM BOOK GROUP BY ISBN)";
        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
                // return all info of resultSet
//                result.add();
                }



        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.singletonList(" ");
    }
    public Book getLeastRentBook() throws SQLException {
        Book book = new Book();
        return book;
    }
    public double getAverageRentBookByDay() throws SQLException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        double average = 0;
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
//        String sql = "SELECT convert(varchar(8),[PayTime],112),COUNT(DISTINCT Account),FROM 某某数据库.[tb_ChargeLog] GROUP BY convert(varchar(8),[PayTime],112) ORDER BY "
//        try {
//            resultSet = db.query(sql);
//            average =
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        return average;
    }

    public double getAverageRentBookByWeek() throws SQLException {
        double average = 0;
        return average;
    }

    public double getAverageRentBookByMonth() throws SQLException {
        double average = 0;
        return average;
    }

    public double getAverageRentBookByYear() throws SQLException {
        double average = 0;
        return average;
    }
    public int getTotalBorrowedBook() throws SQLException {
        int totalBorrowedNumber = 0;
        return totalBorrowedNumber;
    }
    public Book getMostBorrowedBookByCategory() throws SQLException {
        Book book = new Book();
        return book;
    }
    public Book getMostBorrowedBookByAuthor() throws SQLException {
        Book book = new Book();
        return book;
    }


    //

    /*
    BOOK
    1. Total number of books
    2. Most rented books (favourite)
    3. Least rented books (least favourite)
    4. Average number of books borrowed per day/week/month/year
    5. Borrowed books
    6. Most borrowed books by category
    7. Most borrowed books by author

    */
}
