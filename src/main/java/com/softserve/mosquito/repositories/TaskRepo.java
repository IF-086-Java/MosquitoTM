package com.softserve.mosquito.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softserve.entities.Task;

public class TaskRepo implements GenericCRUD<Task> {
    private static final Logger LOGGER = LogManager.getLogger(TaskRepo.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_TASK =
            "INSERT INTO tasks (name, parent_id, owner_id, worker_id, " +
                    "estimation_id, priority_id, status_id)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TASK =
            "UPDATE tasks SET name = ?, worker_id = ?, " +
                    "priority_id =?, status_id = ? WHERE task_id = ?";
    private static final String DELETE_TASK =
            "DELETE FROM tasks WHERE task_id = ?";
    
    private static final String READ_TASK =
            "SELECT * FROM tasks t JOIN statuses USING(status_id) " +
            "JOIN estimations e ON e.estimation_id=t.estimation_id " +
            "JOIN priorities p ON t.priority_id=p.priority_id WHERE task_id=?;";
    
    private static final String READ_ALL_TASKS =
            "SELECT * FROM tasks t JOIN statuses USING(status_id) " +
            "JOIN estimations e ON t.estimation_id=e.estimation_id " +
            "JOIN priorities p ON t.priority_id=p.priority_id;";

    @Override
    public Task create(Task task) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(CREATE_TASK)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setLong(2, task.getParentId());
            preparedStatement.setLong(3, task.getOwnerId());
            preparedStatement.setLong(4, task.getWorkerId());
            preparedStatement.setLong(5, task.getEstimation().getId());
            preparedStatement.setByte(6, task.getPriority().getId());
            preparedStatement.setByte(7, task.getStatus().getId());

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
    public Task update(Task task) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(UPDATE_TASK)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setLong(2, task.getWorkerId());
            preparedStatement.setByte(3, task.getPriority().getId());
            preparedStatement.setByte(4, task.getStatus().getId());
            preparedStatement.setLong(5, task.getId());

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return read(task.getId());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void delete(Task task) {
        try (PreparedStatement preparedStatement =
                     dataSource.getConnection().prepareStatement(DELETE_TASK)) {
            preparedStatement.setLong(1, task.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

//    private Task getData(ResultSet resultSet) {
//        Task task = new Task();
//        try {
//            if (resultSet.next()) {
//                task.setId(resultSet.getLong("task_id"));
//                task.setParentId(resultSet.getLong("parent_id"));
//                task.setOwnerId(resultSet.getLong("owner_id"));
//                task.setWorkerId(resultSet.getLong("worker_id"));
//                task.setEstimation(new Estimation(
//                        resultSet.getLong(8),
//                        resultSet.getInt(9),
//                        resultSet.getInt(10)));
//                task.setPriority(new Priority(
//                        resultSet.getByte(11),
//                        resultSet.getString(12)));
//                task.setStatus(new Status(
//                        resultSet.getByte(13),
//                        resultSet.getString(14)));
//                return task;
//            } else {
//                return task;
//            }
//        } catch (SQLException e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//        return task;
//    }

    @Override
    public Task read(Long id) {
//        try (PreparedStatement preparedStatement =
//                     dataSource.getConnection(.prepareStatement(READ_TASK)) {
//
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            return getData(resultSet);
//        } catch (SQLException e) {
//            LOGGER.error(e.getMessage(), e);
//        }
        return null;
    }

    @Override
    public List<Task> readAll() {
        List<Task> tasks = new ArrayList<>();
//        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(READ_ALL_TASKS);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            while (resultSet.next()) {
//                tasks.add(getData(resultSet));
//            }
//            return tasks;
//
//        } catch (SQLException e) {
//            LOGGER.error(e.getMessage(), e);
//        }
        return tasks;
    }
}
