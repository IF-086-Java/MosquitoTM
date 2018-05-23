package com.softserve.mosquito.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softserve.mosquito.enitities.User;

public class UserRepo implements GenericCRUD<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserRepo.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_USER =
            "INSERT INTO users (email, password, first_name, last_name) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER =
            "UPDATE users SET email = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?";
    private static final String DELETE_USER =
            "DELETE FROM users WHERE id = ?";


    private static final String READ_USER =
            "SELECT * FROM users WHERE id = ?";
    private static final String READ_ALL_USERS =
            "SELECT * FROM users";
    private static final String READ_USER_BY_EMAIL =
            "SELECT * FROM users WHERE email = ?";


    @Override
    public User create(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.error("Creating user failed, no rows affected");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return read(generatedKeys.getLong(1));
                } else {
                    LOGGER.error("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setLong(5, user.getId());

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return read(user.getId());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void delete(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }



    @Override
    public User read(Long id) {
        try (Connection connection = dataSource.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(READ_USER)) {

            preparedStatement.setLong(1, id);
            List<User> users = getData(preparedStatement.executeQuery());
            if(!users.isEmpty())
            	return users.iterator().next();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


    @Override
    public List<User> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return getData(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


    public User readUserByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_USER_BY_EMAIL)) {

            preparedStatement.setString(1, email);
            List<User> users = getData(preparedStatement.executeQuery());
            if(!users.isEmpty())
            	return users.iterator().next();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private List<User> getData(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));

                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }
}
