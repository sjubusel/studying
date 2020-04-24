package com.github.sjubusel.studying.login.impl;

import com.github.sjubusel.studying.login.NewsArticle;
import com.github.sjubusel.studying.login.NewsArticleDao;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DefNewsArticleDao implements NewsArticleDao {
    private List<NewsArticle> articles;

    private DefNewsArticleDao() {
        this.articles = new ArrayList<>();
        this.articles.add(new NewsArticle("TempHeader", ZonedDateTime.now(),
                "Text text text", UUID.randomUUID().toString()));
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
    public List<NewsArticle> getArticles() {
        return articles;
    }

    @Override
    public String save(NewsArticle article) {
        articles.add(article);
        return article.getNewsId();
    }
}
