package com.kevinpina.database.queries;

public enum ProductQuerySQL {

	SELECT_ALL(
			"SELECT p.*, c.name AS category_name FROM product AS p INNER JOIN category AS c ON p.category_id = c.id"),
	SELECT_BY_ID(
			"SELECT p.*, c.name AS category_name FROM product AS p INNER JOIN category AS c ON p.category_id = c.id WHERE p.id = ?"),
	INSERT("INSERT INTO products (name, price, sku, category_id, date) VALUES (?, ?, ?, ?, ?)"),
	UPDATE("UPDATE product SET name = ?, price = ?, sku = ?, category_id = ? WHERE id = ?"),
	DELETE("DELETE FROM product WHERE id = ?");

	private String sql;

	ProductQuerySQL(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

}
