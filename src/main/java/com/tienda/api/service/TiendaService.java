package com.tienda.api.service;

import com.tienda.api.model.ItemCarrito;
import com.tienda.api.model.Pedido;
import com.tienda.api.model.Producto;
import com.tienda.api.repository.CarritoRepository;
import com.tienda.api.repository.PedidoRepository;
import com.tienda.api.repository.ProductoRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// LA LÓGICA (reglas del negocio). No sabe nada de HTTP: eso es del controller.
// Para leer y guardar usa los repositories.
@Service
public class TiendaService {

	private final ProductoRepository productos;
	private final CarritoRepository carrito;
	private final PedidoRepository pedidos;

	public TiendaService(ProductoRepository productos, CarritoRepository carrito, PedidoRepository pedidos) {
		this.productos = productos;
		this.carrito = carrito;
		this.pedidos = pedidos;
	}

	public List<Producto> listarProductos() {
		return productos.findAll();
	}

	public List<ItemCarrito> verCarrito() {
		return carrito.findAll(Sort.by("nombre"));
	}

	// Regla: si el producto ya está en el carrito, suma 1; si no, lo agrega.
	public List<ItemCarrito> agregarAlCarrito(Long id, String nombre, Double precio) {
		if (id == null || nombre == null || precio == null || precio <= 0) {
			throw new IllegalArgumentException("Faltan id, nombre o precio");
		}
		// En producción el precio se tomaría de la tabla producto, no de lo que manda el navegador.
		ItemCarrito item = carrito.findById(id).orElse(null);
		if (item == null) {
			item = new ItemCarrito(id, nombre, precio);
		} else {
			item.cantidad++;
		}
		carrito.save(item);
		return verCarrito();
	}

	public List<ItemCarrito> vaciarCarrito() {
		carrito.deleteAll();
		return verCarrito();
	}

	public List<Pedido> listarPedidos() {
		return pedidos.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	// Regla: no se puede comprar con el carrito vacío. El pedido guarda el resumen y el total.
	@Transactional // Guardar el pedido y vaciar el carrito: o se hacen las dos cosas o ninguna.
	public List<ItemCarrito> finalizarCompra() {
		List<ItemCarrito> items = verCarrito();
		if (items.isEmpty()) {
			throw new IllegalArgumentException("El carrito está vacío");
		}
		String resumen = String.join(", ", items.stream().map(i -> i.cantidad + " × " + i.nombre).toList());
		double total = items.stream().mapToDouble(i -> i.precio * i.cantidad).sum();
		pedidos.save(new Pedido(resumen, total));
		carrito.deleteAll();
		return verCarrito(); // El carrito, ya vacío.
	}
}
