package com.github.sjubusel.studying.login.dao.impl;

import com.github.sjubusel.studying.login.dao.AuthUserDao;
import com.github.sjubusel.studying.login.model.AuthUser;
import com.github.sjubusel.studying.login.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class DefAuthUserDaoTest {

    AuthUserDao authUserDao = DefAuthUserDao.getInstance();

    private DataBaseConnector connector = DataBaseConnector.getInstance();

    @Test
    @DisplayName("get AuthUser by login, which is saved in db")
    void getByLoginWithCorrectLogin() {
        String login = "testUser";
        AuthUser user = authUserDao.getByLogin(login);
        Assertions.assertEquals("0", user.getUserId());
        Assertions.assertEquals("testUser", user.getLogin());
        Assertions.assertEquals("testUser", user.getPassword());
        Assertions.assertEquals("USER", user.getRole().toString());
    }

    @Test
    @DisplayName("get AuthUser by login, which is saved in db")
    void getByLoginWithoutCorrectLogin() {
        String login = "testUserWhichDoesntExist";
        AuthUser user = authUserDao.getByLogin(login);
        Assertions.assertNull(user);
    }

    @Test
    @DisplayName("save AuthUser with Login that does't exist")
    void saveAuthUserWithCorrectLogin() throws SQLException {
        String login = "testUserToDelete";
        int expectedRowsAdded = 1;
        int actualRowsAdded = authUserDao.saveAuthUser(new AuthUser(login, login, Role.USER, login));
        Assertions.assertEquals(expectedRowsAdded, actualRowsAdded);
        restoreInitState(login);
    }

    private void restoreInitState(String login) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM auth_user WHERE login = ?");
            statement.setString(1, login);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Test
    @DisplayName("save AuthUser with Login that already exists")
    void saveAuthUser() {

    }

    @Test
    @DisplayName("get AuthUer by Id")
    void getByUserId() {
    }

    @Test
    @DisplayName("checks whether an AuthUser with LOGIN-parameter exists")
    void containsLogin() {
    }
}