package com.kevinpina.servlet;

import java.io.IOException;
import java.sql.Connection;

import com.kevinpina.model.Product;
import com.kevinpina.service.ProductServiceImpl;
import com.kevinpina.service.Service;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/delete")
public class ProductDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 252301810791239445L;
	
	@Inject
	private Service<Product> serviceProduct;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Connection connection = (Connection) req.getAttribute("connection");

//		Service<Product> serviceProduct = new ProductServiceImpl(connection);

		Long id;
		try {
			id = Long.valueOf(req.getParameter("id"));
		} catch (NumberFormatException e) {
			id = 0L;
		}

		if (id > 0) {
			serviceProduct.delete(id);
			req.setAttribute("title", "Products");
			resp.sendRedirect(req.getContextPath() + "/products");
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id of product is not present!");
		}

	}

}
