package com.kevinpina.database.repositories;

import static com.kevinpina.database.fields.CategoryFieldSQL.ID;
import static com.kevinpina.database.fields.CategoryFieldSQL.NAME;
import static com.kevinpina.database.queries.CategoryQuerySQL.SELECT_ALL;
import static com.kevinpina.database.queries.CategoryQuerySQL.SELECT_BY_ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kevinpina.configs.MysqlConnectionPrincipal;
import com.kevinpina.configs.Repositorio;
import com.kevinpina.model.entities.Category;

import jakarta.inject.Inject;

@Repositorio
//@ApplicationScoped
public class CategoryRepositoryImpl implements Repository<Category> {

	private Connection connection;

	@Inject
	public CategoryRepositoryImpl(@MysqlConnectionPrincipal /* Or use @Named("beanConnection")*/ Connection connection) {
		this.connection = connection;
	}

//	private Connection connection;
//
//	public CategoryRepositoryImpl(Connection connection) {
//		this.connection = connection;
//	}

	@Override
	public List<Category> list() throws SQLException {
		List<Category> categories = new ArrayList<>();

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_ALL.getSql())) {
			while (resultSet.next()) {
				categories.add(getCategory(resultSet));
			}
		}

		return categories;
	}

	@Override
	public Category findById(Long id) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID.getSql())) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return getCategory(resultSet);
				}
			}
		}
		return null;
	}

	private Category getCategory(ResultSet resultSet) throws SQLException {
		return Category.builder().id(resultSet.getLong(ID.getField())).name(resultSet.getString(NAME.getField()))
				.build();
	}

	@Override
	public void save(Category t) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Long id) throws SQLException {
		// TODO Auto-generated method stub
	}

}
