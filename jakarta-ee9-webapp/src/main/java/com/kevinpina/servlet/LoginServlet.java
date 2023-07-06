package com.kevinpina.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4902515493740412080L;

	private static final String USERNAME = "login";
	private static final String PASSWORD = "pass";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (USERNAME.equals(username) && PASSWORD.equals(password)) {
			resp.setContentType("text/html; charset=UTF-8;");
			try (PrintWriter out = resp.getWriter()) {
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("		<head>");
				out.println("			<meta charset=\"UTF-8\" />");
				out.println("			<title>Login</title>");
				out.println("		</head>");
				out.println("		<body>");
				out.println("			<h1>Login Successful</h1>");
				out.println("			<h3>User " + username + " has Loged.</h3>");
				out.println("		</body>");
				out.println("</html>");
			}
		} else {
//			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"We're so sorry but Kevin say! your are not authorized to see this page!");
		}
	}

}
