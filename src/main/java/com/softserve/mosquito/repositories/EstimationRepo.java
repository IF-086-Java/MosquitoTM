package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Estimation;
import com.softserve.mosquito.enitities.LogWork;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstimationRepo implements GenericCRUD<Estimation> {
    private final static Logger log = Logger.getLogger(EstimationRepo.class);
    private DataSource datasource = MySqlDataSource.getDataSource();

    private List<Estimation> parsData(ResultSet rs) {
        List<Estimation> estimations = new ArrayList<>();

        try {
            while (rs.next()) {
                Estimation estimation = new Estimation();
                boolean isEstimation = false;

                estimation.setId(rs.getLong("estimation_id"));
                estimation.setEstimation(rs.getInt("estimation"));
                estimation.setRemaining(rs.getInt("remaining"));

                if (rs.getLong("log_work_id") != 0) {
                    LogWork log = new LogWork();
                    log.setId(rs.getLong("log_work_id"));
                    log.setLogDescription(rs.getString("log_description"));
                    log.setLoggedTime(rs.getInt("logged_time"));
                    log.setCreatedDate(rs.getTimestamp("created_date")
                            .toLocalDateTime());
                    log.setUserId(rs.getLong("user_id"));
                    log.setEstimationId(rs.getLong("estimation_id"));

                    for (Estimation item : estimations) {
                        if (item.getId().equals(estimation.getId())) {
                            item.getLogs().add(log);
                            isEstimation = true;
                            break;
                        }
                    }
                    if (!isEstimation) {
                        estimation.getLogs().add(log);
                    }
                }
                if (!isEstimation) {
                    estimations.add(estimation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estimations;
    }

    @Override
    public Estimation create(Estimation estimation) {
        String query = "INSERT INTO estimations (estimation,remaining) VALUE (?,?);";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, estimation.getEstimation());
            statement.setInt(2, estimation.getRemaining());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Creating estimation failed, no rows affected");

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Estimation read(Long id) {
        String query = "SELECT * FROM estimations LEFT JOIN log_works USING(estimation_id) " +
                "WHERE estimation_id=?;";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            List<Estimation> result = parsData(statement.executeQuery());
            if (result.size() != 1) throw new SQLException("Error with searching object by id");
            return result.iterator().next();
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Estimation update(Estimation estimation) {
        String query = "UPDATE estimations SET estimation=?, remaining=? WHERE estimation_id=?;";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(2, estimation.getEstimation());
            statement.setInt(3, estimation.getRemaining());
            statement.setLong(3, estimation.getId());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Object have not being updated");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return estimation;
    }

    @Override
    public void delete(Estimation estimation) {
        String query = "DELETE FROM estimations WHERE estimation_id=? AND estimation=? AND remaining = ?;";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, estimation.getId());
            statement.setInt(2, estimation.getEstimation());
            statement.setInt(3, estimation.getRemaining());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Object have not being deleted");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Estimation> readAll() {
        String query = "SELECT * FROM estimations LEFT JOIN log_works USING(estimation_id);";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            return parsData(statement.executeQuery());
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
