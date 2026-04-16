package com.restaurantes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Platos")
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    private Double precio;

    @Enumerated(EnumType.STRING)
    private TipoPlato tipoPlato;

    @ManyToOne
    private Restaurante restaurante;

    @Enumerated(EnumType.STRING)
    private Alergeno alergeno = Alergeno.SIN_ALERGENOS;

    public Plato(Long id, String nombre, String descripcion, Double precio, TipoPlato tipoPlato, Restaurante restaurante, Alergeno alergeno) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoPlato = tipoPlato;
        this.restaurante = restaurante;
        this.alergeno = alergeno;
    }

    public Plato() {};

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public TipoPlato getTipoPlato() {
        return tipoPlato;
    }

    public void setTipoPlato(TipoPlato tipoPlato) {
        this.tipoPlato = tipoPlato;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Alergeno getAlergeno() {
        return alergeno;
    }

    public void setAlergeno(Alergeno alergeno) {
        this.alergeno = alergeno;
    }

    @Override
    public String toString() {
        return "Plato{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", tipoPlato=" + tipoPlato +
                ", restaurante=" + restaurante +
                ", alergeno=" + alergeno +
                '}';
    }
}
