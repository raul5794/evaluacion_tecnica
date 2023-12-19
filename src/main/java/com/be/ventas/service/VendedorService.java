package com.be.ventas.service;

import com.be.ventas.entities.Vendedor;
import com.be.ventas.repositories.ProcedimientoUtils;
import com.be.ventas.repositories.VendedorJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {


    @Autowired
    private VendedorJPA vendedorJPA;

    public void crearVendedor(Vendedor vendedor){
        vendedorJPA.save(vendedor);
    }

    public void eliminarVendedor(Long id){
        vendedorJPA.deleteById(id);
    }

    public Optional<Vendedor> buscarVendedor(Long id){
        return vendedorJPA.findById(id);
    }

    public List<Vendedor> listarVendedores(){
        return vendedorJPA.findAll();
    }
}
