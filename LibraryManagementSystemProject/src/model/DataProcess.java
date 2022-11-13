package model;


import controller.ModelController;
import controller.database.DataBase;

//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;




public class DataProcess {
    ModelController modelController = new ModelController();

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
        List<String> result = new ArrayList<>();
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql =
                "SELECT bookName " +
                        "FROM Book GROUP BY ISBN " +
                        "WHERE SUM(bookRentNum) >= ALL(SELECT SUM(bookRentNum) " +
                                                    "FROM BOOK GROUP BY ISBN)";
        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
                String temp = resultSet.getInt(1)+"";
                result.add(temp);

                }
            return result;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.singletonList(" ");
    }

    public List<Book> getLeastRentBook() throws SQLException {
        List<Book> result = new ArrayList<>();
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql =
                "select A.bookId\n" +
                        "from book A \n" +
                        "where A.bookRentNum <= all (select bookRentNum\n" +
                        "from book B);";

        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
                String targetBookId= resultSet.getInt(1)+"";
                Book book = modelController.searchBookOnBookID(targetBookId).get(0);
                result.add(book);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }


//    public double getAverageRentBookByDay() throws SQLException {
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        double average = 0;
//        DataBase db = DataBase.getDataBase();
//        ResultSet resultSet;
////        String sql = "SELECT convert(varchar(8),[PayTime],112),COUNT(DISTINCT Account),FROM 某某数据库.[tb_ChargeLog] GROUP BY convert(varchar(8),[PayTime],112) ORDER BY "
////        try {
////            resultSet = db.query(sql);
////            average =
////        }
////        catch (SQLException e) {
////            e.printStackTrace();
////        }
//        return average;
//    }
//
//    public double getAverageRentBookByWeek() throws SQLException {
//        double average = 0;
//
//        return average;
//    }
//
//    public double getAverageRentBookByMonth() throws SQLException {
//        double average = 0;
//        return average;
//    }
//
//    public double getAverageRentBookByYear() throws SQLException {
//        double average = 0;
//        return average;
//    }

    public int getTotalBorrowedBook() throws SQLException {
        int totalBorrowedNumber = 0;
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql =
                "SELECT SUM(bookRentNum) FROM BOOK";
        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
                return resultSet.getInt(1);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return totalBorrowedNumber;
    }
    public List<Book> getMostBorrowedBookByCategory() throws SQLException {
        List<Book> result = new ArrayList<>();
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql =
                "SELECT A.bookID\n" +
                        "FROM book A\n" +
                        "WHERE A.bookRentNum = (select max(B.bookRentNum)\n" +
                        "                    FROM book B\n" +
                        "                    WHERE A.bookCategory = B.bookCategory );";
        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
                 String targetBookId= resultSet.getInt(1)+"";
                 Book book = modelController.searchBookOnBookID(targetBookId).get(0);
                 result.add(book);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public List<Book> getMostBorrowedBookByAuthor() throws SQLException {
        List<Book> result = new ArrayList<>();
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql =
                "SELECT A.bookID\n" +
                        "FROM book A\n" +
                        "WHERE A.bookRentNum = (select max(B.bookRentNum)\n" +
                        "                    FROM book B\n" +
                        "                    WHERE A.author = B.author);";
        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
                String targetBookId= resultSet.getInt(1)+"";
                Book book = modelController.searchBookOnBookID(targetBookId).get(0);
                result.add(book);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
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
