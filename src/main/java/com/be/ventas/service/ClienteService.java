package com.be.ventas.service;

import com.be.ventas.entities.Cliente;
import com.be.ventas.repositories.ClienteJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteJPA clienteJPA;
    public void crearcliente(Cliente cliente){
        clienteJPA.save(cliente);
    }

    public List<Cliente> listarClientesActivos(){
        return clienteJPA.findAllByActivoEquals(1);
    }

    public List<Cliente> listarClientesNoActivos(){
        return clienteJPA.findAllByActivoEquals(0);
    }

    public void eliminarCliente(Cliente cliente){
        clienteJPA.delete(cliente);
    }

    public void eliminacionLogicaCliente(Long id){
        clienteJPA.eliminacionLogica(id);
    }
}
