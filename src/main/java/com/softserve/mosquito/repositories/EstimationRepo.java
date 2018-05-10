package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Estimation;

import javax.ws.rs.core.Context;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EstimationRepo implements GenericCRUD<Estimation> {
    private Connection connection;

    public EstimationRepo(Connection connection) {
        this.connection = connection;
    }

    private List<Estimation> parsData(ResultSet rs) {
        List<Estimation> estimations = new ArrayList<>();

        try {
            while (rs.next()) {
                estimations.add(new Estimation(rs.getLong("estimation_id"),
                        rs.getInt("estimation"), rs.getInt("remaining")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estimations;
    }

    @Override
    public Estimation create(Estimation estimation) {
        String query = "INSERT INTO estimations (estimation,remaining) VALUE (?,?);";
        Estimation temp = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, estimation.getEstimation());
            statement.setInt(2, estimation.getRemaining());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Object have not being created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "SELECT * FROM estimations WHERE estimation_id=(SELECT last_insert_id());";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            List<Estimation> result = parsData(statement.executeQuery());
            if (result.size() != 1) throw new SQLException("Error with searching created object");
            temp = result.iterator().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public Estimation read(Long id) {
        String query = "SELECT * FROM estimations WHERE estimation_id=" + id + ";";
        Estimation temp = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            List<Estimation> result = parsData(statement.executeQuery());
            if (result.size() != 1) throw new SQLException("Error with searching object by id");
            temp = result.iterator().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public Estimation update(Estimation estimation) {
        String query = "UPDATE estimations SET estimation=?, remaining=? WHERE estimation_id=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(2, estimation.getEstimation());
            statement.setInt(3, estimation.getRemaining());
            statement.setLong(3, estimation.getId());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Object have not being updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estimation;
    }

    @Override
    public void delete(Estimation estimation) {
        String query = "DELETE FROM estimations WHERE estimation_id=? AND estimation=? AND remaining = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, estimation.getId());
            statement.setInt(2, estimation.getEstimation());
            statement.setInt(3, estimation.getRemaining());
            int updatedRow = statement.executeUpdate();
            if (updatedRow != 1) throw new SQLException("Object have not being deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Estimation> readAll() {
        String query = "SELECT * FROM estimations;";
        List<Estimation> estimations = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            estimations = parsData(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estimations;
    }
}
