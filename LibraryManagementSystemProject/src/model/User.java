package model;

import controller.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements SQLModel {
    private String accountID;
    private boolean accountStatus;
    private String noticeString;
    private int reserveCount;
    private final int MAX_WANT_BOOK = 8;
    
    public User(String accountID) {
        this.accountID = accountID;
    }

    public User(String accountID, boolean accountStatus, String noticeString) {
        this.accountID = accountID;
        this.accountStatus = accountStatus;
        this.noticeString = noticeString;
    }

    public String getAccountID() {
        return accountID;
    }

    public boolean getAccountStatus() {
        return accountStatus;
    }

    public String getNoticeString(){
        return noticeString;
    }

    public void setAccountID(String inAccountID) {
        this.accountID = inAccountID;
    }

    public void setAccountStatus(boolean inAccountStatus) {
        this.accountStatus = inAccountStatus;
    }

    public void setNoticeString(String noticeString){this.noticeString = noticeString;}

    public int getReserveCount() {
        return reserveCount;
    }

    public boolean increaseReserveCount() {
        if (reserveCount < MAX_WANT_BOOK) {
            reserveCount++;
            return true;
        }
        return false;
    }

    public boolean decreaseReserveCount() {
        if (reserveCount > 0) {
            reserveCount--;
            return true;
        }
        return false;
    }

    public SQLModel pullFromDatabase() throws SQLException {
        DataBase db = DataBase.getDataBase();
        ResultSet resultSet;
        String sql = "SELECT * FROM USER_ACCOUNT WHERE accountID = " + accountID;

        try{
            resultSet = db.query(sql);
            while (resultSet.next()){
                accountID = resultSet.getString("accountID");
                String temp = resultSet.getString("accountStatus");
                accountStatus = temp.equals("T");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }

    public SQLModel pushToDatabase() throws SQLException {
        DataBase db = DataBase.getDataBase();
        if (db.contains("USER_ACCOUNT","accountID",accountID)){
            String temp = accountStatus ? "T" : "F";
            String sql = "UPDATE USER_ACCOUNT SET accountStatus = " + temp + " WHERE accountID = " + accountID;
            try {
                db.update(sql);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else {
            String temp = accountStatus ? "T" : "F";
            String sql = "INSERT INTO USER_ACCOUNT VALUES (" + accountID + "," + temp + ")";
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
        String sql = "DELETE FROM USER_ACCOUNT WHERE accountID = " + accountID;
        try {
            db.query(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
