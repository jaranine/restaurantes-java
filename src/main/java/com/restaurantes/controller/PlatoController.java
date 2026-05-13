package com.restaurantes.controller;

import com.restaurantes.model.Plato;
import com.restaurantes.model.Resena;
import com.restaurantes.model.Restaurante;
import com.restaurantes.model.enums.Alergeno;
import com.restaurantes.model.enums.TipoComida;
import com.restaurantes.model.enums.TipoPlato;
import com.restaurantes.repository.PlatoRepository;
import com.restaurantes.repository.ResenaRepository;
import com.restaurantes.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class PlatoController {

    private final PlatoRepository platoRepository;
    private final ResenaRepository resenaRepository;
    private final RestauranteRepository restauranteRepository;

    @GetMapping("platos")
    public String platosLista(Model model) {
        List<Plato> platos = platoRepository.findAll();
        model.addAttribute("platos", platos);
        return "platos/plato-lista";
    }

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

    @GetMapping("platos/nuevo")
    public String nuevoPlato(Model model) {
        model.addAttribute("plato", new Plato());
        model.addAttribute("tiposPlato", TipoPlato.values());
        model.addAttribute("alergenos", Alergeno.values());
        model.addAttribute("restaurantes", restauranteRepository.findAll());
        return "platos/plato-form";
    }

    @GetMapping("platos/editar/{id}")
    public String editarPlato(@PathVariable Long id, Model model) {
        model.addAttribute("plato", platoRepository.findById(id).orElseThrow());
        model.addAttribute("tiposPlato", TipoComida.values());
        model.addAttribute("restaurantes", restauranteRepository.findAll());
        return "platos/plato-form";
    }

    @PostMapping("platos")
    public String guardarPlato(@ModelAttribute Plato plato) {
        System.out.println("PLATO GUARDADO: " + plato);
        platoRepository.save(plato);
        return "redirect:/platos/" + plato.getId();
    }
}
