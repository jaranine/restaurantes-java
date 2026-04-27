package com.restaurantes.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

// lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

// anotaciones de JPA
@Entity
@Table(name = "reseñas")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    @Builder.Default // para que el builder no ponga este campo a null
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne
    private Restaurante restaurante;

//    @ManyToOne
//    private User user;
}