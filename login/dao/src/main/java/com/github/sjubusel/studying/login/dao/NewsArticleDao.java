package com.github.sjubusel.studying.login.dao;

import com.github.sjubusel.studying.login.model.NewsArticle;

import java.util.List;

public interface NewsArticleDao {
    List<NewsArticle> getArticles();

    String save(NewsArticle article);

    boolean deleteById(String idToDelete);

    boolean restoreById(String idToRestore);

    NewsArticle fetchById(String idToFetch);

    boolean saveEditedArticle(NewsArticle article);
}
