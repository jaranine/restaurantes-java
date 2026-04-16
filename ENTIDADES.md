* Restaurante
  * Long id
  * String nombre
  * Double precioMedio
  * Boolean activo
  * TipoComida tipoComida (enum: ITALIANA, ESPAÑOLA, MEXICANA, AMERICANA, JAPONESA)
  * LocalDate fechaInicio
  * Integer numeroEmpleados

* Empleado
  * Long id
  * String nombre
  * String apellido
  * String dni
  * Integer edad
  * Restaurante restaurante (ManyToOne)

* Plato
  * Long id
  * String nombre
  * String descripcion
  * Double precio
  * TipoPlato tipoPlato (enum: ENTRANTES, PRIMER_PLATO, POSTRE)
  * Alergeno alergeno (enum: GLUTEN, LACTEOS, FRUTOS_SECOS, MARISCO, HUEVO, SOJA)
  * Asociación:
    * Restaurante restaurante (ManyToOne)

* Pedido
  * Long id
  * LocalDateTime fecha
  * Integer numeroMesa
  * Integer numeroComensales
  * Double precioTotal
  * Double propina
  * EstadoPedido estadoPedido (enum: PENDIENTE, EN_PREPARACION, SERVIDO, CANCELADO)
  * asociaciones:
    * Restaurante restaurante (ManyToOne)

* LineaPedido
  * Long Id
  * Integer cantidad
  * asociaciones:
    * Plato plato (ManyToOne)
    * Pedido pedido (ManyToOne)

* Resena
  * Long id
  * String comentario
  * Integer puntuacion
  * LocalDate fecha
  * asociaciones:
    * Usuario usuario (ManyToOne)
    * Restaurante restaurante (ManyToOne)