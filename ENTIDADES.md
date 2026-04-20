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

Pasos para empezar a crear controllers:
* Crear paquete nuevo llamado controller en com.restaurantes

* HolaController (opcional)
    * Crear una clase java nueva llamada HolaController
    * Anotar la clase con @Controller
    * Crear un método público llamado hola que reciba un Model y devuelva String
    * Anotar el método con @GetMapping ("/hola")
    * Dentro del método, guardar un mensaje en el Model con model.attribute ("message", "Hola, mundo")
    * Devolver el nombre del template HTML que vamos a crear: "hola"

* Crear una clase java nueva llamada RestauranteController
    * Anotar clase con @Controller
    * Crear un constructor que reciba RestauranteRepository y lo guarde en un atributo privado final
    * Crear un método público llamado findAll que reciba un Model y devuelva String
    * Anotar el método con @GetMapping("/restaurantes")
    * Dentro del método, usar restauranteRepository.findAll() para obtener la lista de restaurantes y guardarla en el Model con model.addAttribute("restaurantes", restauranteRepository.findAll())
    * Devolver el nombre del template HTML que vamos a crear: "restaurante-lista"

* Resena
  * Long id
  * String comentario
  * Integer puntuacion
  * LocalDate fecha
  * asociaciones:
    * Usuario usuario (ManyToOne)
    * Restaurante restaurante (ManyToOne)