package com.restaurantes.controller;

import com.restaurantes.repository.LineaPedidoRepository;
import com.restaurantes.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final LineaPedidoRepository lineaPedidoRepository;

    // @GetMapping pedidos
    // filtrar por restaurante, filtrar por usuario
    @GetMapping("pedidos")
    public String orders(Model model) {
        model.addAttribute("pedidos",  pedidoRepository.findAll());
        return "pedidos/pedido-lista";
    }

    // @GetMapping pedidos/{id}
    @GetMapping("pedidos/{id}")
    public String order(Model model, @PathVariable Long id){
        model.addAttribute("pedido", pedidoRepository.findById(id).orElseThrow());
        model.addAttribute("lineasPedido", lineaPedidoRepository.findByPedido_Id(id));
        return "pedidos/pedido-detalle";
    }
}