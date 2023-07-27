package com.kevinpina.database.repositories;

import static com.kevinpina.database.fields.CategoryFieldSQL.CATEGORY;
import static com.kevinpina.database.fields.ProductFieldSQL.CATEGORY_ID;
import static com.kevinpina.database.fields.ProductFieldSQL.DATE;
import static com.kevinpina.database.fields.ProductFieldSQL.NAME;
import static com.kevinpina.database.fields.ProductFieldSQL.PRICE;
import static com.kevinpina.database.fields.ProductFieldSQL.ID;
import static com.kevinpina.database.fields.ProductFieldSQL.SKU;
import static com.kevinpina.database.queries.ProductQuerySQL.DELETE;
import static com.kevinpina.database.queries.ProductQuerySQL.INSERT;
import static com.kevinpina.database.queries.ProductQuerySQL.SELECT_ALL;
import static com.kevinpina.database.queries.ProductQuerySQL.SELECT_BY_ID;
import static com.kevinpina.database.queries.ProductQuerySQL.UPDATE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kevinpina.model.Category;
import com.kevinpina.model.Product;

public class ProductRepositoryImpl implements Repository<Product> {

	private Connection connection;

	public ProductRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Product> list() throws SQLException {
		List<Product> products = new ArrayList<Product>();

		final String SQL = SELECT_ALL.getSql();

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

		final String SQL = SELECT_BY_ID.getSql();

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
		return Product.builder().id(resultSet.getLong(ID.getField())).name(resultSet.getString(NAME.getField()))
				.price(resultSet.getDouble(PRICE.getField())).date(resultSet.getDate(DATE.getField()).toLocalDate())
				.sku(resultSet.getString(SKU.getField()))
				.category(Category.builder().id(resultSet.getLong(CATEGORY_ID.getField()))
						.name(resultSet.getString(CATEGORY.getField() + "_" + NAME.getField())).build())
				.build();
	}

	@Override
	public void save(Product product) throws SQLException {

		boolean idIsNotNull = product.getId() != null && product.getId() > 0;

		String sql = null;

		if (idIsNotNull) {
			sql = UPDATE.getSql();
		} else {
			sql = INSERT.getSql();
		}

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setString(3, product.getSku());
			preparedStatement.setLong(4, product.getCategory().getId());

			if (idIsNotNull) {
				preparedStatement.setLong(5, product.getId());
			} else {
				preparedStatement.setDate(5, Date.valueOf(product.getDate()));
			}

			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				System.out.println(sql.substring(0, 6) + " Success. Product = [" + product + "]");
			}
		}

	}

	@Override
	public void delete(Long id) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE.getSql())) {
			preparedStatement.setLong(1, id);
			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				System.out.println("DELETE Success. Id = [" + id + "]");
			}
		}
	}

}
