package com.kevinpina.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = -2618400749593917772L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// This forward is the same Request! So after redirect the URL still will be /dispatcher and not /products.html
		req.setAttribute("name", "kevin piña");
		getServletContext().getRequestDispatcher("/products.html").forward(req, resp);
		
//		This Redirect is a new Request! so after redirect the URL will be /products.html, watch RedirectServlet.java
//		resp.setHeader("Location", req.getContextPath() + "/products.html");
//		resp.setStatus(HttpServletResponse.SC_FOUND);
//		Or 
//		resp.sendRedirect(req.getContextPath() + "/products.html");
	}

}
