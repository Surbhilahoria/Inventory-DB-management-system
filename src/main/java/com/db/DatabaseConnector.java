package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    public static void createNewDatabaseWithTable(String fileName) {
        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("A new database has been created with the name " + fileName);
                createTable(conn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS inventory (\n"
                + "    id INTEGER PRIMARY KEY,\n"
                + "    name TEXT NOT NULL,\n"
                + "    price REAL NOT NULL,\n"
                + "    quantity INTEGER NOT NULL\n"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("The 'inventory' table has been created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createNewDatabaseWithTable("database/inventory.db");
    }
}