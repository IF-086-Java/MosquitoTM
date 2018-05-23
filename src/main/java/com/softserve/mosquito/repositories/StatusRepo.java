package com.softserve.mosquito.repositories;


import com.softserve.mosquito.enitities.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatusRepo implements GenericCRUD<Status> {
    private static final Logger LOGGER = LogManager.getLogger(StatusRepo.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_STATUS =
            "INSERT INTO statuses (title) VALUE (?);";
    private static final String UPDATE_STATUS =
            "UPDATE statuses SET title=? WHERE status_id=?;";
    private static final String DELETE_STATUS =
            "DELETE FROM statuses WHERE status_id=?;";

    private static final String READ_STATUS =
            "SELECT * FROM statuses WHERE status_id=?;";
    private static final String READ_ALL_STATUSES =
            "SELECT * FROM statuses;";

    private List<Status> parseData(ResultSet resultSet) {
        List<Status> statuses = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Status status = new Status();
                status.setId(resultSet.getByte("status_id"));
                status.setTitle(resultSet.getString("title"));
                statuses.add(status);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return statuses;
    }

    @Override
    public Status create(Status status) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STATUS)) {
            preparedStatement.setString(1, status.getTitle());
            preparedStatement.execute();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return read(generatedKeys.getLong(1));
                } else throw new SQLException("Creating status failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Status read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_STATUS)) {
            preparedStatement.setLong(1, id);
            List<Status> result = parseData(preparedStatement.executeQuery());
            if (result.size() != 1) {
                throw new SQLException("Error with searching status by id");
            }
            return result.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Status update(Status status) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS)) {
            preparedStatement.setString(1, status.getTitle());
            preparedStatement.setByte(2, status.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new SQLException("Statuses have not being updated");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return status;
    }


    @Override
    public void delete(Status status) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STATUS)) {
            preparedStatement.setByte(1, status.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException("Status have not being deleted");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Status> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_STATUSES)) {
            return parseData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }
}
