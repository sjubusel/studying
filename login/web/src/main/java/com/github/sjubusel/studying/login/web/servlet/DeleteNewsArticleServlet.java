package com.github.sjubusel.studying.login.web.servlet;

import com.github.sjubusel.studying.login.model.AuthUser;
import com.github.sjubusel.studying.login.service.NewArticleService;
import com.github.sjubusel.studying.login.service.implementation.DefNewsArticleService;
import com.github.sjubusel.studying.login.web.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/deleteNewsArticle"})
public class DeleteNewsArticleServlet extends HttpServlet {
    private NewArticleService service = DefNewsArticleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Util.sendRedirect("resources", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idToDelete = req.getParameter("idToDelete");
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        if (service.deleteArticleByIdIfHasRights(idToDelete, authUser)) {
            req.getSession().setAttribute("deleteMessage", String.format("NewsArticle №%s is successfully deleted", idToDelete));
            req.getSession().setAttribute("deletedId", idToDelete);
        } else {
            req.getSession().setAttribute("deleteMessage", "You have no rights to delete articles");
        }
        Util.sendRedirect("resources", req, resp);
    }
}
