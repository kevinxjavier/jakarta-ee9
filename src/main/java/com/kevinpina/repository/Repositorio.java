package com.kevinpina.repository;

import java.util.List;

public interface Repositorio<T> {

	List<T> findAll();

	T findById(Integer id);

	void save(T t);

	void delete(Integer id);

}
