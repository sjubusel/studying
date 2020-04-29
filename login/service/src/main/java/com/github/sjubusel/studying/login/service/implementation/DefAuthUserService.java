package com.github.sjubusel.studying.login.service.implementation;

import com.github.sjubusel.studying.login.model.AuthUser;
import com.github.sjubusel.studying.login.dao.AuthUserDao;
import com.github.sjubusel.studying.login.service.AuthUserService;
import com.github.sjubusel.studying.login.model.Role;
import com.github.sjubusel.studying.login.dao.impl.DefAuthUserDao;

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
    public AuthUser login(String userId) {
        authUserDao.getByUserId(userId);
        return null;
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
