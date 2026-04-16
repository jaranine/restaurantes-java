package com.restaurantes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Línea de pedidos")
public class LineaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long Id;

    private Integer cantidad;

    @ManyToOne
    private Plato plato;

    @ManyToOne
    private Pedido pedido;

    public LineaPedido(Pedido pedido, Plato plato, Integer cantidad) {
        this.pedido = pedido;
        this.plato = plato;
        this.cantidad = cantidad;
    }
    public LineaPedido() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "Id=" + Id +
                ", cantidad=" + cantidad +
                ", plato=" + plato +
                ", pedido=" + pedido +
                '}';
    }
}
