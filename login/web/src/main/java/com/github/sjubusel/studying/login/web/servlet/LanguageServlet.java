package com.github.sjubusel.studying.login.web.servlet;

import com.github.sjubusel.studying.login.web.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = {"/language"})
public class LanguageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lang = req.getParameter("lang");
        HttpSession session = req.getSession();
        if (lang.equals("english")) {
            session.setAttribute("language", "en_US");
        } else {
            session.setAttribute("language", "ru_RU");
        }
        Util.sendRedirect("index.jsp", req, resp);
    }
}
