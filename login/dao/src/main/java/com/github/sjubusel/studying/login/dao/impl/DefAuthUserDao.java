package com.github.sjubusel.studying.login.dao.impl;

import com.github.sjubusel.studying.login.dao.AuthUserDao;
import com.github.sjubusel.studying.login.model.AuthUser;
import com.github.sjubusel.studying.login.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DefAuthUserDao implements AuthUserDao {
    private static final Logger logger = LoggerFactory.getLogger(DefAuthUserDao.class);

    private DataBaseConnector connector;

    public DefAuthUserDao() {
        this.connector = DataBaseConnector.getInstance();
        try (Connection connection = this.connector.getConnection(); Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS auth_user" +
                    "(" +
                    "    id       VARCHAR(255) UNIQUE    PRIMARY KEY NOT NULL," +
                    "    login    VARCHAR(255) UNIQUE                NOT NULL," +
                    "    password VARCHAR(255)                       NOT NULL," +
                    "    role     VARCHAR(255)                       NOT NULL" +
                    ")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            logger.error("Gone wrong while checking of a creation of the main SQL table \"{}\"", "auth_user", e);
            throw new RuntimeException(e);
        }
    }

    private static volatile AuthUserDao instance;

    public static AuthUserDao getInstance() {
        AuthUserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthUserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefAuthUserDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public AuthUser getByLogin(String login) {
        try (Connection connection = this.connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT login, password, role, id FROM auth_user as a WHERE login = ?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new AuthUser(resultSet.getString("login"),
                        resultSet.getString("password"),
                        Role.valueOf(resultSet.getString("role")),
                        resultSet.getString("id"));
            } else return null;
        } catch (SQLException e) {
            logger.error("Gone wrong while selecting user \"{}\" by login", login, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveAuthUser(AuthUser user) {
        try (Connection connection = this.connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO auth_user (id, login, password, role) VALUES(?,?,?,?)");
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole().toString());
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Gone wrong while saving user \"{}\"", user, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthUser getByUserId(String userId) {
        try (Connection connection = this.connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM auth_user WHERE id = ?");
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new AuthUser(resultSet.getString("login"),
                        resultSet.getString("password"),
                        Role.valueOf(resultSet.getString("role")),
                        resultSet.getString("id"));
            } else return null;
        } catch (SQLException e) {
            logger.error("Gone wrong while selecting user by userId  \"{}\"", userId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsLogin(String login) {
        try (Connection connection = this.connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT login FROM auth_user WHERE login = ?");
            statement.setString(1, login);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            logger.error("Gone wrong while verifying whether user \"{}\" exists", login, e);
            throw new RuntimeException(e);
        }
    }
}
