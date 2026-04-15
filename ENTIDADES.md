* Restaurant
  * Long id
  * String nombre
  * Double precioMedio
  * Boolean active
  * TipoComida categoría (enum)
  * LocalDate starDate
  * Integer numeroEmpleados

* Empleado
  * Long id
  * String nombre
  * String apellido
  * String dni
  * Integer age
  * Restaurant restaurant (ManyToOne)

* TipoComida (enum)
  * ITALIANA
  * ESPAÑOLA
  * MEXICANA
  * AMERICANA
  * JAPONESA

* Plato
  * Long id
  * String nombre
  * String descripcion
  * Double precio
  * TipoPlato tipo (enum: STARTER, MAIN_COURSE, DESSERT)
  * Asociación:
    * Restaurante restaurante (ManyToOne)

* Order (pedido)
  * fecha
  * precioTotal
  * numeroComensales
  * tip comida
  * estado (enum: PENDIENTE, EN_PREPARACION, SERVIDO, CANCELADO)
  * tipo: enum (a domicilio, para llevar, en restaurante)
  * asociaciones:
    * user (ManyToOne)
    * Restaurante (ManyToOne)
    * List<plato> platos (ManyToMany)

* Review
  * Long id
  * String comentario
  * Integer puntuacion
  * LocalDate fecha
  * asociaciones:
    * user (ManyToOne)
    * Restaurante (ManyToOne)