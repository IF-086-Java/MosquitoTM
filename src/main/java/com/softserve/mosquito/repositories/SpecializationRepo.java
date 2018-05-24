package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Specialization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SPECIALIZATION)) {
            preparedStatement.setString(1, specialization.getTitle());
            preparedStatement.execute();

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0)
                LOGGER.error("Set up specialization was failed");

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    LOGGER.error("Set up specialization was failed");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_SPECIALIZATION)) {
            preparedStatement.setLong(1, id);
            List<Specialization> specializations = parsData(preparedStatement.executeQuery());
            if (!specializations.isEmpty()) {
                return specializations.iterator().next();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization update(Specialization specialization) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SPECIALIZATION)) {
            preparedStatement.setString(1, specialization.getTitle());
            preparedStatement.setByte(2, specialization.getId());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0)
                return specialization;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return specialization;
    }

    @Override
    public void delete(Specialization specialization) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SPECIALIZATION)) {
            preparedStatement.setByte(1, specialization.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Specialization> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_SPECIALIZATIONS)) {
            return parsData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return Collections.emptyList();
    }
}
