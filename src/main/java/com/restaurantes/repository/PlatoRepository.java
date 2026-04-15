package com.restaurantes.repository;

import com.restaurantes.model.Plato;
import com.restaurantes.model.TipoPlato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatoRepository extends JpaRepository<Plato, Long> {
    List<Plato> findByTipoPlato(TipoPlato tipoPlato);
}
