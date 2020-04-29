package com.github.sjubusel.studying.login.dao;

import com.github.sjubusel.studying.login.AuthUser;

public interface AuthUserDao {
    AuthUser getByLogin(String login);

    void saveAuthUser(AuthUser user);

    AuthUser getByUserId(String userId);

    boolean containsLogin(String login);
}
