package com.restaurantes;

import com.restaurantes.model.*;
import com.restaurantes.model.enums.*;
import com.restaurantes.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Una clase con @Entity equivale a una tabla de base de datos
// Un objeto equivale a una fila en una tabla de base de datos
@SpringBootApplication
public class RestaurantesJavaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(RestaurantesJavaApplication.class, args);

        // obtener los repositorios para poder hacer operaciones de bases de datos con ellos
        RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
        EmpleadoRepository empleadoRepository = context.getBean(EmpleadoRepository.class);

        // crear un objeto con getter/setter
        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNombre("100 Montaditos");
        restaurante1.setPrecioMedio(10.5);
        restaurante1.setNumeroEmpleados(10);
        restauranteRepository.save(restaurante1); // guardar en base de datos usando el repositorio: .save()

        // crear un objeto con constructor
        Restaurante restaurante2 = new Restaurante(30.50, "DItaly", 20);
        restauranteRepository.save(restaurante2); // guardar en base de datos usando el repositorio: .save()

        // crear un empleado y guardarlo en base de datos
        Empleado empleado1 = new Empleado(27, "12345678A", "Juanez", "Juan", null);
        empleadoRepository.save(empleado1);

        System.out.println(empleado1);

        // obtener todos los restaurantes de la base de datos
        // SELECT * from restaurantes;
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        //System.out.println(restaurantes);

        //for (int i = 0; i < restaurantes.size(); i++) { // size() nos dice el número de elementos en la lista
        //    System.out.println(restaurantes.get(i));
        //}

        for (Restaurante restaurante : restaurantes) { // alternativa foreach
            System.out.println(restaurante);
        }

        List<Empleado> empleados = empleadoRepository.findAll();
        //System.out.println(empleados);

        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }

        Restaurante r1 = new Restaurante(10.0, "R1", 5);
        Restaurante r2 = new Restaurante(15.0, "R2", 7);

        // Opción clásica
        List<Restaurante> sitiosParaComer = new ArrayList<>();
        List<String> alumnos = new ArrayList<>();
        List<Double> precios = new ArrayList<>();

        // Opción moderna
        List<Restaurante> sitiosGuaposParaComer = List.of(r1, r2);
        restauranteRepository.saveAll(sitiosGuaposParaComer);

        // count() para contar cuantas filas hay en la tabla
        long numeroRestaurantes = restauranteRepository.count();
        if (numeroRestaurantes > 0) {
            System.out.println("Hay para comer, tenemos " + numeroRestaurantes + " restaurantes");
        } else {
            System.out.println("No hay para comer");
        }

        // existById boolean
        long id = 1;
        boolean existe = restauranteRepository.existsById(id);
        if (existe) {
            System.out.println("Restaurante 1 sí existe");
        } else {
            System.out.println("Restaurante 1 no existe");
        }
        // restauranteRepository.deleteById(1L);

        // deleteAll borrar todas las filas de la tabla
        // restauranteRepository.deleteAll();

        // deleteById borrar una fila indicando si id
        // restauranteRepository.deleteById(restaurante2.getId());
        restauranteRepository.deleteById(1L);

        // delete borrar pasando el objeto
        restauranteRepository.delete(r2);

        // findById traer restaurante/empleado
        Long idABuscar = 2L;
        Optional<Restaurante> restauranteFromDatabase = restauranteRepository.findById(idABuscar);
        if (restauranteFromDatabase.isPresent()) {
            Restaurante restauranteX = restauranteFromDatabase.get();
            System.out.println(restauranteX);
        }


        // Crear un restaurante español
        Restaurante restEspanol = new Restaurante(20.0, "Casa Pepe", 15);
        // restEspanol.setTipoComida("Español");
        restEspanol.setTipoComida(TipoComida.ESP);
        restauranteRepository.save(restEspanol);
        System.out.println(restEspanol);

        // Crear un restaurante japonés
        Restaurante restJapones = new Restaurante();
        restJapones.setTipoComida(TipoComida.JAP);
        restauranteRepository.save(restJapones);
        System.out.println(restJapones);

        // Probar a intentar otro tipo de comida y ver que no se puede guardar en base de datos
        //Restaurante restIndio = new Restaurante();
        //restIndio.setTipoComida(TipoComida.IND);

        // Probar fecha de startDate del restaurante
        Restaurante restAsturiano = new Restaurante();
        restAsturiano.setNombre("Cachopos");
        restAsturiano.setFechaInicio(LocalDate.now()); // fecha actual
        restauranteRepository.save(restAsturiano);
        System.out.println(restAsturiano);

        // Fecha futura
        Restaurante sidreria = new Restaurante();
        sidreria.setNombre("Sidrería");
        sidreria.setFechaInicio(LocalDate.of(2026, 6, 25));
        restauranteRepository.save(sidreria);
        System.out.println(sidreria);

        // ManyToOne - asociar un restaurante a dos empleados
        // Paso 1. crear un restaurante y guardarlo
        Restaurante hamburgueseriaAsturiana = new Restaurante(15.0, "Hamburguesería Asturiana", 5);
        restauranteRepository.save(hamburgueseriaAsturiana);

        // Paso 2. crear empleados, setRestaurante y guardar
        Empleado hamburguesero1 = new Empleado(30, "87654321B", "Pepez", "Pepe", null);
        Empleado hamburguesero2 = new Empleado(45, "12344321C", "Fernandez", "Fernando", null);
        hamburguesero1.setRestaurante(hamburgueseriaAsturiana);
        hamburguesero2.setRestaurante(hamburgueseriaAsturiana);
        empleadoRepository.save(hamburguesero1);
        empleadoRepository.save(hamburguesero2);
        System.out.println(hamburguesero1);
        System.out.println(hamburguesero2);

        // Bucle for para iterar sobre todos los empleados imprimiendo el nombre del empleado
        // y el nombre del restaurante si lo tiene
        for (Empleado empleado : empleadoRepository.findAll()) {
            System.out.println("Empleado: " + empleado.getNombre());
            if (empleado.getRestaurante() != null) {
                System.out.println("Trabaja en el restaurante: " + empleado.getRestaurante().getNombre());
            } else {
                System.out.println("No tiene restaurante asignado");
            }
        }

        //for (Empleado empleado : empleadoRepository.findAll()) {
        //    System.out.println(
        //            empleado.getNombre() + " trabaja en " +
        //            (empleado.getRestaurante() != null ? empleado.getRestaurante().getNombre() : "ningún sitio")
        //    );
        //}

        // Probar a filtrar
        System.out.println("PRUEBAS DE FILTROS");
        List<Empleado> empleadosHambAst = empleadoRepository.findByRestaurante_Nombre("Hamburguesería Asturiana");
        List<Empleado> empleadosMayor20 = empleadoRepository.findByEdadGreaterThanEqual(20);
        List<Empleado> empleadosComidaEsp = empleadoRepository.findByRestaurante_TipoComida(TipoComida.ESP);
        List<Empleado> empleadosApellidoDesc = empleadoRepository.descOrderByApellido();

        System.out.println("EMPLEADOS HAMBURGUESERÍA ASTURIANA");
        for (Empleado empleado : empleadosHambAst) {
            System.out.println(empleado);
        }
        System.out.println("EMPLEADOS MAYORES DE 20 AÑOS");
        for (Empleado empleado : empleadosMayor20) {
            System.out.println(empleado);
        }
        System.out.println("EMPLEADOS DE RESTAURANTES DE COMIDA ESPAÑOLA");
        for (Empleado empleado : empleadosComidaEsp) {
            System.out.println(empleado);
        }
        System.out.println("EMPLEADOS ORDENADOS POR APELLIDO DESCENDENTE");
        for (Empleado empleado : empleadosApellidoDesc) {
            System.out.println(empleado);
        }

        // Clases con la clase Plato
        PlatoRepository platoRepository = context.getBean(PlatoRepository.class);

        Plato cachopo = new Plato();
        cachopo.setNombre("Cachopo Asturiano");
        cachopo.setDescripcion("Cachopo con jamón y queso");
        cachopo.setPrecio(15.0);
        cachopo.setTipoPlato(TipoPlato.PRIMER_PLATO);
        cachopo.setRestaurante(restAsturiano);
        platoRepository.save(cachopo);

        Plato fabada = new Plato();
        fabada.setNombre("Fabada Asturiana");
        fabada.setDescripcion("Fabada asturiana con chorizo y morcilla");
        fabada.setPrecio(12.0);
        fabada.setTipoPlato(TipoPlato.PRIMER_PLATO);
        fabada.setRestaurante(restAsturiano);
        platoRepository.save(fabada);

        Plato casadielles = new Plato();
        casadielles.setNombre("Casadielles");
        casadielles.setDescripcion("Casadielles asturianas con nueces y azúcar");
        casadielles.setPrecio(8.0);
        casadielles.setTipoPlato(TipoPlato.POSTRE);
        casadielles.setRestaurante(restAsturiano);
        platoRepository.save(casadielles);

        List<Plato> platoTipo = platoRepository.findByTipoPlato(TipoPlato.PRIMER_PLATO);

        System.out.println("PLATOS PRINCIPALES DE LA CARTA");
        for (Plato plato : platoTipo) {
            System.out.println(plato);
        }

        // Opción 1: crear consultas personalizadas en PlatoRepository
        // que traiga los platos con precio menor que 10 euros (findAllByPrecio)
        List<Plato> platoMenor10 = platoRepository.findByPrecioLessThanEqual(10.0);

        System.out.println("PLATOS CON PRECIO MENOR O IGUAL A 10 EUROS");
        for (Plato plato : platoMenor10) {
            System.out.println(plato);
        }

        // que traiga los platos de un restaurante ordenados por precio ascendente (findAllBy)
        //List<Plato> platoPrecioAsc = platoRepository.ascOrderByPrecio();
        List<Plato> platoPrecioAsc = platoRepository.findByRestauranteIdOrderByPrecio(restAsturiano.getId());

        System.out.println("PLATOS ORDENADOS POR PRECIO ASCENDENTE");
        for (Plato plato : platoPrecioAsc) {
            System.out.println(plato);
        }

        //Long restaurantId = restAsturiano.getId();
        //for (var plato: platoRepository.findByRestauranteIdOrderByPrecio(restaurantId))
        //    System.out.println(plato);

        // que traiga aquellos platos que no contenga alergenos
        List<Plato> platoAlergeno = platoRepository.findByAlergenoIn(List.of(Alergeno.APIO, Alergeno.CRUSTACEOS));

        System.out.println("PLATOS SIN ALERGENOS");
        for (Plato plato : platoAlergeno) {
            System.out.println(plato);
        }

        // Opción 2: crear un pedido
        PedidoRepository pedidoRepository = context.getBean(PedidoRepository.class);

        Pedido pedido1 = new Pedido();
        pedido1.setNumeroComensales(2);
        pedido1.setNumeroMesa(5);
        pedido1.setPropina(5.0);
        pedido1.setRestaurante(restAsturiano);
        pedidoRepository.save(pedido1);

        // Crear 6 líneas de pedido, una para cada pedido
        LineaPedidoRepository lineaPedidoRepository = context.getBean(LineaPedidoRepository.class);

        LineaPedido unCachopo =  new LineaPedido(pedido1, cachopo, 1);
        LineaPedido dosFabadas =  new LineaPedido(pedido1, fabada, 2);
        LineaPedido dosCasadielles =  new LineaPedido(pedido1, casadielles, 2);

        List<LineaPedido> lineasPedido = lineaPedidoRepository.saveAll(List.of(unCachopo, dosFabadas, dosCasadielles));

        // Calcular precio total en Java
        double precioTotal = 0.0;
        for (LineaPedido lineaPedido : lineasPedido) {
            double precioLinea = lineaPedido.getPlato().getPrecio() * lineaPedido.getCantidad();
            //precioTotal = precioTotal + precioLinea;
            precioTotal += precioLinea;
        }

        // Guardar el precioTotal en base de datos
        pedido1.setPrecioTotal(precioTotal);
        pedido1.setEstadoPedido(EstadoPedido.SERVIDO); // marcamos el pedido como completado
        pedidoRepository.save(pedido1);

        // Calcular precio total directamente en base de datos con una Query
        Double precioTotal2 = lineaPedidoRepository.calcularPrecioTotal(pedido1.getId()); // JPQL
        /*
        Ambos precios son el mismo
        El primero totalPrice, se calcula desde java con un bucle for
        El segundo totalPrice2, se calcula directamente en base de datos por lo que sería más óptimo
         */
        System.out.println("Precio totalPrice: " + precioTotal);
        System.out.println("Precio totalPrice2: " + precioTotal2);

        // Crear tres reseñas usando @Builder
        ResenaRepository resenaRepository = context.getBean(ResenaRepository.class);
        Resena resena1 =Resena.builder()
                .descripcion("Te atienden bien y en asturiano")
                .restaurante(hamburgueseriaAsturiana)
                .titulo("Restaurante espectacular")
                .clasificacion(5)
                .build();
        Resena resena2 =Resena.builder()
                .titulo("Horrible")
                .restaurante(restaurante2)
                .descripcion("No te atienden en asturiano")
                .clasificacion(1)
                .build();
        Resena resena3 =Resena.builder()
                .titulo("Más o menos")
                .restaurante(restEspanol)
                .descripcion("Se sabían algunas palabras en asturiano")
                .clasificacion(3)
                .build();

        Resena resena4 = Resena.builder()
                .descripcion("Ni fu ni fa")
                .plato(cachopo)
                .titulo("Me pusieron de menos")
                .clasificacion(2)
                .build();

        Resena resena5 = Resena.builder()
                .descripcion("Excelente")
                .plato(fabada)
                .titulo("Guay")
                .clasificacion(5)
                .build();

        resenaRepository.saveAll(List.of(resena1, resena2, resena3, resena4, resena5));
    }

}
