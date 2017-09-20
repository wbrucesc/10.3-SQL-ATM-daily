package com.will.helpers;



import com.will.Models.AcctChanges;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManager {
    private Connection conn;
    private Statement statement;

    public DatabaseManager(Connection conn) throws SQLException {
        this.conn = conn;
        this.statement = conn.createStatement();
    }

    public void dropHistoryTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS history");
    }

    public void createHistoryTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE history (id INTEGER PRIMARY KEY, name STRING, transAmount DOUBLE);");
    }

    public void insertIntoHistoryTable(String name, double transAmount) throws SQLException {
       String sqlString = String.format("INSERT INTO history (name, transAmount) VALUES ('%s', %f);", name, transAmount);
       statement.executeUpdate(sqlString);
    }

    public void insertIntoHistoryTable(AcctChanges myChange) throws SQLException {
        insertIntoHistoryTable(myChange.getName(), myChange.getTransAmount());
    }

    public ArrayList<AcctChanges> getHistory() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM history");

        ArrayList<AcctChanges> retVal = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double transAmount = rs.getDouble("transAmount");

            AcctChanges tempChange = new AcctChanges(id, name, transAmount);
            retVal.add(tempChange);
        }
        return retVal;
    }
}
