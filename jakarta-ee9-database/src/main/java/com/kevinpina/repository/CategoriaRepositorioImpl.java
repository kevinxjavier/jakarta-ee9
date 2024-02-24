package com.kevinpina.repository;

import com.kevinpina.model.Categoria;
import com.kevinpina.util.SQLUtilsCategoriaProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioImpl implements Repositorio<Categoria> {

	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	private Connection connection;

	public CategoriaRepositorioImpl() {
	}

	public CategoriaRepositorioImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Categoria> findAll() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(SQLUtilsCategoriaProperties.SELECT_CATEGORIA)) {
			while (rs.next()) {
				categorias.add(new Categoria(rs.getInt(ID), rs.getString(NOMBRE)));
			}
		}
		return categorias;
	}

	@Override
	public Categoria findById(Integer id) throws SQLException {
		try (PreparedStatement pstmt = connection.prepareStatement(SQLUtilsCategoriaProperties.SELECT_CATEGORIABY_ID)) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new Categoria(rs.getInt(ID), rs.getString(NOMBRE));
				}
			}
		}
		return null;
	}

	@Override
	public Categoria save(Categoria categoria) throws SQLException {
		String sql = null;
		boolean isNullId = categoria.getId() < 0;

		if (isNullId) {
			sql = SQLUtilsCategoriaProperties.INSERT_CATEGORIA;
		} else {
			sql = SQLUtilsCategoriaProperties.UPDATE_CATEGORIA;
		}
		
		// Validar si idCategoria es diferente de null pero no existe, ¿Se actualizara?

		try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, categoria.getNombre());

			if (!isNullId) {
				pstmt.setInt(2, categoria.getId());
			}

			pstmt.executeUpdate();

			if (isNullId) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						categoria.setId(rs.getInt(1));
					}
				}
			}
		}
		return categoria;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		try (PreparedStatement pstmt = connection.prepareStatement(SQLUtilsCategoriaProperties.DELETE_CATEGORIA)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
