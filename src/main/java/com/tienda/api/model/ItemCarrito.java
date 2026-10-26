package com.tienda.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Tabla "carrito": un producto y cuántas veces se agregó. El id es el del producto.
@Entity
@Table(name = "carrito")
public class ItemCarrito {

	@Id
	public Long id;
	public String nombre;
	public double precio;
	public int cantidad = 1;

	protected ItemCarrito() {} // JPA lo necesita.

	public ItemCarrito(Long id, String nombre, double precio) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}
}
