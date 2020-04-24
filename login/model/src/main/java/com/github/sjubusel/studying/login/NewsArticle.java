package com.github.sjubusel.studying.login;

import java.time.ZonedDateTime;

public class NewsArticle {
    String header;
    ZonedDateTime articleDate;
    String text;
    String newsId;

    public NewsArticle(String header, ZonedDateTime articleDate, String text, String newsId) {
        this.header = header;
        this.articleDate = articleDate;
        this.text = text;
        this.newsId = newsId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public ZonedDateTime getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(ZonedDateTime articleDate) {
        this.articleDate = articleDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "header='" + header + '\'' +
                ", articleDate=" + articleDate +
                ", text='" + text + '\'' +
                ", newsId='" + newsId + '\'' +
                '}';
    }
}
