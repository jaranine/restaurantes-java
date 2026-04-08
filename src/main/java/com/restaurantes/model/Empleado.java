package com.restaurantes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;
    private Integer edad;
}