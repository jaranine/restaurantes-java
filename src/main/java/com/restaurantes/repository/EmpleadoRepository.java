package com.restaurantes.repository;

import com.restaurantes.model.Empleado;
import com.restaurantes.model.TipoComida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByRestaurante_Nombre(String nombre);

    List<Empleado> findByEdadGreaterThanEqual(Integer edad);

    List<Empleado> findByRestaurante_TipoComida(TipoComida tipoComida);
}