package com.tienda.api.controller;

import com.tienda.api.model.ItemCarrito;
import com.tienda.api.model.Pedido;
import com.tienda.api.model.Producto;
import com.tienda.api.service.TiendaService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// LAS PETICIONES: recibe la petición HTTP, llama al service y responde. No decide nada.
// Las mismas seis rutas que la versión en Node, para que los módulos de la tienda no cambien.
//    GET    /productos → la lista de productos (módulo compras)
//    GET    /carrito   → lo que hay en el carrito (carrito y contador)
//    POST   /carrito   → agrega un producto { id, nombre, precio } (carrito)
//    DELETE /carrito   → vacía el carrito (carrito)
//    POST   /pedidos   → convierte el carrito en un pedido y lo vacía (carrito)
//    GET    /pedidos   → historial de pedidos (módulo pedidos)
@RestController
@CrossOrigin(origins = "*") // Desarrollo local: cada módulo está en otro puerto.
public class TiendaController {

	private final TiendaService tienda;

	public TiendaController(TiendaService tienda) {
		this.tienda = tienda;
	}

	// Lo que envía el navegador al agregar.
	record NuevoItem(Long id, String nombre, Double precio) {}

	@GetMapping("/productos")
	public List<Producto> listarProductos() {
		return tienda.listarProductos();
	}

	@GetMapping("/carrito")
	public List<ItemCarrito> verCarrito() {
		return tienda.verCarrito();
	}

	@PostMapping("/carrito")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ItemCarrito> agregar(@RequestBody NuevoItem nuevo) {
		return tienda.agregarAlCarrito(nuevo.id(), nuevo.nombre(), nuevo.precio());
	}

	@DeleteMapping("/carrito")
	public List<ItemCarrito> vaciar() {
		return tienda.vaciarCarrito();
	}

	@GetMapping("/pedidos")
	public List<Pedido> listarPedidos() {
		return tienda.listarPedidos();
	}

	@PostMapping("/pedidos")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ItemCarrito> finalizarCompra() {
		return tienda.finalizarCompra();
	}

	// Si el service rechaza algo, responde 400 con { "error": "..." }.
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> datoInvalido(IllegalArgumentException e) {
		return Map.of("error", e.getMessage());
	}
}
