package com.kevinpina.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {

	List<T> findAll() throws SQLException;

	T findById(Integer id) throws SQLException;

	void save(T t) throws SQLException;

	void delete(Integer id) throws SQLException;

}
