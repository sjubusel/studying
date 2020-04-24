package com.github.sjubusel.studying.login.servlet;

import com.github.sjubusel.studying.login.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/logout"})
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("authUser");
        req.getSession().invalidate();
        resp.addCookie(new Cookie("logged", "0"));
        Util.sendRedirect("login.jsp", req, resp);
    }
}
