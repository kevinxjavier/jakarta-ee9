package com.kevinpina.repository;

import com.kevinpina.model.Categoria;
import com.kevinpina.model.Producto;
import com.kevinpina.util.MySQLConnection;
import com.kevinpina.util.SQLUtilsProductosProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {

    private Connection getConnection() throws SQLException {
        return MySQLConnection.getConnection();
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(SQLUtilsProductosProperties.SELECT_PRODUCT)) {
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("precio"),
                        rs.getDate("fecha"),
                        new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria")), rs.getString("sku")));
            }
        }

        return productos;
    }

    @Override
    public Producto findById(Integer id) throws SQLException {
        Producto producto = null;

        try (PreparedStatement pstmt = getConnection().prepareStatement(SQLUtilsProductosProperties.SELECT_PRODUCT_BY_ID)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                producto = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("precio"),
                        rs.getDate("fecha"),
                        new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria")), rs.getString("sku"));
            }

            rs.close();

        }

        return producto;
    }

    @Override
    public void save(Producto producto) throws SQLException {
        String sql;
        boolean existsProducto = false;
        boolean isNullCategory = producto.getCategoria() == null || producto.getCategoria().getId() == null;
        boolean isNullSku = producto.getSku() == null;
        boolean isNullId = producto.getId() == null;

        if (!isNullId) {
            existsProducto = findById(producto.getId()) != null;
        }

        if (existsProducto) {
            if (!isNullCategory && !isNullSku) {
                sql = SQLUtilsProductosProperties.UPDATE_PRODUCT;
            } else if (!isNullCategory) {
                sql = SQLUtilsProductosProperties.UPDATE_PRODUCT_WITHOUT_CATEGORY;
            } else if (!isNullSku) {
                sql = SQLUtilsProductosProperties.UPDATE_PRODUCT_WITHOUT_SKU;
            } else {
				sql = SQLUtilsProductosProperties.UPDATE_PRODUCT_WITHOUT_CATEGORY_AND_SKU;
            }
        } else {
            if (!isNullId && !isNullCategory && !isNullSku) {
                sql = SQLUtilsProductosProperties.INSERT_PRODUCT;
            } else if (!isNullCategory && !isNullSku) {
                sql = SQLUtilsProductosProperties.INSERT_PRODUCT_WITHOUT_ID;
            } else if (!isNullCategory && !isNullId) {
                sql = SQLUtilsProductosProperties.INSERT_PRODUCT_WITHOUT_SKU;
            } else if (!isNullId && !isNullSku) {
                sql = SQLUtilsProductosProperties.INSERT_PRODUCT_WITHOUT_CATEGORY;
            } else if (!isNullCategory) {
                sql = SQLUtilsProductosProperties.INSERT_PRODUCT_WITHOUT_ID_AND_SKU;
            } else if (!isNullId) {
                sql = SQLUtilsProductosProperties.INSERT_PRODUCT_WITHOUT_CATEGORY_AND_SKU;
            } else if (!isNullSku) {
                sql = SQLUtilsProductosProperties.INSERT_PRODUCT_WITHOUT_ID_AND_CATEGORY;
            } else {
                sql = SQLUtilsProductosProperties.INSERT_PRODUCT_WITHOUT_ID_AND_CATEGORY_AND_SKU;
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
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement pstmt = getConnection().prepareStatement(SQLUtilsProductosProperties.DELETE_PRODUCT_BY_ID)) {
            pstmt.setInt(1, id);
            pstmt.execute();
        }
    }

}
