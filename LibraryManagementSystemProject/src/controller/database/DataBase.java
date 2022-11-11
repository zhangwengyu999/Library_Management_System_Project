package controller.database;
import java.io.*;
import java.io.Console;
import java.sql.*;
import oracle.jdbc.driver.*;
import oracle.sql.*;

public class DataBase {
    private static final DataBase dataBase;

    static {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static OracleConnection connection;
    private static final String username = "\"21098431d\"";
    private static final String pwd = "getPassword";
    private static final String url = "jdbc:oracle:thin:@studora.comp.polyu.edu.hk:1521:dbms";

    private DataBase() throws SQLException {
        try{
            initializeConnection();
        }

        catch(SQLException e){
            System.out.println("Connection Failed!");
            e.printStackTrace();
            throw e;
        }
    }

    public static DataBase getDataBase() {
        return dataBase;
    }

    public void reConnect() {
        try {
            connection.close();
            initializeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * initialize the connection to the database
     */
    private void initializeConnection() throws SQLException {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = (OracleConnection)DriverManager.getConnection(url,username,pwd);
            System.out.println("DB Connection OK!");
        }
        catch (SQLException e) {
            System.out.println("DB Connection Failed!");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * close the connection
     */
    public void closeConnection() throws SQLException {
        try{
            connection.close();
            System.out.println("Close OK!");
        }
        catch (SQLException e) {
            System.out.println("Close Failed!");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * query the database and get the ResultSet
     * @param inSql: the sql statement
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

    /**
     * check whether inObject is in the inTable's inAttr
     * @param inTable: the table to be checked
     * @param inAttr: the attribute to be checked
     * @param inObject: the object to be checked
     * @return: true if inObject is in the inTable's inAttr
     */
    public boolean contains(String inTable, String inAttr, String inObject) throws SQLException {
        String sql = "SELECT * FROM " + inTable + " WHERE " + inAttr + " = \'" + inObject+"\'";
        try{
            System.out.println(sql);
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

    /** change to int
     */
    public boolean contains(String inTable, String inAttr, int inObject) throws SQLException {
        String sql = "SELECT * FROM " + inTable + " WHERE " + inAttr + " = " + inObject;
        try{
            System.out.println(sql);
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

    /**
     * check whether inObjects are in the inTable's inAttrs
     * @param inTable: the table to be checked
     * @param inAttr1: the attribute to be checked
     * @param inObject1: the object to be checked
     * @param inAttr2: the attribute to be checked
     * @param inObject2: the object to be checked
     * @return: true if inObjects are in the inTable's inAttrs
     */
    public boolean contains(String inTable, String inAttr1, String inAttr2, String inObject1, String inObject2) throws SQLException {
        String sql = "SELECT * FROM " + inTable + " WHERE " + inAttr1 + " = \'" + inObject1 + "\' AND " + inAttr2 + " = \'" + inObject2+"\'";
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
//            commit();
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
//            commit();
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
    public void insert(String inSql) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(inSql);
//            commit();
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
