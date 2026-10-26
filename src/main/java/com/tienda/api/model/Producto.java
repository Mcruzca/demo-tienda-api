package com.tienda.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Tabla "producto": la lista de productos que muestra el módulo compras.
@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String nombre;
	public double precio;

	protected Producto() {} // JPA lo necesita.

	public Producto(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
}
