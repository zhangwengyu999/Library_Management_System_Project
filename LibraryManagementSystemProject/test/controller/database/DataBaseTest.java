package controller.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseTest {
    DataBase db;

    @BeforeEach
    void setup() {
        db = DataBase.getDataBase();
    }

    @Test
    void queryTest1() {
        List<String> out = new ArrayList<>();
        String sql = "SELECT STUDENT_NAME FROM STUDENT";
        try{
            ResultSet resultSet = db.query(sql);
            while (resultSet.next()){
                out.add(resultSet.getString(1).trim());
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        List<String> expected = new ArrayList<>();
        expected.add("Mike");
        expected.add("Alice");
        expected.add("Jack JONES");
        expected.add("Mike JONES");
        expected.add("Tom JONES");
        expected.add("Alice JONES");
        expected.add("Bob JONES");
        assertEquals(out, expected);

    }

    @Test
    void containsTest() {
        try {
            assertTrue(db.contains("STUDENT","STUDENT_NAME","Mike"));
            assertFalse(db.contains("STUDENT","STUDENT_NAME","Dog"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testContains() {
        
    }

    @Disabled
    void createUserTable() {
        String createBookTable = "CREATE TABLE USER_ACCOUNT(" +
                                "accountID NUMBER(10) NOT NULL," +
                                "accountStatus CHAR(1) CHECK (accountStatus in ( 'T', 'F' )) NOT NULL," +
                                "PRIMARY KEY (accountID))";
        try {
            db.query(createBookTable);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Disabled
    void createBookTable() {
        String createBookTable = "CREATE TABLE BOOK(" +
                "accountID NUMBER(10) NOT NULL," +
                "accountStatus CHAR(1) CHECK (accountStatus in ( 'T', 'F' )) NOT NULL," +
                "PRIMARY KEY (accountID))";
        try {
            db.query(createBookTable);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void update() {
        try{

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
    }

    @Test
    void insert() {
    }

    @Test
    void commit() {
    }
}