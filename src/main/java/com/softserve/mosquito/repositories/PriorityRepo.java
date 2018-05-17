package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Priority;
import org.apache.logging.log4j.LogManager;
import sun.rmi.runtime.Log;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class PriorityRepo implements GenericCRUD<Priority>{
    private static final org.apache.logging.log4j.Logger Log = LogManager.getLogger(PriorityRepo.class);
    private DataSource datasource = MySqlDataSource.getDataSource();

    private List<Priority> parsData(ResultSet rs) {
        List<Priority> priorities = new ArrayList<>();

        try {
            while (rs.next()) {
                Priority priority = new Priority();
                priority.setId(rs.getByte("priority_id"));
                priority.setTitle(rs.getString("title"));
                priorities.add(priority);
            }
        } catch (SQLException e) {
            Log.error(e.getMessage());
        }
        return priorities;
    }

    @Override
    public Priority create(Priority priority) {
        String sqlQuery = "INSERT INTO priorities (title) VALUE (?);";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, priority.getTitle());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Creating priority failed, no rows affected");

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    throw new SQLException("Creating priority failed, no ID obtained.");
            }
        } catch (SQLException e) {
            Log.error(e.getMessage());
            return null;
        }
    }


    @Override
    public Priority read(Long id) {
        String sqlQuery = "SELECT priority_id, title "
                + " FROM priorities WHERE priority_id=?;";
        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            List<Priority> result = parsData(statement.executeQuery());
            if (result.size() != 1) throw new SQLException("Error with searching priority by id");
            return result.iterator().next();
        } catch (SQLException e) {
            Log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Priority update(Priority priority) {
        String sqlQuery = "UPDATE priorities SET title=? WHERE priority_id=?;";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, priority.getTitle());
            statement.setByte(2, priority.getId());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Priorities have not being updated");
        } catch (SQLException e) {
            Log.error(e.getMessage());
        }
        return priority;
    }

    @Override
    public void delete(Priority priority) {
        String sqlQuery = "DELETE FROM priorities WHERE priority_id=?;";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setByte(1, priority.getId());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Priority have not being deleted");
        } catch (SQLException e) {
            Log.error(e.getMessage());
        }
    }

    @Override
    public List<Priority> readAll() {
        String sqlQuery = "SELECT priority_id, title FROM priorities";

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
