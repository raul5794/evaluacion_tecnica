package com.be.ventas.repositories;

import com.be.ventas.entities.Cliente;
import com.be.ventas.entities.Vendedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProcedimientoUtils implements Serializable {


    private final EntityManager manager;

    @Autowired
    public ProcedimientoUtils(EntityManager manager) {
        this.manager = manager;
    }

    public List<Cliente> fetchClientesGastos() {
        StoredProcedureQuery storedProcedure = manager
                .createStoredProcedureQuery("VENTAS.clientes_compras", Cliente.class)
                .registerStoredProcedureParameter(0, void.class, ParameterMode.REF_CURSOR)
                .setParameter(0, OracleTypes.CURSOR);
        List<Cliente> clientes = storedProcedure.getResultList();
        return clientes;
    }

    public Vendedor fetchVendedorMaximo() {
        StoredProcedureQuery storedProcedureQuery = manager
                .createStoredProcedureQuery("VENTAS.vendedor_exitoso", Vendedor.class)
                .registerStoredProcedureParameter(0, void.class, ParameterMode.REF_CURSOR)
                .setParameter(0, OracleTypes.CURSOR);
        Vendedor vendedor = (Vendedor) storedProcedureQuery.getResultList().get(0);
        return vendedor;
    }


    //Vendedor
    public void eliminarVendedor(Long id) {
        StoredProcedureQuery storedProcedureQuery = manager
                .createStoredProcedureQuery("VENTAS.vendedor_eliminar", void.class)
                .registerStoredProcedureParameter(0, Long.class, ParameterMode.IN)
                .setParameter(0, id);
        storedProcedureQuery.executeUpdate();
    }

    public List<Vendedor> listarVendedores() {
        List<Vendedor> vendedorList;
        StoredProcedureQuery storedProcedureQuery = manager.createStoredProcedureQuery("VENTAS.vendedor_listar")
                .registerStoredProcedureParameter(0, Class.class, ParameterMode.REF_CURSOR);
        List<Object[]> results = storedProcedureQuery.getResultList();
        vendedorList = results.stream().map(result -> new Vendedor((String) result[0]
                , (String) result[1]
                , (String) result[2]
                , (String) result[3],
                (Date) result[4],
                ((BigDecimal) result[5]).intValue()
        )).collect(Collectors.toList());

        return vendedorList;
    }


}
