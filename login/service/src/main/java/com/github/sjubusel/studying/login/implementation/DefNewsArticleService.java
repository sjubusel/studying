package com.github.sjubusel.studying.login.implementation;

import com.github.sjubusel.studying.login.NewArticleService;
import com.github.sjubusel.studying.login.NewsArticle;
import com.github.sjubusel.studying.login.dao.NewsArticleDao;
import com.github.sjubusel.studying.login.dao.impl.DefNewsArticleDao;

import java.time.ZonedDateTime;
import java.util.Map;

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
    public Map<ZonedDateTime, NewsArticle> getNewsArticles() {
        return newsArticleDao.getArticles();
    }

    @Override
    public String saveArticle(NewsArticle article) {
        return newsArticleDao.save(article);
    }
}
