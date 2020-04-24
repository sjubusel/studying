package com.github.sjubusel.studying.login.impl;

import com.github.sjubusel.studying.login.AuthUser;
import com.github.sjubusel.studying.login.AuthUserDao;
import com.github.sjubusel.studying.login.AuthUserService;
import com.github.sjubusel.studying.login.Role;

import java.util.UUID;

public class DefAuthUserService implements AuthUserService {
    private AuthUserDao authUserDao = DefAuthUserDao.getInstance();

    private static volatile AuthUserService instance;

    public static AuthUserService getInstance() {
        AuthUserService localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthUserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefAuthUserService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public AuthUser login(String login, String password) {
        AuthUser user = authUserDao.getByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean verifyUserAuthenticity(String userId) {
        return authUserDao.getByUserId(userId) != null;
    }

    @Override
    public AuthUser register(String login, String password) {
        if (!authUserDao.containsLogin(login)) {
            authUserDao.saveAuthUser(new AuthUser(login, password, Role.USER, UUID.randomUUID().toString()));
            return authUserDao.getByLogin(login);
        } else {
            return null;
        }
    }
}
