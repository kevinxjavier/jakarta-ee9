package com.kevinpina.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private Long id;
	private String name;
	private Double price;
	private Category category;
	private String sku;
	private LocalDate date;

	/**
	 * Usage: Product p = Product.newProduct().name("kevin").id(1L).build();
	 * 
	 * @param id
	 * @param name
	 * @return Product
	 */
	@Builder(builderMethodName = "newProduct")
	public static Product getObject(Long id, String name) {
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		return product;
	}

}
