package com.github.sjubusel.studying.login.dao.impl;

import com.github.sjubusel.studying.login.model.NewsArticle;
import com.github.sjubusel.studying.login.dao.NewsArticleDao;

import java.time.ZonedDateTime;
import java.util.*;

public class DefNewsArticleDao implements NewsArticleDao {
    private Map<ZonedDateTime, NewsArticle> articles;

    private DefNewsArticleDao() {
        this.articles = new LinkedHashMap<>();
        ZonedDateTime dateTime = ZonedDateTime.now();
        this.articles.put(dateTime, new NewsArticle("TempHeader", dateTime,
                "Text text text", UUID.randomUUID().toString(), "CREATOR"));
    }

    private static volatile NewsArticleDao instance;

    public static NewsArticleDao getInstance() {
        NewsArticleDao localInstance = instance;
        if (localInstance == null) {
            synchronized (NewsArticleDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefNewsArticleDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Map<ZonedDateTime, NewsArticle> getArticles() {
        return articles;
    }

    @Override
    public String save(NewsArticle article) {
        articles.put(article.getArticleDate(), article);
        return article.getNewsId();
    }
}
