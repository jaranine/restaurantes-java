package com.restaurantes.model;

import com.restaurantes.model.enums.EstadoPedido;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();
    private Integer numeroMesa;
    private Integer numeroComensales;
    private Double precioTotal;
    private Integer numeroProductos;
    private Double propina;

    @Column(length = 500)
    private String sugerencias;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido = EstadoPedido.PENDIENTE;

    @ManyToOne
    private Restaurante restaurante;

    public Pedido(Restaurante restaurante, EstadoPedido estadoPedido, String sugerencias, Double propina, Integer numeroProductos, Double precioTotal, Integer numeroComensales, Integer numeroMesa, LocalDateTime fecha, Long id) {
        this.restaurante = restaurante;
        this.estadoPedido = estadoPedido;
        this.sugerencias = sugerencias;
        this.propina = propina;
        this.numeroProductos = numeroProductos;
        this.precioTotal = precioTotal;
        this.numeroComensales = numeroComensales;
        this.numeroMesa = numeroMesa;
        this.fecha = fecha;
    }
    public Pedido() {}

    public String getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(String sugerencias) {
        this.sugerencias = sugerencias;
    }

    public Integer getNumeroProductos() {
        return numeroProductos;
    }

    public void setNumeroProductos(Integer numeroProductos) {
        this.numeroProductos = numeroProductos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
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
                "id=" + id +
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
