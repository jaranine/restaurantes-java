package com.restaurantes.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

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
    private Boolean activo = true;

    private Integer numeroEmpleados;

    // Fecha de apertura
    // @CreationTimestamp // generación automatica de fecha
    private LocalDate fechaInicio = LocalDate.now(); // valor por defecto a fecha actual

    // Tipo de comida
    @Enumerated(EnumType.STRING)
    private TipoComida tipoComida = TipoComida.ESP;

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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(Integer numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    // toString

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioMedio=" + precioMedio +
                ", activo=" + activo +
                ", numeroEmpleados=" + numeroEmpleados +
                ", fechaInicio=" + fechaInicio +
                ", tipoComida=" + tipoComida +
                '}';
    }

}