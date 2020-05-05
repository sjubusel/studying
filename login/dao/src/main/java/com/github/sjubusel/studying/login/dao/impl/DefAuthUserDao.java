package com.github.sjubusel.studying.login.dao.impl;

import com.github.sjubusel.studying.login.model.AuthUser;
import com.github.sjubusel.studying.login.dao.AuthUserDao;
import com.github.sjubusel.studying.login.model.Role;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DefAuthUserDao implements AuthUserDao {
    private Map<String, AuthUser> authUserMap;

    public DefAuthUserDao() {
        this.authUserMap = new HashMap<>();
        this.authUserMap.putIfAbsent("admin", new AuthUser("admin", "admin",
                Role.ADMIN, UUID.randomUUID().toString()));
        this.authUserMap.putIfAbsent("user", new AuthUser("user", "user",
                Role.USER, UUID.randomUUID().toString()));
    }

    private static volatile AuthUserDao instance;

    public static AuthUserDao getInstance() {
        AuthUserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthUserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefAuthUserDao();
                }
            }
        }
        return localInstance;
    }

    @Override

    public AuthUser getByLogin(String login) {
        return authUserMap.get(login);
    }

    @Override
    public void saveAuthUser(AuthUser user) {
        authUserMap.putIfAbsent(user.getLogin(), user);
    }

    @Override
    public AuthUser getByUserId(String userId) {
        Collection<AuthUser> authUsers = authUserMap.values();
        for (AuthUser authUser : authUsers) {
            if (authUser.getUserId().equals(userId)) {
                return authUser;
            }
        }
        return null;
    }

    @Override
    public boolean containsLogin(String login) {
        return authUserMap.containsKey(login);
    }
}