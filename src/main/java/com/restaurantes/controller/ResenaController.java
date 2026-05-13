package com.restaurantes.controller;

import com.restaurantes.model.Resena;
import com.restaurantes.repository.PlatoRepository;
import com.restaurantes.repository.ResenaRepository;
import com.restaurantes.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class ResenaController {

    // inyectar el repositorio de reviews
    private final ResenaRepository resenaRepository;
    private final RestauranteRepository restauranteRepository;
    private final PlatoRepository platoRepository;

    // getmapping reviews
    @GetMapping("resenas")
    public String resenas(Model model) {
        model.addAttribute("resenas", resenaRepository.findAll());
        return "resenas/resena-lista";
    }

    @GetMapping("resenas/{id}")
    public String resena(Model model, @PathVariable Long id) {
        model.addAttribute("resena",  resenaRepository.findById(id).orElseThrow());
        return "resenas/resena-detalle";
    }

    @GetMapping("resenas/delete/{id}")
    public String borrar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        resenaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Borrado exitosamente");
        return "redirect:/resenas";
    }

    @GetMapping("resenas/disable/{id}")
    public String deshabilitar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Resena> resenaOptional = resenaRepository.findById(id);
//        if (resenaOptional.isPresent()) {
//            Resena resena = resenaOptional.get();
//            resena.setActivo(false);
//            resenaRepository.save(resena);
//        }
        redirectAttributes.addFlashAttribute("mensaje", "Desactivado exitosamente");
        return "redirect:/resenas";
    }


    @GetMapping("resenas/nuevo")
    public String nuevaResena(
            Model model,
            @RequestParam(required = false) Long restauranteId,
            @RequestParam(required = false) Long platoId) {
        Resena resena = new Resena();

        if (restauranteId != null)
            resena.setRestaurante(restauranteRepository.findById(restauranteId).orElseThrow());

        if (platoId != null)
            resena.setPlato(platoRepository.findById(platoId).orElseThrow());

        model.addAttribute("resena", resena);
        return "resenas/resena-form";
    }


    @GetMapping("resenas/editar/{id}")
    public String editarResena(Model model, @PathVariable Long id) {
        model.addAttribute("resena", resenaRepository.findById(id).orElseThrow());
        return "resenas/resena-form";
    }


    @PostMapping("resenas")
    public String guardarResena(@ModelAttribute Resena resena) {
        resenaRepository.save(resena);

        if (resena.getRestaurante() != null)
            return "redirect:/restaurantes/" + resena.getRestaurante().getId();

        if (resena.getPlato() != null)
            return "redirect:/platos/" + resena.getPlato().getId();

        return "redirect:/resenas";
    }
}