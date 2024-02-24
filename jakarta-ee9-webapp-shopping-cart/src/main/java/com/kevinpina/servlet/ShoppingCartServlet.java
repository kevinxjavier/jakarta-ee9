package com.kevinpina.servlet;

import java.io.IOException;
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

@WebServlet("/add-cart")
public class ShoppingCartServlet extends HttpServlet {

	private static final String SHOPPING_CART = "shoppingCart";
	private static final long serialVersionUID = -2396706622130637756L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		ProductService productService = new ProductServiceImpl();
		Optional<Product> product = productService.findById(id);

		if (product.isPresent()) {
			ItemCart itemCart = new ItemCart(1, product.get());

			ShoppingCart shoppingCart = null;

			HttpSession session = req.getSession();
			if (session.getAttribute(SHOPPING_CART) == null) {
				shoppingCart = new ShoppingCart();
				session.setAttribute(SHOPPING_CART, shoppingCart);
			} else {
				shoppingCart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
			}

			shoppingCart.addItemCar(itemCart);

		}
		
		resp.sendRedirect(req.getContextPath() + "/view-cart");
		
	}

}
