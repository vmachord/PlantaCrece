package com.pim.planta.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class SQLite {
    private static Connection connection;
    private static Statement statement;

    public static void connect() {
        try {
            String url = "jdbc:sqlite:./database.db"+"?busy_timeout=5000";   //ver
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            System.out.println("SQLite connected");
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void close() {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
            System.out.println("SQLite closed");
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    //----------------------------------- Hay que normalizar UML y poner las tablas bien
    public static void createContainer() {
        ResultSet resultSet = null;
        try {
            connect();

            String sql = "CREATE TABLE IF NOT EXISTS User (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL," +
                    "password TEXT NOT NULL," +
                    "estado TEXT NOT NULL)";
            statement.executeUpdate(sql);

            String sql2 = "CREATE TABLE IF NOT EXISTS Token (" +
                    "tokenGenerado TEXT PRIMARY KEY," +
                    "id_user INTEGER NOT NULL," +
                    "fecha TEXT NOT NULL," +
                    "hora TEXT NOT NULL," +
                    "estado TEXT NOT NULL" +
                    ")";

            statement.executeUpdate(sql2);

            String user = "SELECT * FROM User WHERE username = 'usuario1'";
            resultSet = statement.executeQuery(user);

            if (!resultSet.next()) {
                String insertSql1 = "INSERT INTO User (username, password, estado) VALUES ('usuario1', '9010e72389a80487d473017425c6ec7951068abed82a4df32459c91f0e45d2ea','habilitado')";
                statement.executeUpdate(insertSql1);
            }

            String user2 = "SELECT * FROM User WHERE username = 'usuario2'";
            resultSet = statement.executeQuery(user2);

            if (!resultSet.next()) {
                String insertSql2 = "INSERT INTO User (username, password, estado) VALUES ('usuario2', '998aab960cd9f809b09dd12eade1de4a2985f62335d8ff45a775a598ead09b06','habilitado')";
                statement.executeUpdate(insertSql2);
            }

            System.out.println("Rows inserted");

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            close();
        }
    }

    //----------------------------------------------------------

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }
}





























