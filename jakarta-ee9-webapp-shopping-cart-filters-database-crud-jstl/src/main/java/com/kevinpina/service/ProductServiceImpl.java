package com.kevinpina.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.kevinpina.database.repositories.ProductRepositoryImpl;
import com.kevinpina.database.repositories.Repository;
import com.kevinpina.exceptions.ServiceDatabaseException;
import com.kevinpina.model.Product;

public class ProductServiceImpl implements Service<Product> {

	private Repository<Product> repository;

	public ProductServiceImpl(Connection connection) {
		this.repository = new ProductRepositoryImpl(connection);
	}

	@Override
	public List<Product> list() {
//		return Arrays.asList(new Product(1L, "HP Elite Book 4303s", 3203F, "Computer"),
//				new Product(2L, "Asus Lite GH", 1323F, "Computer"), new Product(3L, "Logitec 2200", 50F, "Keyboard"));
		try {
			return repository.list();
		} catch (SQLException e) {
			throw new ServiceDatabaseException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Optional<Product> findById(Long id) {
//		return list().stream().filter(p -> p.getId().longValue() == id.longValue()).findAny();	
		try {
			return Optional.ofNullable(repository.findById(id));
		} catch (SQLException e) {
			throw new ServiceDatabaseException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void save(Product product) {
		try {
			repository.save(product);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceDatabaseException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void delete(Long id) {
		try {
			repository.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceDatabaseException(e.getMessage(), e.getCause());
		}
	}

}
