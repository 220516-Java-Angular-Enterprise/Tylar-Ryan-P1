package com.revature.reimbursements.util.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //maintains list of values in which key and value are Strings
    private Properties props = new Properties();

    private ConnectionFactory() {
        try {

            props.load(new FileReader("db.properties"));
//            props.load(new FileReader("C:/Users/ryanj/OneDrive/Documents/WozU/Projects/Revature/Tylar-Ryan-P1/src/main/resources/db.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {

        //DriverManager manages the Java Database Connectivity drivers
        Connection conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));

        if (conn == null) {
            throw new RuntimeException("Could not establish connection with the database!");
        }

        return conn;
    }
}