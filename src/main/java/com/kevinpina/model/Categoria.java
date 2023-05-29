package com.kevinpina.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Categoria {

	@NonNull
	private Integer id;

	private String nombre;

}
