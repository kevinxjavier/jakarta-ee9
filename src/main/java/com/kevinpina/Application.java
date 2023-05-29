package com.kevinpina;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kevinpina.util.MySQLConnection;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kevinpina.model.Categoria;
import com.kevinpina.model.Producto;
import com.kevinpina.repository.CatagoriaRepositorioImpl;
import com.kevinpina.repository.ProductoRepositorioImpl;
import com.kevinpina.repository.Repositorio;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws SQLException {
//		SpringApplication.run(Application.class, args);

//		try (Connection conn = MySQLConnection.getConnection()) {
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(SQLUtils.SELECT_PRODUCT);
//			while(rs.next()) {
//				System.out.print("Id: {" + rs.getInt("id") + "} | ");
//				System.out.print("Nombre: {" + rs.getString("nombre") + "} | ");
//				System.out.print("Precio: {" + rs.getFloat("precio") + "} | ");
//				System.out.println("Fecha: {" + rs.getDate("fecha") + "} | ");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		try (Connection conn = MySQLConnection.getConnection()) { // Inicializamos Connection en la variable "conn" para
			// asi cerrar la coneccion cuando salga del
			// try-catch

			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}

			try {
				Repositorio<Producto> repositorioProducto = new ProductoRepositorioImpl(conn);

				System.out.println("1 - [Producto] - Save without sku -----------------------------------");

				repositorioProducto.save(new Producto(3, "Betamax GF140X", 99.9F,
						new SimpleDateFormat("yyyy-MM-dd").parse("1987-10-03"), new Categoria(3), null));

				repositorioProducto.findAll().forEach(System.out::println);

				System.out.println("\n2 - [Producto] - Save without category and sku-----------------------");

				repositorioProducto.save(new Producto(3, "VHS 5000", 147.9F, new Date()));

				repositorioProducto.findAll().forEach(System.out::println);

				System.out.println("\n3 - [Producto] - Save all--------------------------------------------");

				repositorioProducto.save(new Producto(3, "Walkman Sony 2023", 19.9F, new Date(), new Categoria(2), "abcde12346"));

				repositorioProducto.findAll().forEach(System.out::println);

				System.out.println("\n4 - [Producto] - Delete record 3-------------------------------------");

				repositorioProducto.delete(3);

				System.out.println(repositorioProducto.findById(2));
				
				
				Repositorio<Categoria> repositorioCategoria = new CatagoriaRepositorioImpl(conn);
				
				System.out.println("\n5 - [Categoria] - Save new -------------------------------------");
				
				Categoria categoria = new Categoria(-1, "Electrohogar");
				Categoria categoriaSaved = repositorioCategoria.save(categoria);
				
				repositorioProducto.save(new Producto(3, "Faxcom T1000", 77.9F,
						new SimpleDateFormat("yyyy-MM-dd").parse("1994-01-03"), categoriaSaved, "abcde12345")); // no pueden haber 2 sku iguales abcde12345
				repositorioProducto.findAll().forEach(System.out::println);
				
				conn.commit();
			} catch (SQLException | ParseException e) {
				conn.rollback();
				e.printStackTrace();
			}
		}
	}

}
