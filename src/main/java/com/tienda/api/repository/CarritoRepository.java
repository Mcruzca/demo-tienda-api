package com.tienda.api.repository;

import com.tienda.api.model.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;

// Solo acceso a la tabla carrito. Spring escribe el código: findAll, findById, save, deleteAll…
public interface CarritoRepository extends JpaRepository<ItemCarrito, Long> {}
