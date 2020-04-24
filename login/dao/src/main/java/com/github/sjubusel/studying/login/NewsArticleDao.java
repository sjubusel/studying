package com.github.sjubusel.studying.login;

import java.time.ZonedDateTime;
import java.util.Map;

public interface NewsArticleDao {
    Map<ZonedDateTime, NewsArticle> getArticles();

    String save(NewsArticle article);
}
