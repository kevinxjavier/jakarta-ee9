package com.kevinpina.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	private Long id;
	private String name;
	private Float price;
	private String categoryName;
	
	/**
	 * Usage: Product p = Product.newProduct().name("kevin").id(1L).build();
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
