package com.github.sjubusel.studying.login.dao.impl;

import com.github.sjubusel.studying.login.dao.Connector;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataBaseConnector implements Connector {

    private static volatile DataBaseConnector instance;

    private final ComboPooledDataSource pool;

    private DataBaseConnector() {
        loadDatabaseDriver();
        pool = new ComboPooledDataSource();
        getAndSetDataBaseCredentials();
        configurePoolSettings();
    }

    private static void loadDatabaseDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAndSetDataBaseCredentials() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String url = resourceBundle.getString("url");
        String login = resourceBundle.getString("login");
        String password = resourceBundle.getString("password");
        pool.setJdbcUrl(url);
        pool.setUser(login);
        pool.setPassword(password);
    }

    private void configurePoolSettings() {
        pool.setMinPoolSize(5);
        pool.setAcquireIncrement(5);
        pool.setMaxPoolSize(10);
        pool.setMaxStatements(100);
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
        return this.pool.getConnection();
    }
}
