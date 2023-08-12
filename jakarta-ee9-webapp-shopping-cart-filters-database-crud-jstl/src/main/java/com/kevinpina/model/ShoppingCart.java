package com.kevinpina.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;

@Getter
public class ShoppingCart {

	private List<ItemCart> itemsCart;

	public ShoppingCart() {
		this.itemsCart = new ArrayList<>();
	}

	public void addItemCar(ItemCart itemCart) {
		if (itemsCart.contains(itemCart)) {

			Optional<ItemCart> itemCarOptional = itemsCart.stream().filter(item -> item.equals(itemCart)).findAny();

			if (itemCarOptional.isPresent()) {
				ItemCart existingItemCart = itemCarOptional.get();
				existingItemCart.setQuantity(existingItemCart.getQuantity() + 1);
			}

		} else {
			itemsCart.add(itemCart);
		}
	}

	public Double getTotal() {
		return itemsCart.stream().mapToDouble(ItemCart::getAmount).sum();
	}

}
