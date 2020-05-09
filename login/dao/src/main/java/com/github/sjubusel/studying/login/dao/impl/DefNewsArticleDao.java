package com.github.sjubusel.studying.login.dao.impl;

import com.github.sjubusel.studying.login.model.NewsArticle;
import com.github.sjubusel.studying.login.dao.NewsArticleDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class DefNewsArticleDao implements NewsArticleDao {
    private DataBaseConnector connector;

    private DefNewsArticleDao() {
        this.connector = DataBaseConnector.getInstance();
        try (Connection connection = this.connector.getConnection(); Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS news_article" +
                    "(" +
                    "    header   VARCHAR(255) NOT NULL," +
                    "    datetime LONGTEXT     NOT NULL," +
                    "    text     BLOB         NOT NULL," +
                    "    news_id  VARCHAR(255) NOT NULL," +
                    "    author   VARCHAR(255) NOT NULL" +
                    ")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static volatile NewsArticleDao instance;

    public static NewsArticleDao getInstance() {
        NewsArticleDao localInstance = instance;
        if (localInstance == null) {
            synchronized (NewsArticleDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefNewsArticleDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Map<ZonedDateTime, NewsArticle> getArticles() {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM news_article");
            ResultSet resultSet = statement.executeQuery();
            HashMap<ZonedDateTime, NewsArticle> map = new HashMap<>();
            while (resultSet.next()) {
                String header = resultSet.getString("header");
                String text = resultSet.getString("text");
                String news_id = resultSet.getString("news_id");
                String author = resultSet.getString("author");
                ZonedDateTime dateTime = ZonedDateTime.of
                        (resultSet.getObject("datetime", LocalDateTime.class), ZoneId.systemDefault());
                map.put(dateTime, new NewsArticle(header, dateTime, text, news_id, author));
            }
            return map;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String save(NewsArticle article) {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO news_article " +
                    "(header, datetime, text, news_id, author) VALUES" +
                    "(?,?,?,?,?)");
            statement.setString(1, article.getHeader());
            statement.setObject(2, article.getArticleDate().toLocalDateTime());
            statement.setString(3, article.getText());
            statement.setString(4, article.getNewsId());
            statement.setString(5, article.getAuthor());
            statement.executeUpdate();
            return article.getNewsId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
