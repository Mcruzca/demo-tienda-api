package com.tienda.api;

import com.tienda.api.model.Producto;
import com.tienda.api.repository.ProductoRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiJavaApplication.class, args);
	}

	// Productos de ejemplo la primera vez que arranca.
	@Bean
	CommandLineRunner productosDeEjemplo(ProductoRepository productos) {
		return args -> {
			if (productos.count() == 0) {
				productos.saveAll(List.of(
						new Producto("Polo", 35),
						new Producto("Gorra", 25),
						new Producto("Taza", 18),
						new Producto("Mochila", 89)));
			}
		};
	}

}
