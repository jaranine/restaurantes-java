package com.restaurantes.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long Id;

    private LocalDate fecha = LocalDate.now();
    private Integer numeroMesa;
    private Integer numeroComensales;
    private Double precioTotal;
    private Double propina;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;

    @ManyToOne
    private Restaurante restaurante;

    public Pedido(LocalDate fecha, Integer numeroMesa, Integer numeroComensales, Double precioTotal, Double propina, EstadoPedido estadoPedido, Restaurante restaurante) {
        this.fecha = fecha;
        this.numeroMesa = numeroMesa;
        this.numeroComensales = numeroComensales;
        this.precioTotal = precioTotal;
        this.propina = propina;
        this.estadoPedido = estadoPedido;
        this.restaurante = restaurante;
    }
    public Pedido() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Integer getNumeroComensales() {
        return numeroComensales;
    }

    public void setNumeroComensales(Integer numeroComensales) {
        this.numeroComensales = numeroComensales;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Double getPropina() {
        return propina;
    }

    public void setPropina(Double propina) {
        this.propina = propina;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "Id=" + Id +
                ", fecha=" + fecha +
                ", numeroMesa=" + numeroMesa +
                ", numeroComensales=" + numeroComensales +
                ", precioTotal=" + precioTotal +
                ", propina=" + propina +
                ", estadoPedido=" + estadoPedido +
                ", restaurante=" + restaurante +
                '}';
    }
}
