package com.be.ventas;

import com.be.ventas.entities.*;
import com.be.ventas.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class VentasApplicationTests {
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

	@BeforeAll
	void contextLoads() throws ParseException {									//orden de creacion
		List<Vendedor> vendedorList = new ArrayList<>();      // 1
		List<Productos> productosList = new ArrayList<>();   //2
		List<Cliente> clienteList = new ArrayList<>();   	 // 3
		List<Ventas> ventasList = new ArrayList<>();     	// 4
		List<DetalleVentasJPA> detalleVentasJPAS = new ArrayList<>(); // 5

		//Creacion de vendedor
		Vendedor vendedor = new Vendedor();
		vendedor.setNombres("Juan");
		vendedor.setActivo(1);
		vendedor.setApellidos("Rigoberto");
		vendedor.setEmail("rigo@berto.ju");
		vendedor.setNroDocumento("12345678");

		vendedorList.add(vendedor);
		vendedorJPA.saveAll(vendedorList);

		//Creacion de productos
		Productos productos1 = new Productos("celular",5, Estados.NUEVO,1);
		Productos productos2 = new Productos("cama",2,Estados.REPARADO,1);
		Productos productos3 = new Productos("casaca",10,Estados.USADO,1);
		Productos productos4 = new Productos("impresora",6, Estados.NUEVO,1);
		Productos productos5 = new Productos("lapicero",9,Estados.REPARADO,1);
		Productos productos6 = new Productos("lampara",10,Estados.USADO,1);
		Productos productos7 = new Productos("pc",1, Estados.NUEVO,1);
		Productos productos8 = new Productos("camara",2,Estados.REPARADO,1);
		Productos productos9 = new Productos("escoba",15,Estados.USADO,1);
		Productos productos10 = new Productos("mouses",25, Estados.NUEVO,1);
		Productos productos11 = new Productos("libro",20,Estados.REPARADO,1);
		Productos productos12 = new Productos("caratula",6,Estados.USADO,1);

		productosList.add(productos1);
		productosList.add(productos2);
		productosList.add(productos3);
		productosList.add(productos4);
		productosList.add(productos5);
		productosList.add(productos6);
		productosList.add(productos7);
		productosList.add(productos8);
		productosList.add(productos9);
		productosList.add(productos10);
		productosList.add(productos11);
		productosList.add(productos12);
		productosJPA.saveAll(productosList);

		// Creacion de clientes

		Cliente cliente1 = new Cliente();
		cliente1.setEmail("cliente@number.uno");
		cliente1.setActivo(1);
		cliente1.setNombres("rosalina");
		cliente1.setApellidos("larosa");
		cliente1.setNroDocumento("96325874");

		Cliente cliente2 = new Cliente();
		cliente2.setEmail("cliente@number.dos");
		cliente2.setActivo(1);
		cliente2.setNombres("catalina");
		cliente2.setApellidos("montes");
		cliente2.setNroDocumento("45628954");

		clienteList.add(cliente1);
		clienteList.add(cliente2);

		clienteJPA.saveAll(clienteList);

		// Creacion de Ventas y detalleVenta ya que Ventas gestiona el ciclo de vida de detalle de ventas
		DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha1 = formateador.parse("10/11/2023");
		Date fecha2 = formateador.parse("05/11/2023");

		Ventas ventas1 = new Ventas(fecha1,"123","456",105.0,vendedor,cliente1,1);
		Ventas ventas2 = new Ventas(fecha2,"789","000",261.5,vendedor,cliente2,1);

		DetalleVentas dv1 = new DetalleVentas(1,10.5,10.5,productos1,ventas1,1);
		DetalleVentas dv2 = new DetalleVentas(2,50.0,100.0,productos2,ventas1,1);
		DetalleVentas dv3 = new DetalleVentas(3,5.0,15.0,productos3,ventas2,1);

		ventas1.addDetalleVentas(dv1);
		ventas1.addDetalleVentas(dv2);
		ventas2.addDetalleVentas(dv3);

		ventasList.add(ventas1);
		ventasList.add(ventas2);

		ventasJPA.saveAll(ventasList);
	}

	@AfterAll
	void afterall(){
		detalleVentasJPA.deleteAll();
		ventasJPA.deleteAll();
		productosJPA.deleteAll();
		clienteJPA.deleteAll();
		vendedorJPA.deleteAll();
	}

}
