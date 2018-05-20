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

import com.softserve.entities.Specialization;

public class SpecializationRepo implements GenericCRUD<Specialization> {
    private static final Logger LOGGER = LogManager.getLogger(SpecializationRepo.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_SPECIALIZATION =
            "INSERT INTO specializations (title) VALUE (?);";
    private static final String UPDATE_SPECIALIZATION =
            "UPDATE specializations SET title=? WHERE specialization_id=?;";
    private static final String DELETE_SPECIALIZATION =
            "DELETE FROM specializations WHERE specialization_id=?;";

    private static final String READ_SPECIALIZATION =
            "SELECT * FROM specializations WHERE specialization_id=?;";
    private static final String READ_ALL_SPECIALIZATIONS =
            "SELECT * FROM specializations";

    private List<Specialization> parsData(ResultSet resultSet) {
        List<Specialization> specializations = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Specialization specialization = new Specialization();
                specialization.setId(resultSet.getByte("specialization_id"));
                specialization.setTitle(resultSet.getString("title"));
                specializations.add(specialization);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return specializations;
    }

    @Override
    public Specialization create(Specialization specialization) {
        try (PreparedStatement statement =
                     dataSource.getConnection().prepareStatement(CREATE_SPECIALIZATION)) {
            statement.setString(1, specialization.getTitle());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating specialization failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Specialization read(Long id) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(READ_SPECIALIZATION)) {
            preparedStatement.setLong(1, id);
            List<Specialization> result = parsData(preparedStatement.executeQuery());
            if (result.size() != 1) {
                throw new SQLException("Error with searching specialization by id");
            }
            return result.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Specialization update(Specialization specialization) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(UPDATE_SPECIALIZATION)) {
            preparedStatement.setString(1, specialization.getTitle());
            preparedStatement.setByte(2, specialization.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException("Specialization have not being updated");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return specialization;
    }

    @Override
    public void delete(Specialization specialization) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(DELETE_SPECIALIZATION)) {
            preparedStatement.setByte(1, specialization.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException("Specialization have not being deleted");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Specialization> readAll() {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(READ_ALL_SPECIALIZATIONS)) {
            return parsData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return Collections.emptyList();
    }
}
