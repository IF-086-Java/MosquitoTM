package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements GenericCRUD<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserRepo.class);
    private DataSource datasource = MySqlDataSource.getDataSource();

    @Override
    public User create(User user) {
        String sqlQuery = "INSERT INTO users (email, first_name, last_name, password)  VALUES (?, ?, ?, ?)";
        try (Connection connection = datasource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());

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
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public User read(Long id) {
        String sqlQuery = "SELECT email, first_name, last_name, password  FROM users WHERE user.user_id = ?";
        try (Connection connection = datasource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        String sqlQuery = "UPDATE users SET email = ?, first_name = ?, last_name = ?, password = ? WHERE user_id = ?";
        PreparedStatement preparedStatement = null;
        try (Connection connection = datasource.getConnection()){
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setLong(5, user.getId());

            int updatedRows = preparedStatement.executeUpdate();
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
        String sqlQuery = "DELETE users WHERE user_id = ?";
        try (Connection connection = datasource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Override
    public List<User> readAll() {
        String sqlQuery = "SELECT email, first_name, last_name, password FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = datasource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3),	rs.getString(4)));
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }
    
    public User getUserByEmail(String email) {
    	String sqlQuery = "SELECT user_id, first_name, last_name, email, password FROM users WHERE email = ?";
    	try(Connection connection = datasource.getConnection()){
    		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		if(rs.next()) {
    			User user = new User();
    			user.setId(rs.getLong("user_id"));
    			user.setFirstName(rs.getString("first_name"));
    			user.setLastName(rs.getString("last_name"));
    			user.setEmail(rs.getString("email"));
    			user.setPassword(rs.getString("password"));
    			return user;
    		}
    	} catch(SQLException e) {
    		LOGGER.error(e);
    	}
    	return null;
    }
}
