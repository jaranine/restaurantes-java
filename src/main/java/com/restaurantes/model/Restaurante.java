package com.restaurantes.model;

import jakarta.persistence.*;

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
    private Boolean active = true;

    private Integer numeroEmpleados;

    // metodo constructor para crear Restaurantes con valores
    public Restaurante(Double precioMedio, String nombre, Integer numeroEmpleados) {
        this.precioMedio = precioMedio;
        this.nombre = nombre;
        this.numeroEmpleados = numeroEmpleados;
    }

    // metodo constructor vacio
    public Restaurante(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioMedio() {
        return precioMedio;
    }

    public void setPrecioMedio(Double precioMedio) {
        this.precioMedio = precioMedio;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(Integer numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }

    // toString

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioMedio=" + precioMedio +
                ", active=" + active +
                ", numeroEmpleados=" + numeroEmpleados +
                '}';
    }
}