package com.kevinpina.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.kevinpina.model.Product;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> list() {
		return Arrays.asList(new Product(1, "HP Elite Book 4303s", 3203F, "Computer"),
				new Product(2, "Asus Lite GH", 1323F, "Computer"), new Product(3, "Logitec 2200", 50F, "Keyboard"));
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return list().stream().filter(p -> p.getId().longValue() == id.longValue()).findAny();
	}

}
