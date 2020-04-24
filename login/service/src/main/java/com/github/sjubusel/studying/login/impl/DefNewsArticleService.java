package com.github.sjubusel.studying.login.impl;

import com.github.sjubusel.studying.login.NewArticleService;
import com.github.sjubusel.studying.login.NewsArticle;
import com.github.sjubusel.studying.login.NewsArticleDao;

import java.util.List;

public class DefNewsArticleService implements NewArticleService {
    private NewsArticleDao newsArticleDao = DefNewsArticleDao.getInstance();

    private static volatile NewArticleService instance;

    private DefNewsArticleService() {
    }

    public static NewArticleService getInstance() {
        NewArticleService localInstance = instance;
        if (localInstance == null) {
            synchronized (NewArticleService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefNewsArticleService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<NewsArticle> getNewsArticles() {
        return newsArticleDao.getArticles();
    }

    @Override
    public String saveArticle(NewsArticle article) {
        return newsArticleDao.save(article);
    }
}
