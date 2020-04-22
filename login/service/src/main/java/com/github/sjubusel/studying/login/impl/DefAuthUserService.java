package com.github.sjubusel.studying.login.impl;

import com.github.sjubusel.studying.login.AuthUser;
import com.github.sjubusel.studying.login.AuthUserDao;
import com.github.sjubusel.studying.login.AuthUserService;

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


}
