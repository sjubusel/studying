package com.github.sjubusel.studying.login.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class DataBaseConnectorTest {
    DataBaseConnector connector = DataBaseConnector.getInstance();

    @Test
    void testGetConnection() throws SQLException {
        Connection connection = connector.getConnection();
        Assertions.assertTrue(connection.isValid(2000));
        connection.close();
    }
}