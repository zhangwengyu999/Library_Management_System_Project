package controller.database;
import java.io.*;
import java.io.Console;
import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class DataBase {
    private static final DataBase dataBase = new DataBase();
    private static OracleConnection connection;
    private static final String username = "\"21098431d\"";
    private static final String pwd = "getPassword";
    private static final String url = "jdbc:oracle:thin:@studora.comp.polyu.edu.hk:1521:dbms";

    public static DataBase getDataBase() {
        return dataBase;
    }

    /*
     * initialize the connection to the database
     */
    public static void initializeConnection() throws SQLException, IOException {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = (OracleConnection)DriverManager.getConnection(url,username,pwd);
        }
        catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            throw e;
        }
    }

    /*
     * close the connection
     */
    public static void closeConnection() throws SQLException {
        try{
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            throw e;
        }
    }

    /*
     * query the database and get the ResultSet
     * @param sql: the sql statement
     * @return: the result set
     */
    public ResultSet query(String inSql) throws SQLException {
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(inSql);
        }
        catch (SQLException e) {
            System.out.println("Query Failed!");
            e.printStackTrace();
            throw e;
        }
        return resultSet;
    }

    /*
     * check whether inObject is in the inTable's inAttr
     * @param inTable: the table to be checked
     * @param inAttr: the attribute to be checked
     * @param inObject: the object to be checked
     * @return: true if inObject is in the inTable's inAttr
     */
    public boolean contains(String inTable, String inAttr, Object inObject) throws SQLException {
        String sql = "SELECT * FROM " + inTable + " WHERE " + inAttr + " = " + inObject;
        try{
            ResultSet resultSet = query(sql);
            if (resultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println("Query Failed!");
            e.printStackTrace();
            throw e;
        }
        return false;
    }

    /*
     * update the database
     * @param sql: the sql statement
     */
    public void update(String inSql) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(inSql);
        }
        catch (SQLException e) {
            System.out.println("Update Failed!");
            e.printStackTrace();
            throw e;
        }
    }

    /*
     * Delete the data in the table
     * @param sql: the sql statement
     */
    public void delete(String inSql) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(inSql);
        }
        catch (SQLException e) {
            System.out.println("Delete Failed!");
            e.printStackTrace();
            throw e;
        }
    }

    /*
     * insert the data into the table
     * @param sql: the sql statement
     */
    public void inert(String inSql) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(inSql);
        }
        catch (SQLException e) {
            System.out.println("Insert Failed!");
            e.printStackTrace();
            throw e;
        }
    }

    /*
     * commit the changes
     */
    public void commit() throws SQLException {
        try {
            connection.commit();
        }
        catch (SQLException e) {
            System.out.println("Commit Failed!");
            e.printStackTrace();
            throw e;
        }
    }
}
