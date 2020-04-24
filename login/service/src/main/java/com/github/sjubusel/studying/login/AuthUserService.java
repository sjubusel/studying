package com.github.sjubusel.studying.login;

public interface AuthUserService {
    AuthUser login(String login, String password);

    boolean verifyUserAuthenticity(String userId);

    AuthUser register(String login, String password);
}
