package com.kevinpina.database.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kevinpina.model.Product;

public class ProductRepositoryImpl implements Repository<Product> {

	private Connection connection;

	public ProductRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Product> list() throws SQLException {
		List<Product> products = new ArrayList<Product>();

		final String SQL = "SELECT p.*, c.name AS category_name FROM product AS p INNER JOIN category AS c ON p.category_id = c.id";

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL)) {

			while (resultSet.next()) {
				Product product = getProduct(resultSet);
				products.add(product);
			}
		}

		return products;
	}

	@Override
	public Product findById(Long id) throws SQLException {
		Product product = null;

		final String SQL = "SELECT p.*, c.name AS category_name FROM product AS p INNER JOIN category AS c ON p.category_id = c.id WHERE p.id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setLong(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					product = getProduct(resultSet);
				}
			}
		}

		return product;
	}

	private Product getProduct(ResultSet resultSet) throws SQLException {
		return Product.builder().id(resultSet.getLong("id")).name(resultSet.getString("name"))
				.price(resultSet.getFloat("price")).categoryName(resultSet.getString("category_name")).build();
	}

	@Override
	public void save(Product t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) throws SQLException {
		// TODO Auto-generated method stub

	}

}
