package com.kevinpina.configs;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

public class ProducerResources {

	@Produces
	@RequestScoped
	@Named("beanConnection") // because auto discovery is all bean-discovery-mode="all" a class of type
				// Connection.class could be also instantiated so give a name a qualified name.
	private Connection getConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/MySQLDB");
		return ds.getConnection();
	}

}
