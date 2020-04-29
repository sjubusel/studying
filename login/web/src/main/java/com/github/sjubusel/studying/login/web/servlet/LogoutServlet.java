package com.github.sjubusel.studying.login.web.servlet;

import com.github.sjubusel.studying.login.web.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/logout"})
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("authUser");
        req.getSession().invalidate();
        resp.addCookie(new Cookie("logged", "0"));
        Util.sendRedirect("index", req, resp);
    }
}
