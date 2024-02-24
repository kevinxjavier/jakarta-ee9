package com.kevinpina.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class FormServlet extends HttpServlet {

	private static final long serialVersionUID = -4581580074904182071L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String country = req.getParameter("country");
		List<String> countryCode = req.getParameterValues("country_code") != null
				? Arrays.asList(req.getParameterValues("country_code"))
				: null;
		List<String> roles = req.getParameterValues("roles") != null ? Arrays.asList(req.getParameterValues("roles"))
				: null;
		String language = req.getParameter("language");
		String page = req.getParameter("page");

		Map<String, String> errors = new HashMap<>();
		validate(req, errors);

		try (PrintWriter out = resp.getWriter()) {

			if (errors.isEmpty()) {
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("		<head>");
				out.println("			<meta charset=\"UTF-8\" />");
				out.println("			<title>Form</title>");
				out.println("		</head>");
				out.println("		<body>");
				out.println("		<h1>Result Form</h1>");
				out.println("			<ul>");
				out.println("				<li>Username: " + username + "</li>");
				out.println("				<li>Password: " + password + "</li>");
				out.println("				<li>Email: " + email + "</li>");
				out.println("				<li>Country: " + country + "</li>");
				out.println("				<li>Country Code: " + countryCode + "</li>");
				out.println("				<li>Roles: " + roles + "</li>");
				out.println("				<li>Language: " + language + "</li>");
				out.println("				<li>Page: " + page + "</li>");
				out.println("			</ul>");
				out.println("		</body>");
				out.println("</html>");
			} else {
				/*
				 * out.println("<h3>Errors:</h3>"); out.println("<ol>"); errors.forEach(error ->
				 * { out.println("<li>" + error + "</li>"); }); out.println("</ol>");
				 */

				req.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
			}
		}
	}

	private void validate(HttpServletRequest req, Map<String, String> errors) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String country = req.getParameter("country");
		List<String> countryCode = req.getParameterValues("country_code") != null
				? Arrays.asList(req.getParameterValues("country_code"))
				: null;
		List<String> roles = req.getParameterValues("roles") != null ? Arrays.asList(req.getParameterValues("roles"))
				: null;
		String language = req.getParameter("language");

		if (username == null || username.isBlank()) {
			errors.put("username", "Username could not be empty");
		}
		if (password == null || password.isBlank()) {
			errors.put("password", "Password could not be empty");
		}
		if (email == null || email.isBlank()) {
			errors.put("email", "Email could not be empty");
		}
		if (country == null || country.isBlank()) {
			errors.put("country", "Country could not be empty");
		}
		if (countryCode == null || countryCode.isEmpty()) {
			errors.put("country_code", "Country Code could not be empty");
		}
		if (roles == null || roles.isEmpty()) {
			errors.put("roles", "Roles could not be empty");
		}
		if (language == null || language.isEmpty()) {
			errors.put("language", "Language could not be empty");
		}
	}

}
