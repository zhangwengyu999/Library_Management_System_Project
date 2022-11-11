package model;

import controller.database.DataBase;
import exception.canNotHappenedException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlacedBook extends RentBook implements SQLModel {
    public PlacedBook(String inAccountID, String inBookID, int inYear, int inMonth, int inDay) {
        super(inAccountID, inBookID, inYear, inMonth,inDay);
    }

    public String getDate() {
        String yyyy = year+"";
        String mm = month<10?"0"+month:month+"";
        String dd = day<10?"0"+day:day+"";
        return yyyy+"-"+mm+"-"+dd;
    }

    public SQLModel pullFromDatabase() throws SQLException {
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql = "SELECT h.bookID, h.accountID, h.placeTime FROM HAS_PLACED h WHERE bookID = " + bookID;
        try{
            resultSet = db.query(sql);
            while (resultSet.next()) {
                bookID = resultSet.getInt("bookID")+"";
                accountID = resultSet.getInt("accountID")+"";
                String temp = resultSet.getString("placeTime").trim();
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
        if (!db.contains("HAS_RENT","bookID",bookID)){
            String sql = "INSERT INTO HAS_PLACED VALUES(" + bookID + "," + accountID +",\'"+ getDate()+"\')";
            db.insert(sql);
        }

        return this;
    }

    public void deleteFromDatabase () throws SQLException {
        DataBase db = DataBase.getDataBase();
        String sql = "DELETE FROM HAS_PLACED " +
                    "WHERE bookID = " + bookID+" AND accountID = "+accountID;
        try {
            db.query(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
