package com.softserve.mosquito.configs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.softserve.mosquito.repositories.DBConnectionManager;

@WebListener
public class AppContextListener implements ServletContextListener {

	/**
	 * Runs when application started
	 * */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	
    	try {
    		Properties props = new Properties();
    		props.load(AppContextListener.class.getResourceAsStream("/db.properties")); // load DB properties			
    		String dbUrl 	= props.getProperty("MYSQL_DB_URL");
    		String dbUser 	= props.getProperty("MYSQL_DB_USERNAME");
    		String dbPass 	= props.getProperty("MYSQL_DB_PASSWORD");
    		
    		DBConnectionManager connectionManager = new DBConnectionManager(dbUrl, dbUser, dbPass);
			if(connectionManager.getConnection() != null) {
    			ctx.setAttribute("DBConnection", connectionManager.getConnection());// add DBConnection to application context
				System.out.println("DB Connection initialized successfully.");
    		}
    	} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	Connection connection = (Connection) servletContextEvent
    			.getServletContext()
    			.getAttribute("DBConnection");
    	try {
    		connection.close();
			System.out.println("DB Connection closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
}