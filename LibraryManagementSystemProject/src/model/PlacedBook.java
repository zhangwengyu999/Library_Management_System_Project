package model;

import controller.database.DataBase;
import exception.canNotHappenedException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlacedBook extends RentBook{
    public PlacedBook(String inAccountID, String inBookID, int inYear, int inMonth, int inDay) {
        super(inAccountID, inBookID, inYear, inMonth,inDay);
    }

    public SQLModel pullFromDatabase() throws SQLException {
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql = "SELECT h.bookID, h.accountID FROM HAS_PLACED h WHERE bookID = " + bookID;
        try{
            resultSet = db.query(sql);
            while (resultSet.next()) {
                bookID = resultSet.getString("bookID");
                accountID = resultSet.getString("accountID");
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
            String sql = "INSERT INTO HAS_PLACED VALUE(" + bookID + "," + accountID + ")";
            db.update(sql);
        }
        return this;
    }

    public void deleteFromDatabase () throws SQLException {
        DataBase db = DataBase.getDataBase();
        String sql = "DELETE FROM HAS_PLACED WHERE bookID = " + bookID;
        try {
            db.query(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
