package com.be.ventas.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BE_VENTA")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_venta")
    private Long idVenta;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    private Date fecha;

    private String serie;
    private String numero;
    private Double total;
    @OneToMany(mappedBy = "ventas", cascade = CascadeType.PERSIST, orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference(value = "ventas-detalle")
    private Set<DetalleVentas> detalleVentasSet = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vendedor")
    private Vendedor vendedor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
   @NotNull
    @Column(nullable = false,columnDefinition = "numeric default 1")
    private Integer activo;

    public Ventas() {
    }

    public Ventas(Date fecha, String serie, String numero, Double total, Set<DetalleVentas> detalleVentasSet, Vendedor vendedor, Cliente cliente, @NotNull Integer activo) {
        this.fecha = fecha;
        this.serie = serie;
        this.numero = numero;
        this.total = total;
        this.detalleVentasSet = detalleVentasSet;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.activo = activo;
    }

    public Ventas(Date fecha, String serie, String numero, Double total, Vendedor vendedor, Cliente cliente, @NotNull Integer activo) {
        this.fecha = fecha;
        this.serie = serie;
        this.numero = numero;
        this.total = total;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.activo = activo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getActivo() {
        return activo;
    }

    public void addDetalleVentas(DetalleVentas detalleVentas){
        detalleVentasSet.add(detalleVentas);
    }
    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<DetalleVentas> getDetalleVentasSet() {
        return detalleVentasSet;
    }

    public void setDetalleVentasSet(Set<DetalleVentas> detalleVentasSet) {
        this.detalleVentasSet = detalleVentasSet;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}
