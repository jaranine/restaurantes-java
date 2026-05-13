package com.restaurantes.controller;

import com.restaurantes.model.LineaPedido;
import com.restaurantes.model.Pedido;
import com.restaurantes.model.Plato;
import com.restaurantes.model.Restaurante;
import com.restaurantes.model.enums.EstadoPedido;
import com.restaurantes.repository.LineaPedidoRepository;
import com.restaurantes.repository.PedidoRepository;
import com.restaurantes.repository.PlatoRepository;
import com.restaurantes.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final LineaPedidoRepository lineaPedidoRepository;
    private final RestauranteRepository restauranteRepository;
    private final PlatoRepository platoRepository;

    // @GetMapping pedidos
    // filtrar por restaurante, filtrar por usuario
    @GetMapping("pedidos")
    public String pedidos(Model model) {
        model.addAttribute("pedidos",  pedidoRepository.findAll());
        return "pedidos/pedido-lista";
    }

    // @GetMapping pedidos/{id}
    @GetMapping("pedidos/{id}")
    public String pedido(Model model, @PathVariable Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        model.addAttribute("pedido", pedido);
        model.addAttribute("lineasPedido", lineaPedidoRepository.findByPedido_Id(id));
        List<Plato> platos = platoRepository.findByRestauranteIdOrderByPrecio(pedido.getRestaurante().getId());
        model.addAttribute("platos", platos);
        return "pedidos/pedido-detalle";
    }

    @GetMapping("pedidos/nuevo")
    public String nuevoPedido(Model model, @RequestParam Long restauranteId) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId).orElseThrow();
        Pedido pedido = new Pedido();
        pedido.setRestaurante(restaurante);
        model.addAttribute("pedido", pedido);
        return "pedidos/pedido-form";
    }

    @PostMapping("pedidos")
    public String guardarPedido(@ModelAttribute Pedido pedido) {
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido.setFecha(LocalDateTime.now());
        pedido.setPrecioTotal(0d);
        pedidoRepository.save(pedido);
        return "redirect:/pedidos/" + pedido.getId();
    }

    @PostMapping("pedidos/{id}/lineas")
    public String anadirLineaPedido(
            @PathVariable Long id, @RequestParam Long platoId) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        Plato plato = platoRepository.findById(platoId).orElseThrow();

        Optional<LineaPedido> lineaOptional =  lineaPedidoRepository.findByPedido_IdAndPlato_Id(id, platoId);

        // opción imperativa clásica tradicional
        LineaPedido lineaPedido;
        if (lineaOptional.isPresent()) {
            lineaPedido = lineaOptional.get();
            lineaPedido.setCantidad(lineaPedido.getCantidad() + 1);
        } else {
            lineaPedido = new LineaPedido();
            lineaPedido.setPlato(plato);
            lineaPedido.setPedido(pedido);
            lineaPedido.setCantidad(1);
        }
        lineaPedidoRepository.save(lineaPedido);
        // opción alternativa estilo funcional
//        LineaPedido linea = lineaPedidoRepository
//                .findByPedido_IdAndPlato_Id(id, platoId)
//                .orElseGet(() -> new LineaPedido(pedido, plato, 0));

//        linea.setCantidad(linea.getCantidad() + 1);
//        lineaPedidoRepository.save(linea);


        if (pedido.getEstadoPedido() == EstadoPedido.PENDIENTE)
            pedido.setEstadoPedido(EstadoPedido.EN_PREPARACION);

        Double precioTotal = lineaPedidoRepository.calcularPrecioTotal(pedido.getId());
        pedido.setPrecioTotal(precioTotal);

        pedidoRepository.save(pedido);

        return "redirect:/pedidos/" + pedido.getId();
    }

    @GetMapping("pedidos/{id}/finalizar")
    public String finalizar(@PathVariable Long id, @RequestParam(required = false) Double propina) {
        Pedido pedido =  pedidoRepository.findById(id).orElseThrow();
        pedido.setEstadoPedido(EstadoPedido.SERVIDO);
        pedido.setPrecioTotal(lineaPedidoRepository.calcularPrecioTotal(pedido.getId()));
        if (propina != null && propina > 0) {
            pedido.setPropina(propina);
        } else {
            pedido.setPropina(0d);
        }
        pedidoRepository.save(pedido);
        return "redirect:/pedidos/" + id;
    }

    @GetMapping("pedidos/{pedidoId}/lineas/{lineaId}/borrar")
    public String borrarLinea(@PathVariable Long pedidoId, @PathVariable Long lineaId) {
        lineaPedidoRepository.deleteById(lineaId);
        Pedido pedido =  pedidoRepository.findById(pedidoId).orElseThrow();
        pedido.setPrecioTotal(lineaPedidoRepository.calcularPrecioTotal(pedido.getId()));
        pedidoRepository.save(pedido);
        return "redirect:/pedidos/" + pedidoId;
    }

    @PostMapping("pedidos/{pedidoId}/lineas/{lineaId}")
    public String actualizarCantidad(
            @PathVariable Long pedidoId,
            @PathVariable Long lineaId,
            @RequestParam Integer cantidad) {
        if (cantidad >= 1) {
            LineaPedido lineaPedido = lineaPedidoRepository.findById(lineaId).orElseThrow();
            lineaPedido.setCantidad(cantidad);
            lineaPedidoRepository.save(lineaPedido);

            Pedido pedido =  pedidoRepository.findById(pedidoId).orElseThrow();
            pedido.setPrecioTotal(lineaPedidoRepository.calcularPrecioTotal(pedido.getId()));
            pedidoRepository.save(pedido);
        }

        return "redirect:/pedidos/" + pedidoId;
    }
}