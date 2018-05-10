package com.softserve.mosquito.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.softserve.mosquito.enitities.*;

public class UserRepo implements GenericCRUD<User> {

    Logger LOGGER = Logger.getLogger(UserRepo.class);
    private DataSource datasource = MySqlDataSource.getDataSource();

    @Override
    public User create(User user) {
        String sqlQuery = "insert into users (email, firstName, lastName, password)"
                + "values (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try (Connection connection = datasource.getConnection()){
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.error("Creating user failed, no rows affected");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return read(generatedKeys.getLong(1));
                } else {
                    LOGGER.error("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public User read(Long id) {
        String sqlQuery = "select "
                + "email, firstName, lastName, password"
                + "from users user "
                + "where user.user_id = ?";
        PreparedStatement ps = null;
        try (Connection connection = datasource.getConnection()){
            ps = connection.prepareStatement(sqlQuery);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        String sqlQuery = "update users set email = ?, firstName = ?, lastName = ?, password = ? where user_id = ?";
        PreparedStatement ps = null;
        try (Connection connection = datasource.getConnection()){
            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setLong(5, user.getId());

            int updatedRows = ps.executeUpdate();
            if (updatedRows > 0) {
                return read(user.getId());
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void delete(User user) {
        String sqlQuery = "delete users where user_id = ?";
        PreparedStatement ps = null;
        try (Connection connection = datasource.getConnection()){
            ps = connection.prepareStatement(sqlQuery);
            ps.setLong(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Override
    public List<User> readAll() {
        String sqlQuery = "select "
                + "email, firstName, lastName, password"
                + "from users user ";
        PreparedStatement ps = null;
        List<User> users = new ArrayList<>();
        try (Connection connection = datasource.getConnection()){
            ps = connection.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3),	rs.getString(4));
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }
}
