package com.github.sjubusel.studying.login;

import java.time.ZonedDateTime;
import java.util.Map;

public interface NewArticleService {
    Map<ZonedDateTime, NewsArticle> getNewsArticles();

    String saveArticle(NewsArticle article);
}
