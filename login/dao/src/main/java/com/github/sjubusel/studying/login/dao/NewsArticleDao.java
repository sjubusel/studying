package com.github.sjubusel.studying.login.dao;

import com.github.sjubusel.studying.login.model.NewsArticle;

import java.time.ZonedDateTime;
import java.util.Map;

public interface NewsArticleDao {
    Map<ZonedDateTime, NewsArticle> getArticles();

    String save(NewsArticle article);
}
