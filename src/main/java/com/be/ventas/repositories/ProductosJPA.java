package com.be.ventas.repositories;

import com.be.ventas.entities.Cliente;
import com.be.ventas.entities.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductosJPA extends JpaRepository<Productos,Long> {

    @Modifying
    @Query(value = "update BE_PRODUCTO c set c.activo = 0 where c.ID_PRODUCTO = :id ",nativeQuery = true)
    void eliminacionLogica(@Param(value = "id") long id);

    List<Productos> findAllByActivoEquals(Integer i);

    Page<Productos> findAllByActivoEquals(Pageable pageable,Integer i);
}
