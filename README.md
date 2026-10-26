# API de la tienda · Java (Spring Boot)
API que usan todos los módulos de la tienda: Java 21 + Spring Boot + base de datos H2.
Puerto 4510.

## Ejecutar
- **IntelliJ IDEA:** abre esta carpeta y ejecuta `ApiJavaApplication` (botón ▶ junto a `main`).
- **Terminal:** `mvnw spring-boot:run`

La primera vez crea `data/tienda.mv.db` con 4 productos de ejemplo.

## Rutas
- GET http://localhost:4510/productos
- GET http://localhost:4510/carrito · POST /carrito · DELETE /carrito
- GET http://localhost:4510/pedidos · POST /pedidos

## Ver la base de datos
http://localhost:4510/h2-console → JDBC URL `jdbc:h2:file:./data/tienda`, usuario `sa`, sin contraseña.

## Capas (`src/main/java/com/tienda/api`)
| Carpeta | Qué hace |
|---|---|
| `controller/TiendaController` | Recibe las peticiones HTTP, llama al service y responde. No decide nada |
| `service/TiendaService` | La lógica: sumar al carrito, no comprar con el carrito vacío, calcular el total |
| `repository/*Repository` | Solo acceso a la base de datos (Spring escribe el código) |
| `model/Producto`, `ItemCarrito`, `Pedido` | Las entidades: cada una es una tabla (`@Entity`) |

Flujo: `Controller → Service → Repository → base de datos H2`.
