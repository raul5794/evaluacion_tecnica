package com.be.ventas.repositories;

import com.be.ventas.entities.DetalleVentas;
import com.be.ventas.entities.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentasJPA extends JpaRepository<DetalleVentas, Long> {

public List<DetalleVentas> findByVentas(Ventas v);
}
