package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Estimation;
import com.softserve.mosquito.enitities.LogWork;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class EstimationRepo implements GenericCRUD<Estimation> {

    private static final Logger LOGGER = LogManager.getLogger(EstimationRepo.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_ESTIMATION =
            "INSERT INTO estimations (estimation,remaining) VALUE (?,?);";
    private static final String UPDATE_ESTIMATION =
            "UPDATE estimations SET estimation=?, remaining=? WHERE estimation_id=?;";
    private static final String DELETE_ESTIMATION =
            "DELETE FROM estimations WHERE estimation_id=?;";

    private static final String READ_ESTIMATION =
            "SELECT * FROM estimations LEFT JOIN log_works USING(estimation_id) WHERE estimation_id=?;";
    private static final String READ_ALL_ESTIMATIONS = "SELECT * FROM estimations " +
            "LEFT JOIN log_works USING(estimation_id);";

    private List<Estimation> parsData(ResultSet resultSet) {
        HashSet<Estimation> estimations = new HashSet<>();
        try {
            while (resultSet.next()) {
                Estimation estimation = new Estimation(
                        resultSet.getLong("estimation_id"),
                        resultSet.getInt("estimation"),
                        resultSet.getInt("remaining"));

                if (resultSet.getLong("log_work_id") != 0) {
                    LogWork logWork = new LogWork(
                            resultSet.getLong("log_work_id"),
                            resultSet.getString("description"),
                            resultSet.getInt("logged"),
                            resultSet.getLong("user_id"),
                            resultSet.getLong("estimation_id"),
                            resultSet.getTimestamp("last_update").toLocalDateTime());
                    estimation.getLogWorks().add(logWork);

                    for (Estimation item : estimations)
                        if (item.getId().equals(estimation.getId())) {
                            item.getLogWorks().add(logWork);
                            break;
                        }
                } else
                    estimations.add(estimation);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>(estimations);
    }

    @Override
    public Estimation create(Estimation estimation) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ESTIMATION)) {
            preparedStatement.setInt(1, estimation.getEstimation());
            preparedStatement.setInt(2, estimation.getRemaining());
            preparedStatement.execute();
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating Estimation failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Estimation read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ESTIMATION)) {
            preparedStatement.setLong(1, id);
            List<Estimation> result = parsData(preparedStatement.executeQuery());
            if (result.size() != 1){
                throw new SQLException("Error with searching Estimation by id");
            }
            return result.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Estimation update(Estimation estimation) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ESTIMATION)) {
            preparedStatement.setInt(2, estimation.getEstimation());
            preparedStatement.setInt(3, estimation.getRemaining());
            preparedStatement.setLong(3, estimation.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new SQLException("Estimation have not being updated");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return estimation;
    }

    @Override
    public void delete(Estimation estimation) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ESTIMATION)) {
            preparedStatement.setLong(1, estimation.getId());
            if (preparedStatement.executeUpdate() != 1){
                throw new SQLException("Estimation have not being deleted");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Estimation> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_ESTIMATIONS)) {
            return parsData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }
}
