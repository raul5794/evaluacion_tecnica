package com.be.ventas.repositories;

import com.be.ventas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteJPA extends JpaRepository<Cliente,Long> {

    @Modifying
    @Query(value = "update BE_CLIENTE c set c.activo = 0 where c.id_cliente = :id ",nativeQuery = true)
    void eliminacionLogica(@Param(value = "id") long id);

    List<Cliente> findAllByActivoEquals(Integer i);


}
