package com.be.ventas.controller;

import com.be.ventas.entities.Cliente;
import com.be.ventas.entities.Productos;
import com.be.ventas.entities.Vendedor;
import com.be.ventas.entities.Ventas;
import com.be.ventas.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class VentasController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private DetalleVentaService detalleVentaService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private VentasService ventasService;

    @PostMapping("cliente")
    public ResponseEntity<?> crearCliente(@Valid @RequestBody Cliente cliente) {
        try {
            this.clienteService.crearcliente(cliente);
            return new ResponseEntity<>(cliente, null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo crear el cliente", null, HttpStatus.OK);
        }
    }

    @DeleteMapping("cliente")
    public ResponseEntity<?> eliminarCliente(@Valid @RequestBody Cliente cliente) {
        this.clienteService.eliminarCliente(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("clientes")
    public ResponseEntity<?> listarClientes() {
        List<Cliente> clienteList = this.clienteService.listarClientesActivos();
        return new ResponseEntity<>(clienteList, HttpStatus.OK);
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> eliminarSoftCliente(@PathVariable Long id) {
        this.clienteService.eliminacionLogicaCliente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("productos")
    public ResponseEntity<?> listarProductos() {
        List<Productos> list = this.productoService.listarProductos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("productos/{pagina}/{size}")
    public ResponseEntity<?> listarProductoPaginado(@PathVariable int pagina, @PathVariable int size) {
        List<Productos> list = this.productoService.listarProductosPaginados(pagina, size);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "venta",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearVenta(@Validated @RequestBody Ventas ventas) {
        this.ventasService.crearVenta(ventas);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("ventas")
    public ResponseEntity<?> listarVentas() {

        return new ResponseEntity<>(this.ventasService.listarVentas(), HttpStatus.OK);
    }

    @DeleteMapping("venta/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable Long id) {
        this.ventasService.eliminarVenta(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
@GetMapping("vendedores")
    public ResponseEntity<?> listarVendedores() {

        return new ResponseEntity<>(this.vendedorService.listarVendedores(),HttpStatus.OK);
    }
@PostMapping("vendedor")
    public ResponseEntity<?> crearVendedor(@Validated @RequestBody Vendedor vendedor) {

        this.vendedorService.crearVendedor(vendedor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
