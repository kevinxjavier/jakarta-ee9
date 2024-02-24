package com.kevinpina.repository;

import com.kevinpina.model.Categoria;
import com.kevinpina.model.Producto;
import com.kevinpina.util.SQLUtilsProductoProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {

	public static final String NOMBRE = "nombre";
	public static final String ID_CATEGORIA = "id_categoria";
	public static final String PRECIO = "precio";
	public static final String FECHA = "fecha";
	public static final String NOMBRE_CATEGORIA = "nombre_categoria";
	public static final String SKU = "sku";
	public static final String ID = "id";
	private Connection connection;

	public ProductoRepositorioImpl() {
	}

	public ProductoRepositorioImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Producto> findAll() throws SQLException {
		List<Producto> productos = new ArrayList<>();

		try (Statement stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(SQLUtilsProductoProperties.SELECT_PRODUCT)) {
			while (rs.next()) {
				productos.add(new Producto(rs.getInt(ID), rs.getString(NOMBRE), rs.getFloat(PRECIO),
						rs.getDate(FECHA),
						new Categoria(rs.getInt(ID_CATEGORIA), rs.getString(NOMBRE_CATEGORIA)), rs.getString(SKU)));
			}
		}

		return productos;
	}

	@Override
	public Producto findById(Integer id) throws SQLException {
		Producto producto = null;

		try (PreparedStatement pstmt = connection.prepareStatement(SQLUtilsProductoProperties.SELECT_PRODUCT_BY_ID)) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				producto = new Producto(rs.getInt(ID), rs.getString(NOMBRE), rs.getFloat(PRECIO),
						rs.getDate(FECHA),
						new Categoria(rs.getInt(ID_CATEGORIA), rs.getString(NOMBRE_CATEGORIA)), rs.getString(SKU));
			}

			rs.close();

		}

		return producto;
	}

	@Override
	public Producto save(Producto producto) throws SQLException {
		String sql;
		boolean existsProducto = false;
		boolean isNullCategory = producto.getCategoria() == null || producto.getCategoria().getId() == null || producto.getCategoria().getId() < 0;
		boolean isNullSku = producto.getSku() == null;
		boolean isNullId = producto.getId() == null;

		if (!isNullId) {
			existsProducto = findById(producto.getId()) != null;
		}

		if (existsProducto) {
			if (!isNullCategory && !isNullSku) {
				sql = SQLUtilsProductoProperties.UPDATE_PRODUCT;
			} else if (!isNullCategory) {
				sql = SQLUtilsProductoProperties.UPDATE_PRODUCT_WITHOUT_CATEGORY;
			} else if (!isNullSku) {
				sql = SQLUtilsProductoProperties.UPDATE_PRODUCT_WITHOUT_SKU;
			} else {
				sql = SQLUtilsProductoProperties.UPDATE_PRODUCT_WITHOUT_CATEGORY_AND_SKU;
			}
		} else {
			if (!isNullId && !isNullCategory && !isNullSku) {
				sql = SQLUtilsProductoProperties.INSERT_PRODUCT;
			} else if (!isNullCategory && !isNullSku) {
				sql = SQLUtilsProductoProperties.INSERT_PRODUCT_WITHOUT_ID;
			} else if (!isNullCategory && !isNullId) {
				sql = SQLUtilsProductoProperties.INSERT_PRODUCT_WITHOUT_SKU;
			} else if (!isNullId && !isNullSku) {
				sql = SQLUtilsProductoProperties.INSERT_PRODUCT_WITHOUT_CATEGORY;
			} else if (!isNullCategory) {
				sql = SQLUtilsProductoProperties.INSERT_PRODUCT_WITHOUT_ID_AND_SKU;
			} else if (!isNullId) {
				sql = SQLUtilsProductoProperties.INSERT_PRODUCT_WITHOUT_CATEGORY_AND_SKU;
			} else if (!isNullSku) {
				sql = SQLUtilsProductoProperties.INSERT_PRODUCT_WITHOUT_ID_AND_CATEGORY;
			} else {
				sql = SQLUtilsProductoProperties.INSERT_PRODUCT_WITHOUT_ID_AND_CATEGORY_AND_SKU;
			}
		}

		try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			int atPosition = 1;

			if (existsProducto) {

				pstmt.setString(atPosition++, producto.getNombre());
				pstmt.setFloat(atPosition++, producto.getPrecio());
				pstmt.setDate(atPosition++, new Date(producto.getFecha().getTime()));

				if (!isNullCategory) {
					pstmt.setInt(atPosition++, producto.getCategoria().getId());
				}

				if (!isNullSku) {
					pstmt.setString(atPosition++, producto.getSku());
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

				if (!isNullSku) {
					pstmt.setString(atPosition++, producto.getSku());
				}

			}

			pstmt.executeUpdate();

			if (producto.getId() == null) { // "SELECT LAST_INSERT_ID() AS id"
				try(ResultSet rs = pstmt.getGeneratedKeys()) {
					if(rs.next()) {
						producto.setId(Integer.parseInt(String.valueOf(rs.getLong(1))));
					}
				}
			}
		}
		return producto;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		try (PreparedStatement pstmt = connection.prepareStatement(SQLUtilsProductoProperties.DELETE_PRODUCT_BY_ID)) {
			pstmt.setInt(1, id);
			pstmt.execute();
		}
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
