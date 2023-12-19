package com.be.ventas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Entity
@Table(name = "BE_VENDEDOR")
@AttributeOverride(name = "id",
column = @Column(name ="id_vendedor" ))
public class Vendedor  extends  Usuario{

    @Transient
    @JsonIgnore
    private Double ventas;

    public Vendedor() {
    }

    public Vendedor(@NotNull String nombres, @NotNull String apellidos, @NotNull String nroDocumento, String email, Date createAt, @NotNull Integer activo) {
        super(nombres, apellidos, nroDocumento, email, createAt, activo);
    }

    public Double getVentas() {
        return ventas;
    }

    public void setVentas(Double ventas) {
        this.ventas = ventas;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "nombres='" + getNombres() + '\'' +
                ", apellidos='" + getApellidos() + '\'' +
                ", nroDocumento='" + getNroDocumento() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", createAt=" + getCreateAt() +
                ", activo=" + getActivo() +
                "ventas=" + ventas +
                '}';
    }
}
