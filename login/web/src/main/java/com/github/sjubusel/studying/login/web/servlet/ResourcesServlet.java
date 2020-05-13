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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@WebServlet(value = {"/resources"})
public class ResourcesServlet extends HttpServlet {
    private NewArticleService newArticleService = DefNewsArticleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsArticle> list = newArticleService.getNewsArticles();
        String languageSessionAttr = (String) req.getSession().getAttribute("language");
        String[] localeParts = languageSessionAttr.split("_");
        Locale locale = new Locale(localeParts[0], localeParts[1]);
        Map<String, NewsArticle> articles = new LinkedHashMap<>();
        for (NewsArticle article : list) {
            String localeDateTime = Util.getZonedDateTimeToString(article.getArticleDate(), locale);
            articles.put(localeDateTime, article);
        }
        req.setAttribute("articles", articles);
        Util.forwardToJsp("resources", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String heading = req.getParameter("heading");
        String bodyArticle = req.getParameter("bodyArticle");
        String author = req.getParameter("author");
        if (heading != null && bodyArticle != null) {
            newArticleService.saveArticle(new NewsArticle(heading, ZonedDateTime.now(),
                    bodyArticle, UUID.randomUUID().toString(), author));
        }
        Util.sendRedirect("resources", req, resp);
    }
}
