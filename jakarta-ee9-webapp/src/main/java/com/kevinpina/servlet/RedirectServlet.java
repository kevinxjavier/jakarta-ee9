package com.kevinpina.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/redirection")
public class RedirectServlet extends HttpServlet {

	private static final long serialVersionUID = 5246442153484429166L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setHeader("Location", req.getContextPath() + "/products.html");
//		resp.setStatus(HttpServletResponse.SC_FOUND);
		
//		The previous 2 lines do exactly the same that this line!
		resp.sendRedirect(req.getContextPath() + "/products.html");
	}

	
	
}
