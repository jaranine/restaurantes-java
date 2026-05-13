package com.restaurantes.repository;

import com.restaurantes.model.Restaurante;
import com.restaurantes.model.enums.TipoComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    //    @Transactional
    //    @Modifying
    //    @Query("delete from Restaurant r where r.name = ?1")
    //    void deleteByNameTodoGuay(String name);

//    List<Restaurante> findByActivoTrue();
    Optional<Restaurante> findByIdAndActivoTrue(Long id);

    @Query("""
        SELECT r from Restaurante r
        WHERE r.activo =true
        AND (:tipoComida IS NULL OR r.tipoComida = :tipoComida)
        AND (:precio IS NULL OR r.precioMedio <= :precio)
        AND (:titulo IS NULL OR :titulo = '' OR LOWER(r.nombre) LIKE LOWER(CONCAT('%', :titulo, '%')))
    """)
    List<Restaurante> findActivoFiltering(
            @Param("tipoComida") TipoComida tipoComida,
            @Param("precio") Double precio,
            @Param("titulo") String titulo
    );
}