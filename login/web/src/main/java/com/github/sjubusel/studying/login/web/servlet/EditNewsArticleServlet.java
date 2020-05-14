package com.github.sjubusel.studying.login.web.servlet;

import com.github.sjubusel.studying.login.model.AuthUser;
import com.github.sjubusel.studying.login.model.NewsArticle;
import com.github.sjubusel.studying.login.service.NewArticleService;
import com.github.sjubusel.studying.login.service.implementation.DefNewsArticleService;
import com.github.sjubusel.studying.login.web.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/editNewsArticle"})
public class EditNewsArticleServlet extends HttpServlet {
    private NewArticleService service = DefNewsArticleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Util.forwardToJsp("editNewsArticle", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String idToEdit = req.getParameter("idToEdit");
        NewsArticle article = service.fetchNewsArticleByIdIfHasRights(idToEdit, authUser);
        if (article != null) {
            req.setAttribute("articleToEdit", article);
        } else {
            req.setAttribute("editMessage", "You have no rights to edit this article");
        }
        Util.forwardToJsp("editNewsArticle", req, resp);
    }
}
