package com.softserve.mosquito.repositories;


import com.softserve.mosquito.enitities.Status;
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

public class StatusRepo implements GenericCRUD<Status>{
    private static final Logger Log = LogManager.getLogger(StatusRepo.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private  List<Status> parseData(ResultSet rs){
        List<Status> statuses = new ArrayList<>();
        try{
            while (rs.next()){
                Status status = new Status();
                status.setStatusId(rs.getByte("status_id"));
                status.setTitle(rs.getString("title"));
                statuses.add(status);
            }
        }catch (SQLException e){
            Log.error(e.getMessage());
        }
        return statuses;
    }

    @Override
    public Status create(Status status) {
        String sqlQuery = "INSERT INTO statuses (status_id, title) VALUE (?,?);";

        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setByte(1, status.getStatusId());
            statement.setString(2, status.getTitle());
            int updateRow = statement.executeUpdate();
            if(updateRow !=1) throw  new SQLException("Creating status failed, no rows affected");

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return read(generatedKeys.getLong(1));

                }else throw new SQLException("Creating status failed, no ID obtained.");
            }

        } catch (SQLException e) {
            Log.error(e.getMessage());
            return null;
        }

    }

    @Override
    public Status read(Long id) {
        String sqlQuery = "SELECT status_id, title FROM statuses WHERE status_id=?";

        try( Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            List<Status> result = parseData(statement.executeQuery());
            if(result.size()!=1){
                throw new SQLException("Error with searching status by id");
            }return result.iterator().next();

        } catch (SQLException e) {
            Log.error(e.getMessage());
            return  null;
        }
    }

    @Override
    public Status update(Status status) {
        String sqlQuery = "UPDATE statuses SET title=? WHERE status_id=?";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, status.getTitle());
            statement.setByte(2, status.getStatusId());
            int updateRow = statement.executeUpdate();
            if (updateRow !=1) throw  new SQLException("Statuses have not being updated");

        } catch (SQLException e) {
            Log.error(e.getMessage());
        }
        return  status;
    }


    @Override
    public void delete(Status status) {
    }

    @Override
    public List<Status> readAll() {
        String sqlQuery = "SELECT status_id, title FROM statuses";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery()){
            return parseData(resultSet);
        } catch (SQLException e) {
            Log.error(e.getMessage());
        }
        return Collections.emptyList();

    }
}
