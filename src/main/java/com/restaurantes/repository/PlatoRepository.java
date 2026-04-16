package com.restaurantes.repository;

import com.restaurantes.model.enums.Alergeno;
import com.restaurantes.model.Plato;
import com.restaurantes.model.enums.TipoPlato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PlatoRepository extends JpaRepository<Plato, Long> {
    List<Plato> findByTipoPlato(TipoPlato tipoPlato);

    List<Plato> findByPrecioLessThanEqual(Double precio);

    //@Query("select p from Plato p order by p.precio ASC")
    //List<Plato> ascOrderByPrecio();

    List<Plato> findByRestauranteIdOrderByPrecio(Long Id);

    //@Query("select p from Plato p where p.restaurante.id = ?1 order by p.precio ASC")
    //List<Plato> findByRestauaraascOrderByPrecio(Long id);

    List<Plato> findByAlergenoIn(Collection<Alergeno> alergenos);


}
