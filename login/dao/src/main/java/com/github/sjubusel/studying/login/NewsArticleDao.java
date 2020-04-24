package com.github.sjubusel.studying.login;

import java.util.List;

public interface NewsArticleDao {
    List<NewsArticle> getArticles();

    String save(NewsArticle article);
}
