package com.restaurantes.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


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
@Table(name = "resenas")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    private Integer clasificacion;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Builder.Default // para que el builder no ponga este campo a null
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ToString.Exclude
    @ManyToOne
    private Restaurante restaurante;

    @ToString.Exclude
    @ManyToOne
    private Plato plato;

//    @ManyToOne
//    private User user;
}