package com.kevinpina.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data /*
		 * @Data contains @RequiredArgsConstructor but for specific Constructor must be
		 * specified down below
		 */
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor /*
							 * @RequiredArgsConstructor and @NonNull are two important keys to solve the
							 * problem: How can I specify a one-argument constructor using Lombok?.
							 */
public class Producto {

	@NonNull
	private Integer id;

	@NonNull
	private String nombre;

	@NonNull
	private Float precio;

	@NonNull
	private Date fecha;

	private Categoria categoria;

}
