package com.be.ventas.repositories;

import com.be.ventas.entities.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface VentasJPA  extends JpaRepository<Ventas,Long> {

}
