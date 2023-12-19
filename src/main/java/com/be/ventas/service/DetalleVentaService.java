package com.be.ventas.service;

import com.be.ventas.entities.DetalleVentas;
import com.be.ventas.entities.Ventas;
import com.be.ventas.repositories.DetalleVentasJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService {

    @Autowired
    DetalleVentasJPA dv;

    public void crearDV(DetalleVentas detalleVentas){
dv.save(detalleVentas);
    }

    public void eliminarDV(Long id){
        dv.deleteById(id);
    }

    public List<DetalleVentas> listarDV(Ventas v){
        return dv.findByVentas(v);
    }
}
