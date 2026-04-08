package com.restaurantes.model;

import jakarta.persistence.*;

/*

SELECT * FROM RESTAURANTE

INSERT INTO restaurante (id, nombre, numero_empleados) VALUES (3, 'Dominos', 4)

UPDATE restaurante
SET numero_empleados = 15
WHERE id = 1;

DELETE FROM restaurante WHERE id = 1;

*/

@Entity
@Table(name = "Restaurantes")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogeneración de IDs
    private Long id;

    @Column(unique = true) // Evita que ser repitan los nombres
    private String nombre;

    private Double precioMedio;

    @Column(columnDefinition = "BOOLEAN DEFAULT true") // Valor por defecto
    private Boolean active;

    private Integer numeroEmpleados;

    // Próximas tareas:
    // Enum
    // Fecha
    // Asociación con otra entidad/tabla
}