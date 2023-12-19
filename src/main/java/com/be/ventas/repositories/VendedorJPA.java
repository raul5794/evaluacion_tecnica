package com.be.ventas.repositories;

import com.be.ventas.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorJPA extends JpaRepository<Vendedor,Long> {

}
