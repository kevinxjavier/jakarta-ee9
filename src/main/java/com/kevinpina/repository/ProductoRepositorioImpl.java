package com.kevinpina.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kevinpina.util.MySQLConnection;
import com.kevinpina.model.Categoria;
import com.kevinpina.model.Producto;
import com.kevinpina.util.SQLUtilsProductosProperties;

public class ProductoRepositorioImpl implements Repositorio<Producto> {

	private Connection getConnection() throws SQLException {
		return MySQLConnection.getConnection();
	}

	@Override
	public List<Producto> findAll() {
		List<Producto> productos = new ArrayList<>();

		try (Statement stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(SQLUtilsProductosProperties.SELECT_PRODUCT)) {
			while (rs.next()) {
				productos.add(new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("precio"),
						rs.getDate("fecha"),
						new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productos;
	}

	@Override
	public Producto findById(Integer id) {
		Producto producto = null;

		try (PreparedStatement pstmt = getConnection().prepareStatement(SQLUtilsProductosProperties.SELECT_PRODUCT_BY_ID)) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				producto = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("precio"),
						rs.getDate("fecha"),
						new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria")));
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return producto;
	}

	@Override
	public void save(Producto producto) {
		String sql;
		boolean existsProducto = false;
		boolean isNullCategory = producto.getCategoria() == null || producto.getCategoria().getId() == null;
		boolean isNullId = producto.getId() == null;

		if (!isNullId) {
			existsProducto = findById(producto.getId()) != null;
		}

		if (existsProducto) {
			sql = SQLUtilsProductosProperties.UPDATE_PRODUCT_WITHOUT_CATEGORY;
			if (!isNullCategory) {
				sql = SQLUtilsProductosProperties.UPDATE_PRODUCT;
			}
		} else {
			if (!isNullId && !isNullCategory) {
				sql = SQLUtilsProductosProperties.INSERT_PRODUCT;
			} else if (!isNullCategory) {
				sql = SQLUtilsProductosProperties.INSERT_PRODUCT_WITHOUT_ID;
			} else if (!isNullId) {
				sql = SQLUtilsProductosProperties.INSERT_PRODUCT_WITHOUT_CATEGORY;
			} else {
				sql = SQLUtilsProductosProperties.INSERT_PRODUCT_WITHOUT_ID_AND_CATEGORY;
			}
		}

		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {

			int atPosition = 1;

			if (existsProducto) {

				pstmt.setString(atPosition++, producto.getNombre());
				pstmt.setFloat(atPosition++, producto.getPrecio());
				pstmt.setDate(atPosition++, new Date(producto.getFecha().getTime()));

				if (!isNullCategory) {
					pstmt.setInt(atPosition++, producto.getCategoria().getId());
				}

				pstmt.setInt(atPosition, producto.getId());

			} else {

				if (!isNullId) {
					pstmt.setInt(atPosition++, producto.getId());
				}

				pstmt.setString(atPosition++, producto.getNombre());
				pstmt.setFloat(atPosition++, producto.getPrecio());
				pstmt.setDate(atPosition++, new Date(producto.getFecha().getTime()));

				if (!isNullCategory) {
					pstmt.setInt(atPosition++, producto.getCategoria().getId());
				}

			}
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer id) {
		try (PreparedStatement pstmt = getConnection().prepareStatement(SQLUtilsProductosProperties.DELETE_PRODUCT_BY_ID)) {
			pstmt.setInt(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
