package com.github.sjubusel.studying.login.model;

public class AuthUser {
    private String login;
    private String password;
    private Role role;
    private String userId;

    public AuthUser(String login, String password, Role role, String userId) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", userId='" + userId + '\'' +
                '}';
    }
}
