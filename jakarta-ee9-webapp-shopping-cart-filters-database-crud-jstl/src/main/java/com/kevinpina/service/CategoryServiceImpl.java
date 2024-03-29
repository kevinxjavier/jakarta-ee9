package com.kevinpina.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.kevinpina.database.repositories.CategoryRepositoryImpl;
import com.kevinpina.database.repositories.Repository;
import com.kevinpina.exceptions.ServiceDatabaseException;
import com.kevinpina.model.Category;

public class CategoryServiceImpl implements Service<Category> {

	Repository<Category> repository;

	public CategoryServiceImpl(Connection connection) {
		repository = new CategoryRepositoryImpl(connection);
	}

	@Override
	public List<Category> list() {
		try {
			return repository.list();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceDatabaseException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Optional<Category> findById(Long id) {
		try {
			return Optional.ofNullable(repository.findById(id));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceDatabaseException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void save(Category category) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void delete(Long id) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
