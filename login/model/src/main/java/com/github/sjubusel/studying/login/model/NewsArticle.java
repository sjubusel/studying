package com.github.sjubusel.studying.login.model;

import java.time.ZonedDateTime;

public class NewsArticle {
    String header;
    ZonedDateTime articleDate;
    String text;
    String newsId;
    String author;

    public NewsArticle(String header, ZonedDateTime articleDate, String text, String newsId, String author) {
        this.header = header;
        this.articleDate = articleDate;
        this.text = text;
        this.newsId = newsId;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
