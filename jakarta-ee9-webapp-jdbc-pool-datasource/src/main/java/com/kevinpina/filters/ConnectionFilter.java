package com.kevinpina.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.kevinpina.database.ConnectionDatabaseDataSource;
import com.kevinpina.exceptions.ServiceDatabaseException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*") // Any route
public class ConnectionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try (Connection connection = ConnectionDatabaseDataSource.getConnection()) {

			if (connection.getAutoCommit()) {
				connection.setAutoCommit(false);
			}

			doFilter(request, response, chain, connection);

		} catch (SQLException | ServiceDatabaseException | NamingException e) {
			e.printStackTrace();
			
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Kevin says! " + e.getMessage());
		}

	}

	private void doFilter(ServletRequest request, ServletResponse response, FilterChain chain, Connection connection)
			throws IOException, ServletException, SQLException {
		try {
			request.setAttribute("connection", connection);
			chain.doFilter(request, response);
			connection.commit();

		} catch (SQLException e) {
			connection.rollback();
			throw e;
		}
	}

	

}
