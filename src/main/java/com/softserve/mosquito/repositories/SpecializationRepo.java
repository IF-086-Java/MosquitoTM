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

public class SpecializationRepo implements GenericCRUD<Specialization>{
    private static final Logger Log = LogManager.getLogger(SpecializationRepo.class);
    private DataSource datasource = MySqlDataSource.getDataSource();

    private List<Specialization> parsData(ResultSet rs) {
        List<Specialization> specializations = new ArrayList<>();

        try {
            while (rs.next()) {
                Specialization specialization = new Specialization();
                specialization.setSpecializationId(rs.getByte("specialization_id"));
                specialization.setTitle(rs.getString("title"));
                specializations.add(specialization);
            }
        } catch (SQLException e) {
            Log.error(e.getMessage());
        }
        return specializations;
    }

    @Override
    public Specialization create(Specialization specialization) {
        String sqlQuery = "INSERT INTO specializations (specialization_id,title) VALUE (?,?);";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setByte(1, specialization.getSpecializationId());
            statement.setString(2, specialization.getTitle());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Creating specialization failed, no rows affected");

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating specialization failed, no ID obtained.");
            }
        } catch (SQLException e) {
            Log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Specialization read(Long id) {
        String sqlQuery = "SELECT specialization_id, title  FROM specializations WHERE specialization_id=?;";
        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            List<Specialization> result = parsData(statement.executeQuery());
            if (result.size() != 1) throw new SQLException("Error with searching specialization by id");
            return result.iterator().next();
        } catch (SQLException e) {
            Log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Specialization update(Specialization specialization) {
        String sqlQuery = "UPDATE specializations SET specialization_id=?, title=? WHERE specialization_id=?;";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setByte(1, specialization.getSpecializationId());
            statement.setString(2, specialization.getTitle());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Specialization have not being updated");
        } catch (SQLException e) {
            Log.error(e.getMessage());
        }
        return specialization;
    }

    @Override
    public void delete(Specialization specialization) {
        String sqlQuery = "DELETE FROM specializations WHERE specialization_id=?;";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setByte(1, specialization.getSpecializationId());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Specialization have not being deleted");
        } catch (SQLException e) {
            Log.error(e.getMessage());
        }
    }

    @Override
    public List<Specialization> readAll() {
        String sqlQuery = "SELECT specialization_id, title FROM specializations";

        try (Connection connection = datasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = statement.executeQuery()) {
            return parsData(resultSet);

        } catch (SQLException e) {
            Log.error(e.getMessage());
        }
        return Collections.emptyList();
    }
}
