package com.kevinpina.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/login", "/login.html" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4902515493740412080L;

	private static final String USERNAME = "login";
	private static final String PASSWORD = "pass";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (USERNAME.equals(username) && PASSWORD.equals(password)) {

			Cookie usernameCookie = new Cookie("username", username);
			resp.addCookie(usernameCookie);

			/*
			resp.setContentType("text/html; charset=UTF-8;");
			try (PrintWriter out = resp.getWriter()) {
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("		<head>");
				out.println("			<meta charset=\"UTF-8\" />");
				out.println("			<title>Login as &lt;" + username + "&gt;</title>");
				out.println("		</head>");
				out.println("		<body>");
				out.println("			<h1>Login Successful</h1>");
				out.println("			<h3>User &lt;" + username + "&gt; has Logged.</h3>");
				out.println("			<a href='" + req.getContextPath() + "/index.html'>Volver</a>");
				out.println("		</body>");
				out.println("</html>");
			}
			*/
			
			// We comment the above code because if not we can go back to the page and login again
			// After redirect to /login.html we avoid go back to the page and login again "sendRedirect is a new Request"
			resp.sendRedirect(req.getContextPath() + "/login.html");
			
		} else {
//			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"We're so sorry but Kevin say! your are not authorized to see this page!");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];

		Optional<Cookie> cookieOptional1 = Arrays.stream(cookies).filter(c -> "username".equals(c.getName())).findAny();
		
		Cookie cookieOptional2 = Arrays.stream(cookies).filter(c -> "username".equals(c.getName())).findAny().orElse(null);
		
		Optional<String> cookieOptional3 = Arrays.stream(cookies).filter(c -> "username".equals(c.getName())).map(c -> c.getValue()).findAny();
		
		Optional<String> cookieOptional4 = Arrays.stream(cookies).filter(c -> "username".equals(c.getName())).map(Cookie::getValue).findAny();

		if (cookieOptional1.isPresent()) {
			try (PrintWriter out = resp.getWriter()) {
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("		<head>");
				out.println("			<meta charset=\"UTF-8\" />");
				out.println("			<title>Logged as &lt;" + cookieOptional4.get() +"&gt;</title>");
				out.println("		</head>");
				out.println("		<body>");
				out.println("			<h1>Already Logged</h1>");
				out.println("			<h3>User &lt;" + cookieOptional1.get().getValue() + "&gt; Logged.</h3>");
				if (cookieOptional3.isPresent()) {
					out.println("				<tr><th colspan=\'4\'  bgcolor=\'blue\'>"
							+ "<p>User " + cookieOptional3.get() + ". <a href='" + req.getContextPath() + "/index.html'>Volver</a></p>"
							+ "<p><a href=" + req.getContextPath() + "\'/logout\"'>Logout</a></th></tr>");
				}
				out.println("		</body>");
				out.println("</html>");
			}
		} else {
			// This forward is the same Request! So after redirect the URL still will be /login.html and not /login.jsp
			// Otherwise is resp.sendRedirect(req.getContextPath() + "/login.jsp"); whch is a 302 and then a 200 and the URL will be /login.jsp 
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}

}
