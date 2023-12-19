package com.be.ventas.entities.dto;

import org.springframework.stereotype.Component;


@Component
public class ClienteDTO  {

    private String nombres;

    private double compras;

    public ClienteDTO() {
    }

    public ClienteDTO(String nombres, double compras) {
        this.nombres = nombres;
        this.compras = compras;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public double getCompras() {
        return compras;
    }

    public void setCompras(double compras) {
        this.compras = compras;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "nombres='" + nombres + '\'' +
                ", compras=" + compras +
                '}';
    }
}
