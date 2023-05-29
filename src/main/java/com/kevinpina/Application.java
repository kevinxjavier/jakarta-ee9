package com.kevinpina;

import com.kevinpina.model.Categoria;
import com.kevinpina.model.Producto;
import com.kevinpina.repository.ProductoRepositorioImpl;
import com.kevinpina.repository.Repositorio;
import com.kevinpina.util.MySQLConnection;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

                Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

                System.out.println("1 Save without sku -----------------------------------");

                repositorio.save(new Producto(3, "Betamax GF140X", 99.9F,
                        new SimpleDateFormat("yyyy-MM-dd").parse("1987-10-03"), new Categoria(3), null));

                repositorio.findAll().forEach(System.out::println);

                System.out.println("2 Save without category and sku-----------------------");

                repositorio.save(new Producto(3, "VHS 5000", 147.9F, new Date()));

                repositorio.findAll().forEach(System.out::println);

                System.out.println("3 Save all--------------------------------------------");

                repositorio.save(new Producto(3, "Walkman Sony 2023", 19.9F, new Date(), new Categoria(2), "abcde12346"));

                repositorio.findAll().forEach(System.out::println);

                System.out.println("4 Delete record 3-------------------------------------");

                repositorio.delete(3);

                System.out.println(repositorio.findById(2));

                conn.commit();
            } catch (SQLException | ParseException e) {
                conn.rollback();
                e.printStackTrace();
            }

        }

    }

}
