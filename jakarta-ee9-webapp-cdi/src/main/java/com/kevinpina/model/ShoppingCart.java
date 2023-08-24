package com.kevinpina.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;

@SessionScoped // Always must be a default Constructor in CDI. we use @Inject if there is a
				// parametric Construtor and also implement Serializable.
@Named("shoppingCart") // In CDI by default the name is cammel case so it not necessary define this:
						// @Named("shoppingCart").
@Getter
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = -5036215984875903085L;

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
