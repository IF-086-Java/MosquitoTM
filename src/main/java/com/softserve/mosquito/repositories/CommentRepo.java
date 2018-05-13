package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Comment;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepo implements GenericCRUD<Comment> {

    private static final Logger LOGGER = Logger.getLogger(CommentRepo.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    @Override
    public Comment create(Comment comment) {
        String sqlQuery = "INSERT INTO comments (comment, user_id, task_id) VALUES(?,?,?); ";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, comment.getText());
            statement.setLong(2, comment.getAuthorId());
            statement.setLong(3, comment.getTaskId());

            int result = statement.executeUpdate();
            if (result == 0) {
                LOGGER.error("Creating comment failed! No rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    LOGGER.error("Creating comment failed! No ID obtained.");
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return null;
    }

    @Override
    public Comment read(Long id) {
        String sqlQuery = "SELECT * "
                + " FROM comments WHERE comments.comment_id=" + id + ";";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = statement.executeQuery()) {

            return new Comment(resultSet.getLong(1), resultSet.getLong(2), resultSet.getLong(3),
                    resultSet.getString(4), resultSet.getDate(5));

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return null;
    }

    @Override
    public Comment update(Comment comment) {
        String sqlQuery = "UPDATE comments SET comment=?, created_date=? WHERE comment_id =" + comment.getId() + ";";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, comment.getText());
            statement.setDate(2, (Date) comment.getDate());

            int result = statement.executeUpdate();
            if (result > 0)
                return read(comment.getId());

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Comment comment) {
        String sqlQuery = "DELETE FROM comments WHERE comment_id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setLong(1, comment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Comment> readAll() {
        List<Comment> comments = new ArrayList<>();
        String sqlQuery = "SELECT * FROM comments";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next())
                comments.add(new Comment(resultSet.getLong(1), resultSet.getLong(2),
                        resultSet.getLong(3), resultSet.getString(4), resultSet.getDate(5)));


        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return comments;
    }
}
