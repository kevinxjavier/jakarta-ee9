package com.kevinpina.configs;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

@Dependent
public class ProducerResources {

	@Resource(name = "jdbc/MySQLDB")
	private DataSource ds;

	@Produces
	@RequestScoped
//	@Named("beanConnection") // because auto discovery is all bean-discovery-mode="all" a class of type
							 // Connection.class could be also instantiated so give a name a qualified name.
	@MysqlConnectionPrincipal // Or use @Named("beanConnection")
	private Connection getConnection() throws NamingException, SQLException {
//		Context initContext = new InitialContext();
//		Context envContext = (Context) initContext.lookup("java:/comp/env");
//		DataSource ds = (DataSource) envContext.lookup("jdbc/MySQLDB");
		return ds.getConnection();
	}

	// @Disposes will close automatically the connection
	// Not only works for databases will work for any Bean
	public void close(@Disposes @MysqlConnectionPrincipal Connection connection) throws SQLException {
		connection.close();
		System.out.println("Closing connection automatically!");
	}
}
