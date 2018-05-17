package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentRepo implements GenericCRUD<Comment> {

    private static final Logger LOGGER = LogManager.getLogger(CommentRepo.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_COMMENT = "INSERT INTO comments (text, task_id, author_id) VALUES(?,?,?);";
    private static final String UPDATE_COMMENT = "UPDATE comments SET text=? WHERE comment_id =?;";
    private static final String DELETE_COMMENT = "DELETE FROM comments WHERE comment_id=?";
    private static final String READ_COMMENT = "SELECT * FROM comments WHERE comment_id=?;";
    private static final String READ_ALL_COMMENT = "SELECT * FROM comments;";

    private List<Comment> parsData(ResultSet resultSet) {
        List<Comment> comments = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Comment comment = new Comment(resultSet.getLong("comment_id"),
                        resultSet.getString("text"), resultSet.getLong("task_id"),
                        resultSet.getLong("author_id"), resultSet.getTimestamp("last_update").toLocalDateTime());
                comments.add(comment);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return comments;
    }

    @Override
    public Comment create(Comment comment) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(CREATE_COMMENT)) {
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setLong(2, comment.getTaskId());
            preparedStatement.setLong(3, comment.getAuthorId());
            preparedStatement.execute();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating Comment failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Comment read(Long id) {

        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(READ_COMMENT)) {
            preparedStatement.setLong(1, id);
            List<Comment> result = parsData(preparedStatement.executeQuery());
            if (result.size() != 1) throw new SQLException("Error with searching comment by id");
            return result.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Comment update(Comment comment) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(UPDATE_COMMENT)) {
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setLong(2, comment.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new SQLException("Comment have not being updated");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return comment;
    }

    @Override
    public void delete(Comment comment) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(DELETE_COMMENT)) {
            preparedStatement.setLong(1, comment.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new SQLException("Comment have not being deleted");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Comment> readAll() {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(READ_ALL_COMMENT)) {
            return parsData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }
}
