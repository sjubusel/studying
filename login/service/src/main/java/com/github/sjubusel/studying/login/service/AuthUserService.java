package com.github.sjubusel.studying.login.service;

import com.github.sjubusel.studying.login.model.AuthUser;

public interface AuthUserService {
    AuthUser login(String login, String password);

    AuthUser login(String userId);

    boolean verifyUserAuthenticity(String userId);

    AuthUser register(String login, String password);
}
