package com.github.sjubusel.studying.login.dao.impl;

import com.github.sjubusel.studying.login.model.NewsArticle;
import com.github.sjubusel.studying.login.dao.NewsArticleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class DefNewsArticleDao implements NewsArticleDao {
    private static final Logger logger = LoggerFactory.getLogger(DefNewsArticleDao.class);
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
                    "    author   VARCHAR(255) NOT NULL," +
                    "    status   VARCHAR(255)  NULL," +
                    "    delete_dateTime   VARCHAR(255)  NULL" +
                    ")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            logger.error("Gone wrong while checking of a creation of the main SQL table \"{}\"", "news_article", e);
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
    public List<NewsArticle> getArticles() {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM news_article WHERE status IS NULL ORDER BY datetime desc");
            ResultSet resultSet = statement.executeQuery();
            List<NewsArticle> list = new LinkedList<>();
            while (resultSet.next()) {
                String header = resultSet.getString("header");
                String text = resultSet.getString("text");
                String news_id = resultSet.getString("news_id");
                String author = resultSet.getString("author");
                ZonedDateTime dateTime = ZonedDateTime.of
                        (resultSet.getObject("datetime", LocalDateTime.class), ZoneId.systemDefault());
                list.add(new NewsArticle(header, dateTime, text, news_id, author));
            }
            return list;
        } catch (SQLException e) {
            logger.error("Gone wrong while retrieving and converting data from \"news_article\"", e);
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
            logger.error("Gone wrong while saving article \"{}\" to \"news_article\"-table", article, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(String idToDelete) {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE test_jdbc.news_article t SET t.status = 'deleted', t.delete_datetime = ? WHERE t.news_id LIKE ?");
            statement.setObject(1, ZonedDateTime.now().toLocalDateTime());
            statement.setString(2, idToDelete);
            int i = statement.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            logger.error("Gone wrong while deleting an article \"{}\" from \"news_article table\"", idToDelete, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean restoreById(String idToRestore) {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE test_jdbc.news_article t SET t.status = null WHERE t.news_id LIKE ?");
            statement.setString(1, idToRestore);
            int i = statement.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            logger.error("Gone wrong while restoring an article \"{}\" from \"news_article table\"", idToRestore, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public NewsArticle fetchById(String idToFetch) {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM news_article WHERE news_id = ?");
            statement.setString(1, idToFetch);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String header = resultSet.getString("header");
                String text = resultSet.getString("text");
                String news_id = resultSet.getString("news_id");
                String author = resultSet.getString("author");
                ZonedDateTime dateTime = ZonedDateTime.of
                        (resultSet.getObject("datetime", LocalDateTime.class), ZoneId.systemDefault());
                return new NewsArticle(header, dateTime, text, news_id, author);
            }
            return null;
        } catch (SQLException e) {
            logger.error("Gone wrong while fetching an article \"{}\" from \"news_article table\"", idToFetch, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveEditedArticle(NewsArticle article) {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE test_jdbc.news_article t " +
                            "SET t.header = ?, t.text = ?, t.editor = ?, t.edit_datetime = ? " +
                            "WHERE t.news_id = ?");
            statement.setString(1, article.getHeader());
            statement.setString(2, article.getText());
            statement.setString(3, article.getAuthor());
            statement.setObject(4, article.getArticleDate().toLocalDateTime());
            statement.setString(5, article.getNewsId());
            int i = statement.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            logger.error("Gone wrong while editing an article \"{}\" from \"news_article table\"", article.getNewsId(), e);
            throw new RuntimeException(e);
        }
    }
}
