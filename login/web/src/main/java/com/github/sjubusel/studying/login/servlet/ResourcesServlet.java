package com.github.sjubusel.studying.login.servlet;

import com.github.sjubusel.studying.login.NewArticleService;
import com.github.sjubusel.studying.login.NewsArticle;
import com.github.sjubusel.studying.login.Util;
import com.github.sjubusel.studying.login.impl.DefNewsArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = {"/resources"})
public class ResourcesServlet extends HttpServlet {
    private NewArticleService newArticleService = DefNewsArticleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsArticle> newsArticles = newArticleService.getNewsArticles();
        req.setAttribute("articles", newsArticles);
        Util.forwardToJsp("resources", req, resp);
    }
}
