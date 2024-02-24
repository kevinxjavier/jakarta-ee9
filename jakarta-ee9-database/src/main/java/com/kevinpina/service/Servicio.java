package com.kevinpina.service;

import java.sql.SQLException;
import java.util.List;

import com.kevinpina.model.Categoria;
import com.kevinpina.model.Producto;

public interface Servicio<T> {

	List<T> findAll() throws SQLException;

	T findById(Integer id) throws SQLException;

	T save(T t) throws SQLException;

	void delete(Integer id) throws SQLException;
	
	Producto save(Producto producto, Categoria categoria) throws SQLException;

}
