package com.github.sjubusel.studying.login.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface Connector {
    Connection getConnection() throws SQLException;
}
