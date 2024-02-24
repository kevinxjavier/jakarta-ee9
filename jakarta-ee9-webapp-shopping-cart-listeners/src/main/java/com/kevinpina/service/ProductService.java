package com.kevinpina.service;

import java.util.List;
import java.util.Optional;

import com.kevinpina.model.Product;

public interface ProductService {

	List<Product> list();
	Optional<Product> findById(Integer id);

}
