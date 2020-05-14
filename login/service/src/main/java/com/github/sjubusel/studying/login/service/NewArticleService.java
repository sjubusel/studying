package com.github.sjubusel.studying.login.service;

import com.github.sjubusel.studying.login.model.AuthUser;
import com.github.sjubusel.studying.login.model.NewsArticle;

import java.util.List;

public interface NewArticleService {
    List<NewsArticle> getNewsArticles();

    String saveArticle(NewsArticle article);

    boolean deleteArticleByIdIfHasRights(String idToDelete, AuthUser authUser);

    boolean restoreArticleById(String idToRestore);

    NewsArticle fetchNewsArticleByIdIfHasRights(String idToFetch, AuthUser authUser);

    boolean saveEditedArticle(NewsArticle article);
}
