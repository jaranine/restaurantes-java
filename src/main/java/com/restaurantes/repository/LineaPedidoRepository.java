package com.restaurantes.repository;

import com.restaurantes.model.LineaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Long> {
    List<LineaPedido> findByPedido_Id(Long id);
    /*
    DDD - Domain Driven Design, podemos mover este cálculo a la raíz que es Order, que es la principal
    ya que este OrderLine es un agregado de esa raíz que añade información extra
     */
    @Query("""
        SELECT SUM(lp.cantidad * lp.plato.precio)
        FROM LineaPedido lp where lp.pedido.id = ?1
        """)
    Double calcularPrecioTotal(Long pedidoId);
}