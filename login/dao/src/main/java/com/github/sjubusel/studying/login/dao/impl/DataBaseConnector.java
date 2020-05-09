package com.github.sjubusel.studying.login.dao.impl;

import com.github.sjubusel.studying.login.dao.Connector;

import java.sql.*;
import java.util.ResourceBundle;

public class DataBaseConnector implements Connector {

    private static volatile DataBaseConnector instance;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    private DataBaseConnector() {
        loadDatabaseDriver();
    }

    private static void loadDatabaseDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataBaseConnector getInstance() {
        DataBaseConnector localInstance = instance;
        if (localInstance == null) {
            synchronized (DataBaseConnector.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DataBaseConnector();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = resourceBundle.getString("url");
        String login = resourceBundle.getString("login");
        String password = resourceBundle.getString("password");
        return DriverManager.getConnection(url, login, password);
    }
}
