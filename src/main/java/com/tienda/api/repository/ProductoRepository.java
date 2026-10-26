package com.tienda.api.repository;

import com.tienda.api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

// Solo acceso a la tabla producto. Spring escribe el código: findAll, save, count…
public interface ProductoRepository extends JpaRepository<Producto, Long> {}
