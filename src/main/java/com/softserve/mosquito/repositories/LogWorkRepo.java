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

import com.softserve.entities.LogWork;

public class LogWorkRepo implements GenericCRUD<LogWork> {

    private static final Logger LOGGER = LogManager.getLogger(LogWorkRepo.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_LOG_WORK =
            "INSERT INTO log_works (description, logged, user_id, log_work_id) VALUE (?,?,?,?);";
    private static final String UPDATE_LOG_WORK =
            "UPDATE log_works SET description=?, logged=? WHERE log_work_id=?;";
    private static final String DELETE_LOG_WORK =
            "DELETE FROM log_works WHERE log_work_id=?;";

    private static final String READ_LOG_WORK =
            "SELECT * FROM log_works WHERE log_work_id=?;";
    private static final String READ_ALL_LOG_WORKS =
            "SELECT * FROM log_works;";

    private List<LogWork> parsData(ResultSet resultSet) {
        ArrayList<LogWork> logWorks = new ArrayList<>();
        try {
            while (resultSet.next()) {
                LogWork logWork = new LogWork(resultSet.getLong("log_work_id"),
                        resultSet.getString("description"), resultSet.getInt("logged"),
                        resultSet.getLong("user_id"), resultSet.getLong("estimation_id"),
                        resultSet.getTimestamp("last_update").toLocalDateTime());
                logWorks.add(logWork);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return logWorks;
    }

    @Override
    public LogWork create(LogWork logWork) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(CREATE_LOG_WORK)) {
            preparedStatement.setString(1, logWork.getDescription());
            preparedStatement.setInt(2, logWork.getLogged());
            preparedStatement.setLong(3, logWork.getUserId());
            preparedStatement.setLong(4, logWork.getEstimationId());
            preparedStatement.execute();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return read(generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Creating LogWork failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public LogWork read(Long id) {
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(READ_LOG_WORK)) {
            preparedStatement.setLong(1, id);
            List<LogWork> result = parsData(preparedStatement.executeQuery());
            if (result.size() != 1) {
                throw new SQLException("Error with searching LogWork by id");
            }
            return result.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public LogWork update(LogWork logWork) {
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(UPDATE_LOG_WORK)) {
            preparedStatement.setString(1, logWork.getDescription());
            preparedStatement.setInt(2, logWork.getLogged());
            preparedStatement.setLong(3, logWork.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException("LogWork have not being updated");
            }
        }catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return logWork;
    }

    @Override
    public void delete(LogWork logWork) {
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(DELETE_LOG_WORK)) {
            preparedStatement.setLong(1, logWork.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException("LogWork have not being deleted");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<LogWork> readAll() {
        try (PreparedStatement preparedStatement = dataSource.getConnection()
                .prepareStatement(READ_ALL_LOG_WORKS)) {
            return parsData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }
}
