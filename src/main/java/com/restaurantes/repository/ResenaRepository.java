package com.restaurantes.repository;

import com.restaurantes.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Long> {

    List<Resena> findByRestaurante_IdOrderByFechaCreacionDesc(Long id);
    List<Resena> findByPlato_IdOrderByFechaCreacionDesc(Long id);

    List<Resena> findByRestaurante_IdAndClasificacionGreaterThanEqualOrderByFechaCreacionDesc(Long id, Integer clasificacion);

    // rating >= 4 del restaurante 1 ordenados por más reciente primero
    // List<Resena> findByRatingGreaterThanEqualAndRestaurant_IdOrderByFechaCreacionDesc(Integer rating, Long id);


}