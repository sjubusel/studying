package com.github.sjubusel.studying.login.servlet;

import com.github.sjubusel.studying.login.model.AuthUser;
import com.github.sjubusel.studying.login.service.AuthUserService;
import com.github.sjubusel.studying.login.Util;
import com.github.sjubusel.studying.login.service.implementation.DefAuthUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/register"})
public class RegisterServlet extends HttpServlet {
    private AuthUserService authUserService = DefAuthUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = Util.getCookieIfExists("logged", req.getCookies());
        if (cookie == null || !Util.ifCookieIsValid(cookie)) {
            Util.forwardToJsp("register", req, resp);
        } else {
            Util.sendRedirect("resources", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthUser user = authUserService.register(login, password);
        if (user != null) {
            req.getSession().setAttribute("authUser", user);
            resp.addCookie(Util.createLongTimeCookie(user.getUserId()));
            Util.sendRedirect("resources", req, resp);
        } else {
            req.setAttribute("error", "login is already used by another user");
            Util.forwardToJsp("register", req, resp);
        }
    }
}
