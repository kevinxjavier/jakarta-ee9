package com.kevinpina.util;

public class SQLUtilsCategoriaProperties {

    public static final String SELECT = "SELECT * FROM categorias ";
    public static final String WHERE_ID = "WHERE id = ?";
    public static final String SELECT_CATEGORIA = SELECT;
    public static final String SELECT_CATEGORIABY_ID = SELECT + WHERE_ID;
    public static final String UPDATE_CATEGORIA = "UPDATE productos SET nombre = ? " + WHERE_ID;
	public static final String INSERT_CATEGORIA = "INSERT INTO categorias (nombre) VALUES (?)";
	public static final String DELETE_CATEGORIA = "DELETE FROM categorias " + WHERE_ID;

}
