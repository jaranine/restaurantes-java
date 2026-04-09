package com.restaurantes;

import com.restaurantes.model.Empleado;
import com.restaurantes.model.Restaurante;
import com.restaurantes.repository.EmpleadoRepository;
import com.restaurantes.repository.RestauranteRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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
    }

}
