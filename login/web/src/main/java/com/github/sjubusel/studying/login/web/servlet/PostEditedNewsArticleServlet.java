package com.github.sjubusel.studying.login.web.servlet;

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
import java.time.ZonedDateTime;

@WebServlet(value = {"/postEditedNewsArticle"})
public class PostEditedNewsArticleServlet extends HttpServlet {
    private NewArticleService service = DefNewsArticleService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String newHeading = req.getParameter("newHeading");
        String newText = req.getParameter("newText");
        String newAuthor = req.getParameter("newAuthor");
        String id = req.getParameter("idToEdit");
        NewsArticle article = new NewsArticle(newHeading, ZonedDateTime.now(), newText, id, newAuthor);
        if (service.saveEditedArticle(article)) {
            req.getSession().setAttribute("repostedMessage", String.format("NewsArticle №%s is successfully edited", id));
//            req.getSession().setAttribute("repostedId", id);
        } else {
            req.getSession().setAttribute("repostedMessage", String.format("NewsArticle №%s is not reposted", id));
        }
        Util.sendRedirect("editNewsArticle", req, resp);
    }
}
