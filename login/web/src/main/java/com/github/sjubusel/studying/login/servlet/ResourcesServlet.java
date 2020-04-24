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
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet(value = {"/resources"})
public class ResourcesServlet extends HttpServlet {
    private NewArticleService newArticleService = DefNewsArticleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<ZonedDateTime, NewsArticle> newsArticles = newArticleService.getNewsArticles();
        Map<String, NewsArticle> articles = new LinkedHashMap<>();
        for (Map.Entry<ZonedDateTime, NewsArticle> entry : newsArticles.entrySet()) {
            ZonedDateTime zonedDateTime = entry.getKey();
            String localeDateTime = Util.getZonedDateTimeToString(zonedDateTime, req.getLocale());
            articles.put(localeDateTime, entry.getValue());
        }
        req.setAttribute("articles", articles);
        Util.forwardToJsp("resources", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String heading = req.getParameter("heading");
        String bodyArticle = req.getParameter("bodyArticle");
        String author = req.getParameter("author");
        newArticleService.saveArticle(new NewsArticle(heading, ZonedDateTime.now(),
                bodyArticle, UUID.randomUUID().toString(), author));
        Util.sendRedirect("resources", req, resp);
    }
}
