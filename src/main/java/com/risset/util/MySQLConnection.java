package com.risset.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/cvGenerator_db";
    private static final String USERNAME = "haris";
    private static final String PASSWORD = "180900";

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
        Connection connection =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }

}
