package com.kevinpina;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kevinpina.model.Categoria;
import com.kevinpina.model.Producto;
import com.kevinpina.service.CategoriaServicioImpl;
import com.kevinpina.service.ProductoServicioImpl;
import com.kevinpina.service.Servicio;

@SpringBootApplication
public class ApplicationServicio {

	public static void main(String[] args) throws ParseException, SQLException {
		SpringApplication.run(ApplicationServicio.class, args);

		System.out.println("1 - [Producto] - Save without sku -----------------------------------");

		Servicio<Producto> servicioProducto = new ProductoServicioImpl();

		servicioProducto.save(new Producto(3, "Betamax GF140X", 99.9F, new SimpleDateFormat("yyyy-MM-dd").parse("1987-10-03"),
				new Categoria(3), null));

		servicioProducto.findAll().forEach(System.out::println);

		System.out.println("\n2 - [Producto] - Save without category and sku-----------------------");

		servicioProducto.save(new Producto(3, "VHS 5000", 147.9F, new Date()));

		servicioProducto.findAll().forEach(System.out::println);

		System.out.println("\n3 - [Producto] - Save all--------------------------------------------");

		servicioProducto.save(new Producto(3, "Walkman Sony 2023", 19.9F, new Date(), new Categoria(2), "abcde12346"));

		servicioProducto.findAll().forEach(System.out::println);

		System.out.println("\n4 - [Producto] - Delete record 3-------------------------------------");

		servicioProducto.delete(3);

		System.out.println(servicioProducto.findById(2));
		
		
		Servicio<Categoria> servicioCategoria = new CategoriaServicioImpl();

		System.out.println("\n5 - [Categoria] - Save new -------------------------------------");

		Categoria categoria = new Categoria(-1, "Electrohogar");
//		Categoria categoriaSaved = servicioCategoria.save(categoria); //Commented to force servicioProducto.save() save the new Catergoria if nothing goes wrong

		servicioProducto.save(new Producto(3, "Faxcom T1000", 77.9F,
				new SimpleDateFormat("yyyy-MM-dd").parse("1994-01-03"), categoria, "abcde12345")); // no pueden haber 2 sku iguales abcde12345
		servicioCategoria.findAll().forEach(System.out::println); // Will not save Categoria by sku duplicated 

	}

}
