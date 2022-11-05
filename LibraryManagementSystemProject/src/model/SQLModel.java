package model;

import java.sql.SQLException;

public interface SQLModel {
    public SQLModel pushToDatabase () throws SQLException;
    public SQLModel pullFromDatabase () throws SQLException;

    public void deleteFromDatabase() throws SQLException;
}
