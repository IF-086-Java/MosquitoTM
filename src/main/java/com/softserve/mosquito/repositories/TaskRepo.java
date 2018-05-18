package com.softserve.mosquito.repositories;

import com.softserve.mosquito.enitities.Estimation;
import com.softserve.mosquito.enitities.Priority;
import com.softserve.mosquito.enitities.Status;
import com.softserve.mosquito.enitities.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepo implements GenericCRUD<Task> {
    private static final Logger LOGGER = LogManager.getLogger(TaskRepo.class);
    private DataSource datasource = MySqlDataSource.getDataSource();

    private static final String CREATE_TASK =
            "INSERT INTO tasks (name, parent_id, owner_id, worker_id, " +
                    "estimation_id, priority_id, status_id)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_TASK =
            "UPDATE tasks SET name = ?, worker_id = ?, " +
                    "priority_id =, status_id = ? ? WHERE task_id = ?";

    private static final String DELETE_TASK = "DELETE tasks WHERE task_id = ?";


//    private static final String READ_TASK = "SELECT "
//            + "task.task_id, task.parent_id, task.owner_id, task.worker_id, task.name, "
//            + "statuses.status_title, priority.priority_title, "
//            + "estim.estimation_id, estim.estimation, estim.remaining "
//            + "FROM tasks task "
//            + "left join statuses statuses on statuses.status_id = task.status_id "
//            + "left join priorities priority on priority.priority_id = task.priority_id "
//            + "left join estimations estim on estim.estimation_id = task.estimation_id "
//            + "WHERE task.task_id = ?";
//
//
//    private static final String READ_ALL_TASKS = "SELECT "
//            + "task.task_id, task.parent_id, task.owner_id, task.worker_id, task.name, "
//            + "statuses.status_title, priority.priority_title, "
//            + "estim.estimation_id, estim.estimation, estim.remaining "
//            + "FROM tasks task "
//            + "left join statuses statuses on statuses.status_id = task.status_id "
//            + "left join priorities priority on priority.priority_id = task.priority_id "
//            + "left join estimations estim on estim.estimation_id = task.estimation_id ";


    @Override
    public Task create(Task task) {
        try (PreparedStatement preparedStatement =
                     datasource.getConnection().prepareStatement(CREATE_TASK)) {
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
                     datasource.getConnection().prepareStatement(UPDATE_TASK)) {
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
                     datasource.getConnection().prepareStatement(DELETE_TASK)) {
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
//                     datasource.getConnection(.prepareStatement(READ_TASK)) {
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
//        List<Task> tasks = new ArrayList<>();
//        try (PreparedStatement preparedStatement = datasource.getConnection().prepareStatement(READ_ALL_TASKS);
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
        return null;
    }
}
