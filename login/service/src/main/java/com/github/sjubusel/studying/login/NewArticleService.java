package com.github.sjubusel.studying.login;

import com.github.sjubusel.studying.login.model.NewsArticle;

import java.time.ZonedDateTime;
import java.util.Map;

public interface NewArticleService {
    Map<ZonedDateTime, NewsArticle> getNewsArticles();

    String saveArticle(NewsArticle article);
}
