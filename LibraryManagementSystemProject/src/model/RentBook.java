package model;
import exception.canNotHappenedException;
import controller.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RentBook implements SQLModel {
    protected String accountID;
    protected String bookID;
    protected int year;
    protected int month;
    protected int day;

    public RentBook(String inAccountID, String inBookID, int inYear, int inMonth, int inDay) {
        this.accountID = inAccountID;
        this.bookID = inBookID;
        this.year = inYear;
        this.month = inMonth;
        this.day = inDay;
    }

    public String getDate() {
        String yyyy = year+"";
        String mm = month<10?"0"+month:month+"";
        String dd = day<10?"0"+day:day+"";
        return yyyy+"-"+mm+"-"+dd;
    }

    public int[] getDateArray() {
        return new int[]{year,month,day};
    }

    public void setDate(int[] inRentDate) {
        this.year = inRentDate[0];
        this.month = inRentDate[1];
        this.day = inRentDate[2];
    }

    public SQLModel pullFromDatabase() throws SQLException{
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql = "SELECT h.bookID, h.accountID FROM HAS_RENT h WHERE bookID = \'" + bookID+"\'";
        try{
            resultSet = db.query(sql);
            while (resultSet.next()) {
                bookID = resultSet.getString("bookID").trim();
                accountID = resultSet.getString("accountID").trim();
                String temp = resultSet.getString("rentTime").trim();
                String[] temp2 = temp.split("-");
                year = Integer.parseInt(temp2[0]);
                month = Integer.parseInt(temp2[1]);
                day = Integer.parseInt(temp2[2]);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }
    public SQLModel pushToDatabase() throws SQLException {
        DataBase db = DataBase.getDataBase();
        if (db.contains("HAS_RENT","bookID",bookID)){
            try {
                throw new canNotHappenedException();
            }
            catch (canNotHappenedException e){
                e.printStackTrace();
            }
        }
        else {
            String sql = "INSERT INTO HAS_RENT VALUE( " + bookID + "," + accountID + ",\'"+ getDate()+"\')";
            db.update(sql);
        }
        return this;
    }

    public void deleteFromDatabase () throws SQLException {
        DataBase db = DataBase.getDataBase();
        String sql = "DELETE FROM HAS_RENT WHERE bookID = \'" + bookID+"\'";
        try {
            db.query(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setBook(String inBookID) {
        this.bookID = inBookID;
    }

    public void setUser(String inAccountID) {
        this.accountID = inAccountID;
    }

    public String getBookID() {
        return this.bookID;
    }

    public String getAccountID() {
        return this.accountID;
    }


//    public SQLModel pullFromDataBase() throws SQLException {
//        DataBase db = DataBase.getDataBase();
//        ResultSet resultSet;
//        String sql = "SELECT * FROM BOOK WHERE bookID = " ;
//    }
}
