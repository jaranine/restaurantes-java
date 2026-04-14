package com.restaurantes;

import com.restaurantes.model.Empleado;
import com.restaurantes.model.Restaurante;
import com.restaurantes.model.TipoComida;
import com.restaurantes.repository.EmpleadoRepository;
import com.restaurantes.repository.RestauranteRepository;
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
        restAsturiano.setStarDate(LocalDate.now()); // fecha actual
        restauranteRepository.save(restAsturiano);
        System.out.println(restAsturiano);

        // Fecha futura
        Restaurante sidreria = new Restaurante();
        sidreria.setNombre("Sidrería");
        sidreria.setStarDate(LocalDate.of(2026, 6, 25));
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

    }

}
