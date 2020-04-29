package com.github.sjubusel.studying.login.web.servlet;

import com.github.sjubusel.studying.login.model.AuthUser;
import com.github.sjubusel.studying.login.service.AuthUserService;
import com.github.sjubusel.studying.login.web.Util;
import com.github.sjubusel.studying.login.service.implementation.DefAuthUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/login"})
public class LoginServlet extends HttpServlet {
    private AuthUserService authUserService = DefAuthUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = Util.getCookieIfExists("logged", req.getCookies());
        if (cookie == null || !Util.ifCookieIsValid(cookie)) {
            Util.forwardToJsp("login", req, resp);
        } else {
            Util.sendRedirect("resources", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AuthUser user = authUserService.login(login, password);
        if (user != null) {
            req.getSession().setAttribute("authUser", user);
            resp.addCookie(Util.createLongTimeCookie(user.getUserId()));
            Util.sendRedirect("resources", req, resp);
        } else {
            req.setAttribute("error", "login or password invalid");
            Util.forwardToJsp("login", req, resp);
        }
    }
}
