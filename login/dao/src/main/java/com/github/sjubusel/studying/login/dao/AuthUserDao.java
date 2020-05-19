package com.github.sjubusel.studying.login.dao;

import com.github.sjubusel.studying.login.model.AuthUser;

public interface AuthUserDao {
    AuthUser getByLogin(String login);

    int saveAuthUser(AuthUser user);

    AuthUser getByUserId(String userId);

    boolean containsLogin(String login);
}
