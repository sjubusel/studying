package com.github.sjubusel.studying.login;

import java.util.List;

public interface NewArticleService {
    List<NewsArticle> getNewsArticles();

    String saveArticle(NewsArticle article);
}
