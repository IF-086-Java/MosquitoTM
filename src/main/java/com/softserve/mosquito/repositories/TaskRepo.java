package com.softserve.mosquito.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.softserve.mosquito.enitities.*;

public class TaskRepo implements GenericCRUD<Task> {
	Logger LOGGER = Logger.getLogger(TaskRepo.class); 
	private DataSource datasource = MySqlDataSource.getDataSource();
	
	@Override
	public Task create(Task task) {
		String sqlQuery = "insert into tasks (parent_id, owner_id, worker_id, name, status_id, priority_id, estimation_id)"
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try (Connection connection = datasource.getConnection()){
			ps = connection.prepareStatement(sqlQuery);
			ps.setLong(1, task.getParentId());
			ps.setLong(2, task.getOwnerId());
			ps.setLong(3, task.getWorkerId());
			ps.setString(4, task.getName());
			ps.setLong(5, task.getStatus().getStatusNumber());
			ps.setLong(6, task.getPriority().getPriorityNumber());
			ps.setLong(7, task.getEstimation().getId());

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
	public Task read(Long id) {
		String sqlQuery = "select "
				+ "task.task_id, task.parent_id, task.owner_id, task.worker_id, task.name, "
				+ "statuses.status_title, priority.priority_title, "
				+ "estim.estimation_id, estim.estimation, estim.remaining "
				+ "from tasks task "
				+ "left join statuses statuses on statuses.status_id = task.status_id "
				+ "left join priorities priority on priority.priority_id = task.priority_id "
				+ "left join estimations estim on estim.estimation_id = task.estimation_id "
				+ "where task.task_id = ?";
		PreparedStatement ps = null;
		try (Connection connection = datasource.getConnection()){
			ps = connection.prepareStatement(sqlQuery);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Status status = Status.valueOf(rs.getString(6));
				Priority priority = Priority.valueOf(rs.getString(7));
				Estimation estimation = new Estimation(rs.getLong(8), rs.getInt(9), rs.getInt(10));
				return new Task(rs.getLong(1), rs.getLong(2), rs.getLong(3),	rs.getLong(4), rs.getString(5),
						status,
						priority,
						estimation);
			} else {
				return null;
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public Task update(Task task) {
		String sqlQuery = "update tasks set name = ?, worker_id = ?, status_id = ?, priority_id = ? where task_id = ?";
		PreparedStatement ps = null;
		try (Connection connection = datasource.getConnection()){
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, task.getName());
			ps.setLong(2, task.getId());
			ps.setLong(3, task.getStatus().getStatusNumber());
			ps.setLong(4, task.getPriority().getPriorityNumber());
			ps.setLong(5, task.getId());
			
			int updatedRows = ps.executeUpdate();
			if (updatedRows > 0) {
				return read(task.getId());
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		} 
		return null;
	}

	@Override
	public void delete(Task task) {
		String sqlQuery = "delete tasks where task_id = ?";
		PreparedStatement ps = null;
		try (Connection connection = datasource.getConnection()){
			ps = connection.prepareStatement(sqlQuery);
			ps.setLong(1, task.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		} 
	}

	@Override
	public List<Task> readAll() {
		String sqlQuery = "select "
				+ "task.task_id, task.parent_id, task.owner_id, task.worker_id, task.name, "
				+ "statuses.status_title, priority.priority_title, "
				+ "estim.estimation_id, estim.estimation, estim.remaining "
				+ "from tasks task "
				+ "left join statuses statuses on statuses.status_id = task.status_id "
				+ "left join priorities priority on priority.priority_id = task.priority_id "
				+ "left join estimations estim on estim.estimation_id = task.estimation_id ";
		PreparedStatement ps = null;
		List<Task> tasks = new ArrayList<>();
		try (Connection connection = datasource.getConnection()){
			ps = connection.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Status status = Status.valueOf(rs.getString(6));
				Priority priority = Priority.valueOf(rs.getString(7));
				Estimation estimation = new Estimation(rs.getLong(8), rs.getInt(9), rs.getInt(10));
				tasks.add(new Task(rs.getLong(1), rs.getLong(2), rs.getLong(3),	rs.getLong(4), rs.getString(5),
						status,	priority, estimation));
			}
			return tasks;
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return null;
	}

}
