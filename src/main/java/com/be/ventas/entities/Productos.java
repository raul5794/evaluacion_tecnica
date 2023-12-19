package com.be.ventas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BE_PRODUCTO")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_producto")
    private Long idProducto;

    private String descripcion;

    private Integer cantidad;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Estados estado;

    @NotNull
    @Column(nullable = false,columnDefinition = "numeric default 1")
    private Integer activo;

    @JsonManagedReference(value = "productos-detalle")
    @JsonIgnore
    @OneToMany(mappedBy = "productos",orphanRemoval = true)
    private Set<DetalleVentas> detalleVentasSet= new HashSet<>();

    public Productos() {
    }

    public Productos(String descripcion, Integer cantidad, @NotNull Estados estado, @NotNull Integer activo) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.estado = estado;
        this.activo = activo;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Long getIdProducto() {
        return idProducto;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Set<DetalleVentas> getDetalleVentasSet() {
        return detalleVentasSet;
    }

    public void setDetalleVentasSet(Set<DetalleVentas> detalleVentasSet) {
        this.detalleVentasSet = detalleVentasSet;
    }
}
