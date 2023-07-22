package com.kevinpina.database.fields;

public enum CategoryFieldSQL {

	CATEGORY_ID("category_id"), CATEGORY_NAME("category_name");

	private String field;

	CategoryFieldSQL(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}
}
