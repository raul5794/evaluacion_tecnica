package com.be.ventas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Transient;

import java.util.Date;


@Entity
@Table(name = "BE_CLIENTE")
@AttributeOverride(name = "id",
column = @Column(name ="id_cliente" ))
public class Cliente extends  Usuario{
@Transient
@JsonIgnore
        private Double compras;

        public Cliente() {
        }

        public Cliente(@NotNull String nombres, @NotNull String apellidos, @NotNull String nroDocumento, String email, Date createAt, @NotNull Integer activo, Double compras) {
                super(nombres, apellidos, nroDocumento, email, createAt, activo);
                this.compras = compras;
        }

        public Double getCompras() {
                return compras;
        }

        public void setCompras(Double compras) {
                this.compras = compras;
        }

        @Override
        public String toString() {
                return "Cliente{" +
                        "nombres='" + getNombres() + '\'' +
                        ", apellidos='" + getApellidos() + '\'' +
                        ", nroDocumento='" + getNroDocumento() + '\'' +
                        ", email='" + getEmail() + '\'' +
                        ", createAt=" + getCreateAt() +
                        ", activo=" + getActivo() +
                        ", compras=" + getCompras() +
                        '}';
        }
}
