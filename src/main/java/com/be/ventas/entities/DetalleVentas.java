package com.be.ventas.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "BE_DETALLE_VENTA" )
public class DetalleVentas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_detalle")
    private Long idDetalle;
    @NotNull
    private Integer cantidad;
    @NotNull
    private Double precio;
    @Formula("cantidad * precio")
    private Double subTotal;
    @JsonBackReference(value = "productos-detalle")
    @ManyToOne(optional = false, fetch =FetchType.LAZY)
    private Productos productos;

    @JsonBackReference(value = "ventas-detalle")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ventas ventas;

    @NotNull
    @Column(nullable = false,columnDefinition = "numeric default 1")
    private Integer activo;

    public DetalleVentas() {
    }

    public DetalleVentas(@NotNull Integer cantidad, @NotNull Double precio, Double subTotal, Productos productos, Ventas ventas, @NotNull Integer activo) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
        this.productos = productos;
        this.ventas = ventas;
        this.activo = activo;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }


    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }
}
