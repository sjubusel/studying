package com.github.sjubusel.studying.login;

public interface AuthUserDao {
    AuthUser getByLogin(String login);

    void saveAuthUser(AuthUser user);

    boolean containsThisUserId(String userId);

    boolean containsLogin(String login);
}
