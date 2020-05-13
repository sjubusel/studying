package com.github.sjubusel.studying.login.service.implementation;

import com.github.sjubusel.studying.login.dao.NewsArticleDao;
import com.github.sjubusel.studying.login.dao.impl.DefNewsArticleDao;
import com.github.sjubusel.studying.login.model.AuthUser;
import com.github.sjubusel.studying.login.model.NewsArticle;
import com.github.sjubusel.studying.login.service.NewArticleService;

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

    @Override
    public boolean deleteArticleByIdIfHasRights(String idToDelete, AuthUser authUser) {
        if (authUser.getRole().toString().equals("ADMIN")) {
            return newsArticleDao.deleteById(idToDelete);
        }
        return false;
    }
}
