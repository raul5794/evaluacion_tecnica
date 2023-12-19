package com.be.ventas.service;

import com.be.ventas.entities.Ventas;
import com.be.ventas.repositories.VentasDAO;
import com.be.ventas.repositories.VentasJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentasService {

@Autowired
private VentasDAO dao;

@Autowired
private VentasJPA jpa;

public void crearVenta(Ventas v){
        dao.crearVenta(v);
}

public void eliminarVenta(Long  id){
    Optional<Ventas> v = jpa.findById(id);
dao.eliminarVenta(v.get());
}

public List<Ventas> listarVentas(){
    return jpa.findAll();
}
}
