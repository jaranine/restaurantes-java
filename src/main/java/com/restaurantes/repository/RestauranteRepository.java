package com.restaurantes.repository;

import com.restaurantes.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}