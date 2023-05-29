package com.kevinpina.util;

public abstract class SQLUtilsProductosProperties {

    public static final String SELECT = "p.id, p.nombre, p.precio, p.fecha, c.id AS id_categoria, c.nombre AS nombre_categoria, p.sku FROM productos AS p INNER JOIN categorias AS c ON p.categoria_id = c.id";
	public static final String INSERT = "INSERT INTO productos (";
	public static final String VALUES = ") VALUES (";

    public static final String SELECT_PRODUCT = "SELECT " + SELECT;
    public static final String SELECT_PRODUCT_BY_ID = "SELECT " + SELECT + " WHERE p.id = ?";
    public static final String DELETE_PRODUCT_BY_ID = "DELETE FROM productos WHERE id = ?";
    public static final String INSERT_PRODUCT = INSERT + "id, nombre, precio, fecha, categoria_id, sku" + VALUES + " ?, ?, ?, ?, ?, ?)";
   	public static final String INSERT_PRODUCT_WITHOUT_ID = INSERT + "nombre, precio, fecha, categoria_id, sku" + VALUES + "?, ?, ?, ?, ?)";
	public static final String INSERT_PRODUCT_WITHOUT_SKU = INSERT + "id, nombre, precio, fecha, categoria_id" + VALUES + "?, ?, ?, ?, ?)";
    public static final String INSERT_PRODUCT_WITHOUT_CATEGORY = INSERT + "id, nombre, precio, fecha, sku" + VALUES + "?, ?, ?, ?, ?)";
    public static final String INSERT_PRODUCT_WITHOUT_CATEGORY_AND_SKU = INSERT + "id, nombre, precio, fecha" + VALUES + "?, ?, ?, ?)";
	public static final String INSERT_PRODUCT_WITHOUT_ID_AND_CATEGORY = INSERT + "nombre, precio, fecha, sku" + VALUES + "?, ?, ?, ?)";
	public static final String INSERT_PRODUCT_WITHOUT_ID_AND_SKU = INSERT + "nombre, precio, fecha, categoria_id" + VALUES + "?, ?, ?, ?)";
    public static final String INSERT_PRODUCT_WITHOUT_ID_AND_CATEGORY_AND_SKU = INSERT + "nombre, precio, fecha" + VALUES + "?, ?, ?)";
    public static final String UPDATE_PRODUCT = "UPDATE productos SET nombre = ?, precio = ?, fecha = ?, categoria_id = ?, sku = ? WHERE id = ?";
    public static final String UPDATE_PRODUCT_WITHOUT_CATEGORY = "UPDATE productos SET nombre = ?, precio = ?, fecha = ?, categoria_id = ? WHERE id = ?";
	public static final String UPDATE_PRODUCT_WITHOUT_SKU = "UPDATE productos SET nombre = ?, precio = ?, fecha = ?, sku = ? WHERE id = ?";

	public static final String UPDATE_PRODUCT_WITHOUT_CATEGORY_AND_SKU = "UPDATE productos SET nombre = ?, precio = ?, fecha = ? WHERE id = ?";

}
