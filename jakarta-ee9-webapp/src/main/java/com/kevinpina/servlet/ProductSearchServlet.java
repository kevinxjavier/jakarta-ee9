package com.kevinpina.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import com.kevinpina.model.Product;
import com.kevinpina.service.ProductService;
import com.kevinpina.service.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class ProductSearchServlet extends HttpServlet {

	private static final long serialVersionUID = -4765498327527890144L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductService productService = new ProductServiceImpl();
		String productName = req.getParameter("product");

		Optional<Product> product = productService.list().stream().filter(p -> {
			if (productName.isBlank()) {
				return false;
			}
			return p.getName().toLowerCase().contains(productName);
		}).findFirst();

		if (product.isPresent()) {
			resp.setContentType("text/html; charset=UTF-8;");
			try (PrintWriter out = resp.getWriter()) {
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("		<head>");
				out.println("			<meta charset=\"UTF-8\" />");
				out.println("			<title>Login</title>");
				out.println("		</head>");
				out.println("		<body>");
				out.println("			<h1>Product Found</h1>");
				out.println("			<h3>" + product.get() + " has been found.</h3>");
				out.println("		</body>");
				out.println("</html>");
			}
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,
					"We are sorry but Kevin does not found the product " + productName + "!");
		}
	}

}
