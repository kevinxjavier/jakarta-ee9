package com.kevinpina.servlet;

import java.io.IOException;
import java.util.Optional;

import com.kevinpina.service.LoginCookieService;
import com.kevinpina.service.LoginCookieServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = -5298424009018388726L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginCookieService login = new LoginCookieServiceImpl();
		Optional<String> username = login.getUsername(req);
		
		if (username.isPresent()) {
			Cookie usernameCookie = new Cookie("username", "");
			usernameCookie.setMaxAge(0); // This will destroy all the cookies
			resp.addCookie(usernameCookie); // This set the cookie username to value empty
		}
//		sendRedirect is a new Request"
		resp.sendRedirect(req.getContextPath() + "/login.html");
	}

	
	
}
