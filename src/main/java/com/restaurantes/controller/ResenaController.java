package com.restaurantes.controller;

import com.restaurantes.repository.ResenaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ResenaController {

    // inyectar el repositorio de reviews
    private final ResenaRepository resenaRepository;

    // getmapping reviews
    @GetMapping("resenas")
    public String resenas(Model model) {
        model.addAttribute("resenas", resenaRepository.findAll());
        return "resenas/resena-lista";
    }
}