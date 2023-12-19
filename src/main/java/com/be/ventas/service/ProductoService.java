package com.be.ventas.service;

import com.be.ventas.entities.Productos;
import com.be.ventas.repositories.ProductosJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductosJPA productosJPA;

    public void crearProducto(Productos productos) {
        productosJPA.save(productos);
    }

    public Optional<Productos> buscarProducto(Long id) {
        return productosJPA.findById(id);
    }

    public List<Productos> listarProductos() {
        return productosJPA.findAll();
    }

    public List<Productos> listarProductosActivos() {
        return productosJPA.findAllByActivoEquals(1);
    }

    public List<Productos> listarProductosEliminados() {
        return productosJPA.findAllByActivoEquals(0);
    }

    public void eliminarProducto(Productos productos) {
        productosJPA.delete(productos);
    }

    public void eliminacionLogicaProducto(Long id) {
        productosJPA.eliminacionLogica(id);
    }

    public List<Productos> listarProductosPaginados(int pagina, int size) {
        Page<Productos> lista = productosJPA.findAllByActivoEquals(PageRequest.of(pagina, size), 1);
        List<Productos> list = lista.stream().toList();
        return list;
    }
}
