package com.kevinpina.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import com.kevinpina.model.ItemCart;
import com.kevinpina.model.Product;
import com.kevinpina.model.ShoppingCart;
import com.kevinpina.service.ProductService;
import com.kevinpina.service.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart/add")
public class ShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = -2396706622130637756L;

	public static final String SHOPPING_CART = "shoppingCart";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));

		Connection connection = (Connection) req.getAttribute("connection");
		ProductService productService = new ProductServiceImpl(connection);
		Optional<Product> product = productService.findById(id);

		if (product.isPresent()) {
			ItemCart itemCart = new ItemCart(1, product.get());

			HttpSession session = req.getSession();
			ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(SHOPPING_CART);

			shoppingCart.addItemCar(itemCart);

		}

		resp.sendRedirect(req.getContextPath() + "/cart/view");

	}

}
