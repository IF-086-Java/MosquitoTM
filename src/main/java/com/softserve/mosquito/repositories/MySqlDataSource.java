package com.softserve.mosquito.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MySqlDataSource {

	private static final Logger LOGGER = LogManager.getLogger(StatusRepo.class);
	private static DataSource ds = null;

	static {
		LOGGER.info("Inside Database() static method... ");
		Context context = null;
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/mosquitoDB");
		} catch (NamingException e) {
			LOGGER.error("Unable to get Datasource...");
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource() {
		return ds;
	}	
}
