package com.kevinpina.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kevinpina.model.Categoria;
import com.kevinpina.model.Producto;
import com.kevinpina.repository.CategoriaRepositorioImpl;
import com.kevinpina.repository.Repositorio;
import com.kevinpina.util.MySQLConnection;

public class CategoriaServicioImpl implements Servicio<Categoria> {

	private Repositorio<Categoria> repositorio;

	public CategoriaServicioImpl() {
		repositorio = new CategoriaRepositorioImpl();
	}

	@Override
	public List<Categoria> findAll() throws SQLException {
		try (Connection connection = MySQLConnection.getConnection()) {
			repositorio.setConnection(connection);
			return repositorio.findAll();
		}
	}

	@Override
	public Categoria findById(Integer id) throws SQLException {
		try (Connection connection = MySQLConnection.getConnection()) {
			repositorio.setConnection(connection);
			return repositorio.findById(id);
		}
	}

	@Override
	public Categoria save(Categoria categoria) throws SQLException {
		try (Connection connection = MySQLConnection.getConnection()) {
			repositorio.setConnection(connection);

			if (connection.getAutoCommit()) {
				connection.setAutoCommit(false);
			}

			Categoria categoriaGuardada = null;
			try {
				categoriaGuardada = repositorio.save(categoria);
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			}
			return categoriaGuardada;
		}
	}

	@Override
	public void delete(Integer id) throws SQLException {
		try (Connection connection = MySQLConnection.getConnection()) {
			repositorio.setConnection(connection);

			if (connection.getAutoCommit()) {
				connection.setAutoCommit(false);
			}

			try {
				repositorio.delete(id);
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			}
		}
	}

	@Override
	public Producto save(Producto producto, Categoria categoria) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
