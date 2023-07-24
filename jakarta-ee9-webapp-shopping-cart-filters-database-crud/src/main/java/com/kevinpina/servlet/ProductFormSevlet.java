package com.kevinpina.servlet;

import java.io.IOException;
import java.sql.Connection;

import com.kevinpina.model.Product;
import com.kevinpina.service.ProductServiceImpl;
import com.kevinpina.service.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/form")
public class ProductFormSevlet extends HttpServlet {

	private static final long serialVersionUID = 400264055384925024L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection connection = (Connection) req.getAttribute("connection");
		Service<Product> service = new ProductServiceImpl(connection);
		
		req.setAttribute("categories", service.list());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	
}
