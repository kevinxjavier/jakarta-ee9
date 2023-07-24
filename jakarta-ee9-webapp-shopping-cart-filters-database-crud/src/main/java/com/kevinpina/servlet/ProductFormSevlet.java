package com.kevinpina.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.kevinpina.model.Category;
import com.kevinpina.model.Product;
import com.kevinpina.service.CategoryServiceImpl;
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
		Service<Category> serviceCategory = new CategoryServiceImpl(connection);
		req.setAttribute("categories", serviceCategory.list());

		Service<Product> serviceProduct = new ProductServiceImpl(connection);
		Long id = (req.getParameter("id") != null) ? Long.valueOf(req.getParameter("id")) : 0L;
		Optional<Product> product = Optional.empty();
		if (id > 0L) {
			product = serviceProduct.findById(id);
			if (product.isEmpty()) {
				product = Optional.ofNullable(new Product());
			}
		}
		req.setAttribute("product", product);

		getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
//		req.getRequestDispatcher("/product.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection connection = (Connection) req.getAttribute("connection");
		Service<Product> serviceProduct = new ProductServiceImpl(connection);

//		Getting Paramaters
		String name = req.getParameter("name");

		Double price;
		try {
			price = Double.valueOf(req.getParameter("price"));
		} catch (NumberFormatException e) {
			price = 0D;
		}

		String sku = req.getParameter("sku");
		String dateString = req.getParameter("date");

		Long categoryId;
		try {
			categoryId = Long.valueOf(req.getParameter("category"));
		} catch (NumberFormatException e) {
			categoryId = 0L;
		}

//		Validating Parameters
		Map<String, String> errors = new HashMap<>();

		if (name == null || name.isBlank()) {
			errors.put("name", "Name is required!");
		}

		if (price.equals(0D)) {
			errors.put("price", "Price is required!");
		}

		if (sku == null || sku.isBlank()) {
			errors.put("sku", "Sku is required!");
		} else if (sku.length() > 10) {
			errors.put("sku", "Sku is more than 10 characters!");
		}

		if (dateString == null || dateString.isBlank()) {
			errors.put("date", "Date is required!");
		}

		if (categoryId.equals(0L)) {
			errors.put("category", "Category is required!");
		}

		if (errors.isEmpty()) {

			LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

//			Saving Products
			Product product = Product.builder().name(name).sku(sku).price(price).date(date)
					.category(Category.builder().id(categoryId).build()).build();

			serviceProduct.save(product);

//			Important always use redirect to avoid "Refresh the Browser or F5" and save many times
			resp.sendRedirect(req.getContextPath() + "/products");
		} else {
			req.setAttribute("errors", errors);
			doGet(req, resp);
		}

	}

}
