package com.softserve.mosquito.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.softserve.mosquito.enitities.Specialization;
import com.softserve.mosquito.repositories.MySqlDataSource;

@Path("/specializations")
public class SpecializationController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Specialization> getAllSpecializations() {
		
		// TODO: remove, replace by service. For testing
		DataSource datasource = MySqlDataSource.getDataSource();
		String sqlQuery = "SELECT * FROM specializations";
		Statement stmt = null;
		List<Specialization> specializations = new ArrayList<>();
		try (Connection connection = datasource.getConnection()){
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				specializations.add(Specialization.valueOf(rs.getString(2)));
			}
			return specializations;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
