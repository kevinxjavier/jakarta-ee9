package com.kevinpina.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinpina.model.Product;
import com.kevinpina.service.ProductService;
import com.kevinpina.service.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/products.xls", "/products.html", "/products.json" })
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 7757978577654827122L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("------------ Name: [" + req.getAttribute("name") + "]");
		
		ProductService productService = new ProductServiceImpl();
		List<Product> products = productService.list();

		try (PrintWriter out = resp.getWriter()) {

			if (req.getServletPath().contains(".xls")) {

				resp.setContentType("application/vnd.ms-excel;");
				resp.setHeader("Content-Disposition", "attachment; filename=products.xls");
				showTable(products, out);

			} else if (req.getServletPath().contains(".json")) {

				resp.setContentType("application/json;");
				String json = new ObjectMapper().writeValueAsString(products);
				out.write(json);

			} else {
				resp.setContentType("text/html; charset=UTF-8;");
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("		<head>");
				out.println("			<meta charset=\"UTF-8\" />");
				out.println("			<title>Request HTTP Header</title>");
				out.println("		</head>");
				out.println("		<body>");
				out.println("			<h1>Product List</h1>");

				showTable(products, out);

				out.println("			<h5><a href=\"" + req.getContextPath() + "/products.xls\">Download .xls</a><br/>");
				out.println("			<a href=\"" + req.getContextPath() + "/products.json\">Download .json</a><br/></h5>");
				out.println("		</body>");
				out.println("</html>");
			}

		}
	}
	
	

	/**
	 * Call the POST with the following curl:
	 * 
	 * $ curl --location 'http://kevin.cx:9000/jakarta-ee9-webapp/products.json' \
		--header 'Content-Type: application/json' \
		--data '{
		    "id": 3,
		    "name": "Ryzen 7 5505",
		    "price": 450.0,
		    "type": "CPU"
		}'
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletInputStream servletInputStream = req.getInputStream();
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(servletInputStream, Product.class);
		
		try (PrintWriter out = resp.getWriter()) {
			resp.setContentType("text/html; charset=UTF-8;");
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("		<head>");
			out.println("			<meta charset=\"UTF-8\" />");
			out.println("			<title>Product Details</title>");
			out.println("		</head>");
			out.println("		<body>");
			out.println("			<h1>Product Details</h1>");
			out.println("			<b>Id:</b> " + product.getId() + "<br/>");
			out.println("			<b>Name:</b> " + product.getName() + "<br/>");
			out.println("			<b>Price:</b> " + product.getPrice() + "<br/>");
			out.println("			<b>Type:</b> <td>" + product.getType() + "</br/>");
			out.println("		</body>");
			out.println("</html>");
		}
		
	}



	private void showTable(List<Product> products, PrintWriter out) {
		out.println("			<table>");
		out.println("				<tr>");
		out.println("					<th>Id</th>");
		out.println("					<th>Name</th>");
		out.println("					<th>Price</th>");
		out.println("					<th>Type</th>");
		out.println("				</tr>");
		products.forEach(product -> {
			out.println("			<tr>");
			out.println("				<td>" + product.getId() + "</td>");
			out.println("				<td>" + product.getName() + "</td>");
			out.println("				<td>" + product.getPrice() + "</td>");
			out.println("				<td>" + product.getType() + "</td>");
			out.println("			</tr>");
		});
		out.println("			</table>");
	}

}
