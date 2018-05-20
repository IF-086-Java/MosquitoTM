package com.softserve.mosquito.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softserve.entities.Priority;

public class PriorityRepo implements GenericCRUD<Priority> {
    private static final Logger LOGGER = LogManager.getLogger(PriorityRepo.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_PRIORITY =
            "INSERT INTO priorities (title) VALUE (?);";
    private static final String UPDATE_PRIORITY =
            "UPDATE priorities SET title=? WHERE priority_id=?;";
    private static final String DELETE_PRIORITY =
            "DELETE FROM priorities WHERE priority_id=?;";

    private static final String READ_PRIORITY =
            "SELECT * FROM priorities WHERE priority_id=?;";
    private static final String READ_ALL_PRIORITIES =
            "SELECT * FROM priorities";


    private List<Priority> parsData(ResultSet resultSet) {
        List<Priority> priorities = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Priority priority = new Priority();
                priority.setId(resultSet.getByte("priority_id"));
                priority.setTitle(resultSet.getString("title"));
                priorities.add(priority);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return priorities;
    }

    @Override
    public Priority create(Priority priority) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(CREATE_PRIORITY)) {
            preparedStatement.setString(1, priority.getTitle());
            preparedStatement.execute();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return read(generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Creating priority failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }


    @Override
    public Priority read(Long id) {
        try (PreparedStatement preparedStatement
                     = dataSource.getConnection().prepareStatement(READ_PRIORITY)) {
            preparedStatement.setLong(1, id);
            List<Priority> result = parsData(preparedStatement.executeQuery());
            if (result.size() != 1) {
                throw new SQLException("Error with searching priority by id");
            }
            return result.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Priority update(Priority priority) {
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(UPDATE_PRIORITY)) {
            statement.setString(1, priority.getTitle());
            statement.setByte(2, priority.getId());
            if (statement.executeUpdate() != 1) {
                throw new SQLException("Priorities have not being updated");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return priority;
    }

    @Override
    public void delete(Priority priority) {
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(DELETE_PRIORITY)) {
            statement.setByte(1, priority.getId());
            if (statement.executeUpdate() != 1) {
                throw new SQLException("Priority have not being deleted");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Priority> readAll() {
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(DELETE_PRIORITY)) {
            return parsData(statement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }
}
