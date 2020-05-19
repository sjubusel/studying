package com.github.sjubusel.studying.login.dao.impl;

import com.github.sjubusel.studying.login.dao.Connector;
import com.github.sjubusel.studying.login.dao.NewsArticleDao;
import com.github.sjubusel.studying.login.model.NewsArticle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefNewsArticleDaoTest {

    private NewsArticleDao dao = DefNewsArticleDao.getInstance();

    private Connector connector = DataBaseConnector.getInstance();

    @Test
    @DisplayName("get all articles from a database")
    void getArticles() {
        List<NewsArticle> expectedArticles = new ArrayList<>();
        expectedArticles.add
                (new NewsArticle("один", ZonedDateTime.now(), "один", "один", "секретный параметр Для"));
        expectedArticles.add
                (new NewsArticle("два", ZonedDateTime.now(), "два", "два", "два"));
        expectedArticles.add
                (new NewsArticle("три", ZonedDateTime.now(), "три", "три", "три"));
        expectedArticles.add
                (new NewsArticle("четыре", ZonedDateTime.now(), "четыре", "четыре", "четыре"));
        expectedArticles.add
                (new NewsArticle("пять", ZonedDateTime.now(), "пять", "пять", "пять"));

        List<NewsArticle> actualArticles = dao.getArticles();

        for (int i = 0; i < actualArticles.size() - 1; i++) {
            NewsArticle expectedArticle = expectedArticles.get(i);
            NewsArticle actualArticle = actualArticles.get(i);
            assertEquals(expectedArticle.getHeader(), actualArticle.getHeader());
            assertEquals(expectedArticle.getText(), actualArticle.getText());
            assertEquals(expectedArticle.getNewsId(), actualArticle.getNewsId());
            assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        }
    }

    @Test
    @DisplayName("verify whether saves and returns articleId correctly")
    void save_AndReturnId() {
        String elementToDelete = "saveAndDelete";
        NewsArticle articleToSave =
                new NewsArticle(elementToDelete, ZonedDateTime.now(), elementToDelete, elementToDelete, elementToDelete);

        String idOfSavedArticle = dao.save(articleToSave);

        Assertions.assertEquals(elementToDelete, idOfSavedArticle);
        restoreInitContentsOfDb__save_AndReturnId(elementToDelete);
    }

    private void restoreInitContentsOfDb__save_AndReturnId(String elementToDelete) {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM news_article WHERE news_id = ?");
            statement.setString(1, elementToDelete);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void deleteById() {
        String deletedId = "delete";
        String statusOfDeletedArticle;
        boolean deleted = dao.deleteById(deletedId);
        Assertions.assertTrue(deleted);
        statusOfDeletedArticle = getStatusOfDeletedArticleFromDB(deletedId);
        Assertions.assertEquals("deleted", statusOfDeletedArticle);
//        restoreInit_deleteById(deletedId);
    }

    private String getStatusOfDeletedArticleFromDB(String deletedId) {
        String statusOfDeletedArticle = null;
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT status FROM news_article WHERE news_id = ?");
            statement.setString(1, deletedId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                statusOfDeletedArticle = resultSet.getString("status");
            } else {
                fail("status is incorrect");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statusOfDeletedArticle;
    }

//    private void restoreInit_deleteById(String deletedId) {
//        try (Connection connection = connector.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement
//                    ("UPDATE news_article t SET t.status = NULL, t.delete_dateTime = NULL WHERE news_id = ?");
//            statement.setString(1, deletedId);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    void restoreById() {
        String deletedId = "delete";
        String statusOfDeletedArticle = getStatusOfDeletedArticleFromDB(deletedId);
        if (statusOfDeletedArticle != null && statusOfDeletedArticle.equals("deleted")) {
            boolean restored = dao.restoreById(deletedId);
            assertTrue(restored);
            assertNull(getStatusOfDeletedArticleFromDB(deletedId));
        } else {
            fail("Status of an article with DeletedId is incorrect");
        }
        restoreInitConfigAsBefore_restoreById(deletedId);
    }

    private void restoreInitConfigAsBefore_restoreById(String deletedId) {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE news_article t SET t.status = 'deleted' WHERE t.news_id = ?");
            statement.setString(1, deletedId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void fetchById() {
        String id = "один";
        String parameterToAssert = "секретный параметр Для";

        NewsArticle article = dao.fetchById(id);
        assertEquals(parameterToAssert, article.getAuthor());
    }

    @Test
    void saveEditedArticle() {
//        fail();
    }
}