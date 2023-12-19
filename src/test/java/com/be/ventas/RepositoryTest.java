package com.be.ventas;

import com.be.ventas.entities.*;
import com.be.ventas.repositories.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class RepositoryTest extends VentasApplicationTests {
    @Autowired
    private ClienteJPA clienteJPA;
    @Autowired
    private DetalleVentasJPA detalleVentasJPA;
    @Autowired
    private ProductosJPA productosJPA;
    @Autowired
    private VendedorJPA vendedorJPA;
    @Autowired
    private VentasJPA ventasJPA;
    @Autowired
    private ProcedimientoUtils proc;
    @Autowired
    private VentasDAO ventasDAO;

    @Test
    @DisplayName("Verificar Create, read  usando JPA")
    public void verificarCRUD() {
        long clienteList = clienteJPA.count();
        long productosList = productosJPA.count();
        long ventasList = ventasJPA.count();
        long vendedorList = vendedorJPA.count();
        long detalleVentas = detalleVentasJPA.count();

        assertAll(
                () -> assertEquals(2, clienteList),
                () -> assertEquals(12, productosList),
                () -> assertEquals(2, ventasList),
                () -> assertEquals(3, detalleVentas),
                () -> assertEquals(1, vendedorList)
        );


    }

    @Test
    @DisplayName("Eliminación lógica")
    @Transactional
    public void eliminacionLogica() {

        List<Cliente> clientes1 = clienteJPA.findAll();
        clienteJPA.eliminacionLogica(1);
        List<Cliente> clientes2 = clienteJPA.findAllByActivoEquals(1);

        assertAll(
                () -> assertEquals(2, clientes1.size()),
                () -> assertEquals(1, clientes2.size())
        );
    }

    @Test
    @DisplayName("Buqueda Paginada Productos")
    public void busquedaPaginada() {
        Page<Productos> productopage = productosJPA.findAllByActivoEquals(PageRequest.of(1, 5), 1);

        assertEquals(5, productopage.getSize());
    }

    @Test
    @DisplayName("Compras realizadas por clientes usando Stored Procedures")
    public void clientesCompras() {
        List<Cliente> clienteDTOS = proc.fetchClientesGastos();
        assertEquals(2, clienteDTOS.size());
    }

    @Test
    @DisplayName("Vendedor con mayor venta")
    public void vendedorExitodo() {
        Vendedor vendedor = proc.fetchVendedorMaximo();
        assertEquals(366.5,vendedor.getVentas());
    }

    @Test
    public void vendedorListar(){
        List<Vendedor> vendedors = proc.listarVendedores();
        assertEquals(1,vendedors.size());
    }

    @Test
    @DisplayName("Validar insert ventas y detalle ventas haciendo uso directo de EntitiyManager")
    public void crearVenta() throws ParseException {
        DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = formateador.parse("05/11/2023");
        Optional<Vendedor> vendedor = vendedorJPA.findById(Long.valueOf(1));
        Optional<Cliente> cliente = clienteJPA.findById(1L);
        Optional<Productos> producto = productosJPA.findById(1L);
        Ventas venta = new Ventas(fecha,"123","999",300.00,vendedor.get(),cliente.get(),1);
        DetalleVentas dv1 = new DetalleVentas(2,10.5,200.0,producto.get(),venta,1);
        DetalleVentas dv2 = new DetalleVentas(1,50.0,100.0,producto.get(),venta,1);

        venta.addDetalleVentas(dv1);
        venta.addDetalleVentas(dv2);

        ventasDAO.crearVenta(venta);

        assertAll(
                ()->assertEquals(3,ventasJPA.count()),
                ()->assertEquals(5,detalleVentasJPA.count())
        );


    }
    @Disabled
    @Test
    @DisplayName("Eliminar venta usando EntityManager")
    public void eliminarVenta(){
        Optional<Ventas> venta1 = ventasJPA.findById(1L);

        ventasDAO.eliminarVenta(venta1.get());

        assertAll(
                ()->assertEquals(1,ventasJPA.count()),
                ()->assertEquals(1,detalleVentasJPA.count())
        );
    }
}
