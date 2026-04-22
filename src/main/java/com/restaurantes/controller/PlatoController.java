package com.restaurantes.controller;

import com.restaurantes.model.Plato;
import com.restaurantes.model.Restaurante;
import com.restaurantes.repository.PlatoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class PlatoController {

    private PlatoRepository platoRepository;

    public PlatoController(PlatoRepository platoRepository) {
        this.platoRepository = platoRepository;
    }

    @GetMapping("platos/{id}")
    public String platoDetalle(@PathVariable Long id, Model model) {
        Optional<Plato> platoOptional = platoRepository.findById(id);
        if (platoOptional.isPresent()) {
            Plato plato = platoOptional.get();
            model.addAttribute("plato", plato);
            return "platos/plato-detalle";
        }
        // el restaurante no existe
        return "redirect:/restaurantes";
    }
}
