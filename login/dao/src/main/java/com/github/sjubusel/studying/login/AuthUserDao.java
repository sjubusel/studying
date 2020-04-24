package com.github.sjubusel.studying.login;

public interface AuthUserDao {
    AuthUser getByLogin(String login);

    void saveAuthUser(AuthUser user);

    AuthUser getByUserId(String userId);

    boolean containsLogin(String login);
}
