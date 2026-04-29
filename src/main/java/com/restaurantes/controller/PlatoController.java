package com.restaurantes.controller;

import com.restaurantes.model.Plato;
import com.restaurantes.model.Resena;
import com.restaurantes.repository.PlatoRepository;
import com.restaurantes.repository.ResenaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class PlatoController {

    private final PlatoRepository platoRepository;
    private final ResenaRepository resenaRepository;

    @GetMapping("platos/{id}")
    public String platoDetalle(@PathVariable Long id, Model model) {
        Optional<Plato> platoOptional = platoRepository.findById(id);
        if (platoOptional.isPresent()) {
            Plato plato = platoOptional.get();
            model.addAttribute("plato", plato);
            List<Resena> resenas = resenaRepository.findByPlato_IdOrderByFechaCreacionDesc(id);
            model.addAttribute("resenas", resenas);
            return "platos/plato-detalle";
        }
        // el restaurante no existe
        return "redirect:/restaurantes";
    }
}
