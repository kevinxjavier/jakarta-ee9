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
import com.kevinpina.repository.ProductoRepositorioImpl;
import com.kevinpina.repository.Repositorio;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
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

			Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

			repositorio.save(new Producto(3, "Betamax GF140X", 99.9F,
					new SimpleDateFormat("yyyy-MM-dd").parse("1987-10-03"), new Categoria(3)));

			repositorio.findAll().forEach(System.out::println);
			System.out.println("--------------------------");

			repositorio.save(new Producto(3, "VHS 5000", 147.9F, new Date()));

			repositorio.findAll().forEach(System.out::println);
			System.out.println("--------------------------");

			repositorio.save(new Producto(3, "Walkman Sony 2023", 19.9F, new Date(), new Categoria(2)));

			repositorio.findAll().forEach(System.out::println);
			System.out.println("--------------------------");

			repositorio.delete(3);

			Producto producto = repositorio.findById(2);
			System.out.println(producto);
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}

	}

}
