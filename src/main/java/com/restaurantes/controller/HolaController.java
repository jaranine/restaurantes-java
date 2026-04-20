package com.restaurantes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HolaController {

    // HTTP- GET, POST, PUSH, DELETE...
    @GetMapping("/hola")
    public String hola() {
        return "hola";
    }

    @GetMapping("/adios")
    public String hola(Model model) {
        model.addAttribute("mensaje", "Adios mundo cruel");
        return "adios";
    }

}
