package com.restaurantes.controller;

import com.restaurantes.model.Plato;
import com.restaurantes.model.Resena;
import com.restaurantes.repository.ResenaRepository;
import com.restaurantes.model.Restaurante;
import com.restaurantes.repository.PlatoRepository;
import com.restaurantes.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor // lombok
public class RestauranteController {

    // Inyectar el restaurante repository
    private final RestauranteRepository restauranteRepository;
    private final PlatoRepository platoRepository;
    private final ResenaRepository resenaRepository;
    //public RestauranteController(RestauranteRepository restauranteRepository, PlatoRepository platoRepository) {
    //    this.restauranteRepository = restauranteRepository;
    //    this.platoRepository = platoRepository;
    //}

    /*
    Métodos comunes a clase controller:

    @GetMapping("restaurantes") findAll
    @GetMapping("restaurantes/{id}") findById

    @GetMapping("restaurantes/create") createForm
    @PostMapping("restaurantes/create") create

    @GetMapping("restaurantes/edit") editForm
    @PostMapping("restaurantes/edit") edit

    @GetMapping("restaurantes/delete/{id}") delete
    */

    // Get all restaurantes
    //http://localhost:8080/restaurantes
    @GetMapping("restaurantes")
    public String restauranteLista(Model model) {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        model.addAttribute("restaurantes", restauranteRepository.findAll());
        model.addAttribute("numeroRestaurantes", restaurantes.size());
        model.addAttribute("titulo", "Lista de restaurantes");
        return "restaurantes/restaurante-lista";
    }

    // nuevo mtodo para traer un solo restaurante por su id
    @GetMapping("restaurantes/{id}")
    public String restauranteDetalle(@PathVariable Long id, Model model) {

        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);

        if (restauranteOptional.isPresent()) {
            // el restaurante si existe
            Restaurante restaurante = restauranteOptional.get();
            model.addAttribute("restaurante", restaurante);

            // opcional:
            // cargar los platos (Dish) de este restaurant en el model
            List<Plato> platos = platoRepository.
                    findByRestauranteIdOrderByPrecio(
                            restaurante.getId());
            model.addAttribute("platos", platos);

            // resenas
            //List<Resena> resenas = resenaRepository.findAll();
            List<Resena> resenas = resenaRepository.findByRestaurante_IdOrderByFechaCreacionDesc(restaurante.getId());
            model.addAttribute("resenas", resenas); // accesibles desde HTML

            return "restaurantes/restaurante-detalle";
        }
            // el restaurante no existe
            return "redirect:/restaurantes";
    }

}
