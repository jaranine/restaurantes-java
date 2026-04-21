package com.restaurantes.controller;

import com.restaurantes.model.Restaurante;
import com.restaurantes.repository.RestauranteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class RestauranteController {

    // Inyectar el restaurante repository
    private final RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

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
        model.addAttribute("title", "Lista de restaurantes");
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
            return "restaurantes/restaurante-detalle";
        }
            // el restaurante no existe
            return "redirect:/restaurantes";
    }

}
