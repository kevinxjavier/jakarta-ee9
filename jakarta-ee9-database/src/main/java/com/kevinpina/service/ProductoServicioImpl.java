package com.kevinpina.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kevinpina.model.Categoria;
import com.kevinpina.model.Producto;
import com.kevinpina.repository.CategoriaRepositorioImpl;
import com.kevinpina.repository.ProductoRepositorioImpl;
import com.kevinpina.repository.Repositorio;
import com.kevinpina.util.MySQLConnection;

public class ProductoServicioImpl implements Servicio<Producto> {

	private Repositorio<Producto> repositorioProducto;
	private Repositorio<Categoria> repositorioCategoria;

	public ProductoServicioImpl() {
		repositorioProducto = new ProductoRepositorioImpl();
		repositorioCategoria = new CategoriaRepositorioImpl();
	}

	@Override
	public List<Producto> findAll() throws SQLException {
		try (Connection connection = MySQLConnection.getConnection()) {
			repositorioProducto.setConnection(connection);
			return repositorioProducto.findAll();
		}
	}

	@Override
	public Producto findById(Integer id) throws SQLException {
		try (Connection connection = MySQLConnection.getConnection()) {
			repositorioProducto.setConnection(connection);
			return repositorioProducto.findById(id);
		}
	}

	@Override
	public Producto save(Producto producto) throws SQLException {
		try (Connection connection = MySQLConnection.getConnection()) {
			repositorioProducto.setConnection(connection);
			repositorioCategoria.setConnection(connection);

			if (connection.getAutoCommit()) {
				connection.setAutoCommit(false);
			}

			Producto productoGuardado = null;
			try {
				if (producto.getCategoria() != null) {

					Categoria categoriaEncontrada = repositorioCategoria.findById(producto.getCategoria().getId());

					if (categoriaEncontrada != null) {
						producto.setCategoria(categoriaEncontrada);
					} else {
						if (producto.getCategoria().getNombre() != null
								|| !producto.getCategoria().getNombre().equals("")) {
							Categoria categoriaGuardada = repositorioCategoria.save(producto.getCategoria());
							producto.setCategoria(categoriaGuardada);
						}
					}

				}
				productoGuardado = repositorioProducto.save(producto);
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			}
			return productoGuardado;
		}
	}

	@Override
	public Producto save(Producto producto, Categoria categoria) throws SQLException {
		try (Connection connection = MySQLConnection.getConnection()) {
			repositorioProducto.setConnection(connection);
			repositorioCategoria.setConnection(connection);

			if (connection.getAutoCommit()) {
				connection.setAutoCommit(false);
			}

			Categoria categoriaGuardado = null;

			if (categoria != null) {
				categoriaGuardado = repositorioCategoria.save(categoria);
				producto.setCategoria(categoriaGuardado);
			}

			Producto productoGuardado = null;
			try {
				if (producto.getCategoria() != null) {
					categoriaGuardado = repositorioCategoria.save(producto.getCategoria());
					producto.setCategoria(categoriaGuardado);
				}
				productoGuardado = repositorioProducto.save(producto);
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			}
			return productoGuardado;
		}
	}

	@Override
	public void delete(Integer id) throws SQLException {
		try (Connection connection = MySQLConnection.getConnection()) {
			repositorioProducto.setConnection(connection);

			if (connection.getAutoCommit()) {
				connection.setAutoCommit(false);
			}

			try {
				repositorioProducto.delete(id);
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			}
		}
	}

}
