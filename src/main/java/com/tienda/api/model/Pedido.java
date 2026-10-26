package com.tienda.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Tabla "pedido": una compra finalizada, con el resumen de productos y el total.
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String productos;
	public double total;
	public String fecha;

	protected Pedido() {} // JPA lo necesita.

	public Pedido(String productos, double total) {
		this.productos = productos;
		this.total = total;
		this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
