package com.tienda.api.repository;

import com.tienda.api.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

// Solo acceso a la tabla pedido. Spring escribe el código: findAll, save…
public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
