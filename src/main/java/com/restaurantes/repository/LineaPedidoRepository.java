package com.restaurantes.repository;

import com.restaurantes.model.LineaPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Long> {
}