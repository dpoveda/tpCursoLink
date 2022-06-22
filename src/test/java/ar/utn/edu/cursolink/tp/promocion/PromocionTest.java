package ar.utn.edu.cursolink.tp.promocion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import ar.utn.edu.cursolink.tp.carrito.Carrito;
import ar.utn.edu.cursolink.tp.carrito.ItemCarrito;
import ar.utn.edu.cursolink.tp.descuento.fijo.DescuentoFijo;
import ar.utn.edu.cursolink.tp.descuento.porcentaje.DescuentoPorcentaje;
import ar.utn.edu.cursolink.tp.exception.CuponYaUsadoException;
import ar.utn.edu.cursolink.tp.exception.NoPuedeGenerarLaOrdenException;
import ar.utn.edu.cursolink.tp.exception.TipoDeTarjetaIncorrectoException;
import ar.utn.edu.cursolink.tp.mediodepago.MedioDePago;
import ar.utn.edu.cursolink.tp.ordendecompra.OrdenDeCompra;
import ar.utn.edu.cursolink.tp.producto.Producto;
import ar.utn.edu.cursolink.tp.proveedor.Proveedor;
import ar.utn.edu.cursolink.tp.tarjeta.Tarjeta;
import ar.utn.edu.cursolink.tp.usuario.cliente.Cliente;

public class PromocionTest {
	
	//version1, sin agregar los productos
	@Test
	public void testCalcularMontoFinalFijoConPromoCuponOk() throws CuponYaUsadoException {
		
		int montoTotal = 95;
		DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
		Proveedor proveedor = new Proveedor ("Arcor");
		
		double resultado;
		PromoCupon promoCupon = new PromoCupon("123ABC", descFijo, proveedor);
		
		
		resultado = promoCupon.aplicarPromocion(montoTotal);
		
		assertEquals(45, resultado);
		
	}
	
	@Test
	public void testCalcularMontoFinalPorcentajeConPromoCuponOk() throws CuponYaUsadoException {
		
		int montoTotal = 100;
		DescuentoPorcentaje descPorc = new DescuentoPorcentaje(0.2);//mi desc es 0.1
		Proveedor proveedor = new Proveedor ("Arcor");
		
		double resultado;
		PromoCupon promoCupon = new PromoCupon("123ABC", descPorc, proveedor);
		
		
		resultado = promoCupon.aplicarPromocion(montoTotal);
		
		assertEquals(80, resultado);
		
	}
	
	//version2, agregando los productos en el carrito
	
	@Test
	public void testCalcularMontoFinalFijoConProductosConPromoCuponOk() {
		
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos 
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		//creamos los proveedores
		Proveedor proveedor0 = new Proveedor("FaberCastell"); 
		Proveedor proveedor1 = new Proveedor("Bic"); 
		
		
		//creo los preveedores y lo inicializo y lo seteo con el producto
		producto0.setProveedor(proveedor0);
		producto1.setProveedor(proveedor1);
		producto2.setProveedor(proveedor0);
		
		
		
		
		//OPERATORIA
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		
		
		DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
	
		PromoCupon promoCupon = new PromoCupon("123ABC", descFijo, proveedor0);
		MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
		cliente.elegirMedioDePago(medio);
		
		OrdenDeCompra orden = new OrdenDeCompra(cliente);
		orden.setPromo(promoCupon);
		double resultado = orden.calcularPrecioFinal();
		
		assertEquals(1110, resultado);

	
	}
	
	@Test
	public void testCalcularMontoFinalFijoConProductosConPromoCuponNoOk() {
		
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos 
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		//creamos los proveedores
		Proveedor proveedor0 = new Proveedor("FaberCastell"); 
		Proveedor proveedor1 = new Proveedor("Bic"); 
		Proveedor proveedor3 = new Proveedor("PaperMate"); 
		
		//creo los preveedores y lo inicializo y lo seteo con el producto
		producto0.setProveedor(proveedor0);
		producto1.setProveedor(proveedor1);
		producto2.setProveedor(proveedor0);
		
		
		
		
		//OPERATORIA
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		
		
		DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
	
		PromoCupon promoCupon = new PromoCupon("123ABC", descFijo, proveedor3); //pongo un proveedor que no esta por lo tanto, no me reconoce el cupon
		MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
		cliente.elegirMedioDePago(medio);
		
		OrdenDeCompra orden = new OrdenDeCompra(cliente);
		orden.setPromo(promoCupon);
		double resultado = orden.calcularPrecioFinal();
		
		assertNotEquals(1110, resultado);
	}		
	
	@Test
	public void testCalcularMontoFinalPorcentajeConProductosConPromoCuponOk() {
		
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos 
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		//creamos los proveedores
		Proveedor proveedor0 = new Proveedor("FaberCastell"); 
		Proveedor proveedor1 = new Proveedor("Bic"); 
	
		
		//creo los preveedores y lo inicializo y lo seteo con el producto
		producto0.setProveedor(proveedor0);
		producto1.setProveedor(proveedor1);
		producto2.setProveedor(proveedor0);
		
		
		
		
		//OPERATORIA
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		
		
		DescuentoPorcentaje descPorcentaje = new DescuentoPorcentaje(0.1);//mi desc es 116 pesos con el desc de 0.1
	
		PromoCupon promoCupon = new PromoCupon("123ABC", descPorcentaje, proveedor0);
		
		MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
		cliente.elegirMedioDePago(medio);
		
		OrdenDeCompra orden = new OrdenDeCompra(cliente);
		orden.setPromo(promoCupon);
		double resultado = orden.calcularPrecioFinal();
		
		assertEquals(1044, resultado);
		
//		double resultado1 = orden.calcularPrecioFinal(); //con esto ya vi que no se puede volver a usar el mismo codigo tambien
//		assertEquals(1044, resultado1);
	}
	
	@Test
	public void testCalcularMontoFinalPorcentajeConProductosConPromoCuponNoOk() {
		
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos 
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		//creamos los proveedores
		Proveedor proveedor0 = new Proveedor("FaberCastell"); 
		Proveedor proveedor1 = new Proveedor("Bic"); 
		Proveedor proveedor3 = new Proveedor("PaperMate"); 
		
		//creo los preveedores y lo inicializo y lo seteo con el producto
		producto0.setProveedor(proveedor0);
		producto1.setProveedor(proveedor1);
		producto2.setProveedor(proveedor0);
		
		
		
		
		//OPERATORIA
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		
		
		DescuentoPorcentaje descPorcentaje = new DescuentoPorcentaje(0.1);//mi desc es 116 pesos con el desc de 0.1
	
		PromoCupon promoCupon = new PromoCupon("123ABC", descPorcentaje, proveedor3); //pongo un proveedor que no esta por lo tanto, no me reconoce el cupon
		MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
		cliente.elegirMedioDePago(medio);
		
		OrdenDeCompra orden = new OrdenDeCompra(cliente);
		orden.setPromo(promoCupon);

		double resultado = orden.calcularPrecioFinal();
		
		assertNotEquals(1044, resultado);
	}
	
	@Test
	public void testCalcularMontoFinalFijoConPromoMembresiaOk() {
		
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos 
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		//creamos los proveedores
		Proveedor proveedor0 = new Proveedor("FaberCastell"); 
		Proveedor proveedor1 = new Proveedor("Bic"); 
		
		
		//creo los preveedores y lo inicializo y lo seteo con el producto
		producto0.setProveedor(proveedor0);
		producto1.setProveedor(proveedor1);
		producto2.setProveedor(proveedor0);
		
		
		
		
		//OPERATORIA
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		
		
		DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
		Collection<Cliente> clientes = new ArrayList<Cliente>();
		
		cliente.setCarrito(carrito);
		PromoMembresia promoMembresia = new PromoMembresia(descFijo);
		promoMembresia.setClientes(clientes);
		promoMembresia.agregarMiembro(cliente);
		
		MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
		cliente.elegirMedioDePago(medio);
		OrdenDeCompra orden = new OrdenDeCompra(cliente);
		orden.setPromo(promoMembresia);
		double resultado = orden.calcularPrecioFinal();
		
		assertEquals(1110, resultado);
	
	}
	
	@Test
	public void testCalcularMontoFinalFijoConPromoMembresiaNoOk() {
		
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos 
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		//creamos los proveedores
		Proveedor proveedor0 = new Proveedor("FaberCastell"); 
		Proveedor proveedor1 = new Proveedor("Bic"); 
		
		
		//creo los preveedores y lo inicializo y lo seteo con el producto
		producto0.setProveedor(proveedor0);
		producto1.setProveedor(proveedor1);
		producto2.setProveedor(proveedor0);
		
		
		
		
		//OPERATORIA
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		
		
		DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
		
		Collection<Cliente> clientes = new ArrayList<Cliente>();
		
	
		PromoMembresia promoMembresia = new PromoMembresia(descFijo);
		promoMembresia.setClientes(clientes);
		//membresia.agregarMiembro(cliente); //no estoy agregando al cliente, por lo tanto, no es miembro
		MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
		cliente.elegirMedioDePago(medio);
		OrdenDeCompra orden = new OrdenDeCompra(cliente);
		orden.setPromo(promoMembresia);
		double resultado = orden.calcularPrecioFinal();
		
		assertNotEquals(1110, resultado);
	
	}
	
	@Test
	public void testCalcularMontoFinalPorcentajeConPromoMembresiaOk() {
		
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos 
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		//creamos los proveedores
		Proveedor proveedor0 = new Proveedor("FaberCastell"); 
		Proveedor proveedor1 = new Proveedor("Bic"); 
		
		
		//creo los preveedores y lo inicializo y lo seteo con el producto
		producto0.setProveedor(proveedor0);
		producto1.setProveedor(proveedor1);
		producto2.setProveedor(proveedor0);
		
		
		
		
		//OPERATORIA
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		
		
		DescuentoPorcentaje descPorcentaje = new DescuentoPorcentaje(0.1);//mi desc es 116 pesos con el desc de 0.1
		Collection<Cliente> clientes = new ArrayList<Cliente>();
		
		cliente.setCarrito(carrito);
		PromoMembresia promoMembresia = new PromoMembresia(descPorcentaje);
		promoMembresia.setClientes(clientes);
		promoMembresia.agregarMiembro(cliente);
		MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
		cliente.elegirMedioDePago(medio);
		OrdenDeCompra orden = new OrdenDeCompra(cliente);
		orden.setPromo(promoMembresia);
		double resultado = orden.calcularPrecioFinal();
		
		assertEquals(1044, resultado);
	
	}
	
	@Test
	public void testCalcularMontoFinalPorcentajeConPromoMembresiaNoOk() {
		
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos 
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		//creamos los proveedores
		Proveedor proveedor0 = new Proveedor("FaberCastell"); 
		Proveedor proveedor1 = new Proveedor("Bic"); 
		
		
		//creo los preveedores y lo inicializo y lo seteo con el producto
		producto0.setProveedor(proveedor0);
		producto1.setProveedor(proveedor1);
		producto2.setProveedor(proveedor0);
		
		
		
		
		//OPERATORIA
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		
		
		DescuentoPorcentaje descPorcentaje = new DescuentoPorcentaje(0.1);//mi desc es 116 pesos con el desc de 0.1
		
		Collection<Cliente> clientes = new ArrayList<Cliente>();
		
		PromoMembresia promoMembresia = new PromoMembresia(descPorcentaje);
		promoMembresia.setClientes(clientes);
		//membresia.agregarMiembro(cliente); //no estoy agregando al cliente, por lo tanto, no es miembro
		MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
		cliente.elegirMedioDePago(medio);
		OrdenDeCompra orden = new OrdenDeCompra(cliente);
		orden.setPromo(promoMembresia);
		double resultado = orden.calcularPrecioFinal();
		

		assertNotEquals(1044, resultado);
	}
	
	
	//version 3, agregando que la orden de compra puede tener muchas promos 

	@Test
	public void testCalcularMontoFinalFijoConPromoMedioBilleteraOk() {
		
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos 
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		//creamos los proveedores
		Proveedor proveedor0 = new Proveedor("FaberCastell"); 
		Proveedor proveedor1 = new Proveedor("Bic"); 
		
		
		//creo los preveedores y lo inicializo y lo seteo con el producto
		producto0.setProveedor(proveedor0);
		producto1.setProveedor(proveedor1);
		producto2.setProveedor(proveedor0);
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		//elMontoSinPromos hasta ahora es 1160
		
		
		//creo los descuentos y las promos
		DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
		

		
		
		PromoMedioDePago promoMedio = new PromoMedioDePago(descFijo);
		
		
		MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
		cliente.elegirMedioDePago(medio);
		cliente.setMontoBilletera(2000);
		OrdenDeCompra orden = new OrdenDeCompra(cliente);
		
		orden.setPromo(promoMedio);
		double resultado = orden.calcularPrecioFinal();


		//OPERATORIA
		
		assertEquals(1110, resultado); 
	
	}
	
	@Test
	public void testCalcularMontoFinalFijoConPromoMedioBilleteraNoOk() {
		
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos 
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		//creamos los proveedores
		Proveedor proveedor0 = new Proveedor("FaberCastell"); 
		Proveedor proveedor1 = new Proveedor("Bic"); 
		
		
		//creo los preveedores y lo inicializo y lo seteo con el producto
		producto0.setProveedor(proveedor0);
		producto1.setProveedor(proveedor1);
		producto2.setProveedor(proveedor0);
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		//elMontoSinPromos hasta ahora es 1160
		
		
		//creo los descuentos y las promos
		DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
		

		
		
		PromoMedioDePago promoMedio = new PromoMedioDePago(descFijo);
		
		
		MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
		cliente.elegirMedioDePago(medio);
		cliente.setMontoBilletera(1159); //no tendria que funcionar porque mi monto es menor que el esperado
		OrdenDeCompra orden = new OrdenDeCompra(cliente);
		
		orden.setPromo(promoMedio);
		//OPERATORIA
		double resultado = orden.calcularPrecioFinal();


		//POSTCONDICION
		assertNotEquals(1110, resultado); 
	
	}

	@Test
	public void testCalcularMontoFinalFijoConPromoMedioTarjetaCreditoOk() throws TipoDeTarjetaIncorrectoException {
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
				Cliente cliente = new Cliente("Daniela");
				Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
				Carrito carrito = new Carrito(items);
				cliente.setCarrito(carrito);
				
				//creamos los productos 
				Producto producto0 = new Producto("Lapicera",70);
				Producto producto1 = new Producto("Goma",30);
				Producto producto2 = new Producto("Lapiz",50);
				
				//creamos los proveedores
				Proveedor proveedor0 = new Proveedor("FaberCastell"); 
				Proveedor proveedor1 = new Proveedor("Bic"); 
				
				
				//creo los preveedores y lo inicializo y lo seteo con el producto
				producto0.setProveedor(proveedor0);
				producto1.setProveedor(proveedor1);
				producto2.setProveedor(proveedor0);
				
				//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
				cliente.agregarAlCarrito(producto0, 5);
				cliente.agregarAlCarrito(producto1, 6);
				cliente.agregarAlCarrito(producto2, 7);
				cliente.agregarAlCarrito(producto0, 4);
				
				//elMontoSinPromos hasta ahora es 1160
				
				
				//creo los descuentos y las promos
				DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
				

				
				
				PromoMedioDePago promoMedio = new PromoMedioDePago(descFijo);
				
				
				//MedioDePago medio1 = MedioDePago.TARJETADECREDITO; lo comente porque no se utiliza, pero puede estar creado igual
				MedioDePago medio2 = MedioDePago.TARJETADEDEBITO;
				//MedioDePago medio3 = MedioDePago.BILLETERAELECTRONICA; lo comente porque no se utiliza, pero puede estar creado igual
				
				cliente.elegirMedioDePago(medio2);
				
				Tarjeta tarjetaCredito = new Tarjeta(cliente, "Superville", 67367373, medio2); 
				
				cliente.agregarTarjeta(tarjetaCredito);
				
				//System.out.println(cliente.getTarjetas());
				OrdenDeCompra orden = new OrdenDeCompra(cliente);
				
				orden.setPromo(promoMedio);
				
				//OPERATORIA
				double resultado = orden.calcularPrecioFinal();


				//POSTCONDICION
				assertEquals(1110, resultado); 
		//falta ver bien esto, pero creo que esta ok, y hacer el notEquals
	}
	
	@Test
	public void testCalcularMontoFinalFijoConPromoMedioTarjetaCreditoNoOk() throws TipoDeTarjetaIncorrectoException {
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
				Cliente cliente = new Cliente("Daniela");
				Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
				Carrito carrito = new Carrito(items);
				cliente.setCarrito(carrito);
				
				//creamos los productos 
				Producto producto0 = new Producto("Lapicera",70);
				Producto producto1 = new Producto("Goma",30);
				Producto producto2 = new Producto("Lapiz",50);
				
				//creamos los proveedores
				Proveedor proveedor0 = new Proveedor("FaberCastell"); 
				Proveedor proveedor1 = new Proveedor("Bic"); 
				
				
				//creo los preveedores y lo inicializo y lo seteo con el producto
				producto0.setProveedor(proveedor0);
				producto1.setProveedor(proveedor1);
				producto2.setProveedor(proveedor0);
				
				//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
				cliente.agregarAlCarrito(producto0, 5);
				cliente.agregarAlCarrito(producto1, 6);
				cliente.agregarAlCarrito(producto2, 7);
				cliente.agregarAlCarrito(producto0, 4);
				
				//elMontoSinPromos hasta ahora es 1160
				
				
				//creo los descuentos y las promos
				DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
				

				
				
				PromoMedioDePago promoMedio = new PromoMedioDePago(descFijo);
				
				
				MedioDePago medio1 = MedioDePago.TARJETADECREDITO;
				MedioDePago medio2 = MedioDePago.TARJETADEDEBITO;
			//	MedioDePago medio3 = MedioDePago.BILLETERAELECTRONICA; lo comente porque no se utiliza, pero puede estar creado igual
				
				cliente.elegirMedioDePago(medio1);
				
				Tarjeta tarjetaDebito = new Tarjeta(cliente, "Superville", 67367373, medio2); 
				//Tarjeta tarjetaDebito1 = new Tarjeta(cliente, "BBVA", 6736783, medio2); //Funciona con dos tarjetas
				cliente.agregarTarjeta(tarjetaDebito);
				//cliente.agregarTarjeta(tarjetaDebito1);
				
				OrdenDeCompra orden = new OrdenDeCompra(cliente);
				
				orden.setPromo(promoMedio);
				
				//OPERATORIA
				double resultado = orden.calcularPrecioFinal();


				//POSTCONDICION
				assertNotEquals(1110, resultado); 
		
	}
	
	//ya sea tarjeta de debido o de credito funciona igual, se sigue discriminando por su tipo independientemente de cual sea
	
	@Test
	public void testCalcularMontoFinalFijoConPromoMedioTarjetaCreditoConGenerarOrdenClienteOk() throws TipoDeTarjetaIncorrectoException, NoPuedeGenerarLaOrdenException {
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
				Cliente cliente = new Cliente("Daniela");
				Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
				Carrito carrito = new Carrito(items);
				cliente.setCarrito(carrito);
				
				//creamos los productos 
				Producto producto0 = new Producto("Lapicera",70);
				Producto producto1 = new Producto("Goma",30);
				Producto producto2 = new Producto("Lapiz",50);
				
				//creamos los proveedores
				Proveedor proveedor0 = new Proveedor("FaberCastell"); 
				Proveedor proveedor1 = new Proveedor("Bic"); 
				
				
				//creo los preveedores y lo inicializo y lo seteo con el producto
				producto0.setProveedor(proveedor0);
				producto1.setProveedor(proveedor1);
				producto2.setProveedor(proveedor0);
				
				//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
				cliente.agregarAlCarrito(producto0, 5);
				cliente.agregarAlCarrito(producto1, 6);
				cliente.agregarAlCarrito(producto2, 7);
				cliente.agregarAlCarrito(producto0, 4);
				
				//elMontoSinPromos hasta ahora es 1160
				
				
				//creo los descuentos y las promos
				DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
				

				
				
				PromoMedioDePago promoMedio = new PromoMedioDePago(descFijo);
				
				
				MedioDePago medio1 = MedioDePago.TARJETADECREDITO;
				MedioDePago medio2 = MedioDePago.TARJETADEDEBITO; //lo comente porque no se utiliza, pero puede estar creado igual
			//	MedioDePago medio3 = MedioDePago.BILLETERAELECTRONICA; lo comente porque no se utiliza, pero puede estar creado igual
				
				cliente.elegirMedioDePago(medio1);
				
				Tarjeta tarjetaDebito = new Tarjeta(cliente, "Superville", 67367373, medio1); 
				Tarjeta tarjetaDebito1 = new Tarjeta(cliente, "BBVA", 6736783, medio2); //Funciona con dos tarjetas
				cliente.agregarTarjeta(tarjetaDebito);
				cliente.agregarTarjeta(tarjetaDebito1);
				
				OrdenDeCompra orden = cliente.generarOrdenDeCompra();
							
				orden.setPromo(promoMedio);
				
				//OPERATORIA
				double resultado = orden.calcularPrecioFinal();


				//POSTCONDICION
				assertEquals(1110, resultado); 
		
	}
	
	@Test
	public void testCalcularMontoFinalFijoConPromoMedioTarjetaCreditoConGenerarOrdenClienteNoOk() throws TipoDeTarjetaIncorrectoException, NoPuedeGenerarLaOrdenException {
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
				Cliente cliente = new Cliente("Daniela");
				Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
				Carrito carrito = new Carrito(items);
				cliente.setCarrito(carrito);
				
				//creamos los productos 
				Producto producto0 = new Producto("Lapicera",70);
				Producto producto1 = new Producto("Goma",30);
				Producto producto2 = new Producto("Lapiz",50);
				
				//creamos los proveedores
				Proveedor proveedor0 = new Proveedor("FaberCastell"); 
				Proveedor proveedor1 = new Proveedor("Bic"); 
				
				
				//creo los preveedores y lo inicializo y lo seteo con el producto
				producto0.setProveedor(proveedor0);
				producto1.setProveedor(proveedor1);
				producto2.setProveedor(proveedor0);
				
				//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
				cliente.agregarAlCarrito(producto0, 5);
				cliente.agregarAlCarrito(producto1, 6);
				cliente.agregarAlCarrito(producto2, 7);
				cliente.agregarAlCarrito(producto0, 4);
				
				//elMontoSinPromos hasta ahora es 1160
				
				
				//creo los descuentos y las promos
				DescuentoFijo descFijo = new DescuentoFijo(50);//mi desc es 50
				

				
				
				PromoMedioDePago promoMedio = new PromoMedioDePago(descFijo);
				
				
				MedioDePago medio1 = MedioDePago.TARJETADECREDITO;
				MedioDePago medio2 = MedioDePago.TARJETADEDEBITO; 
				MedioDePago medio3 = MedioDePago.BILLETERAELECTRONICA; 
				
				cliente.elegirMedioDePago(medio3);
				
				Tarjeta tarjetaDebito = new Tarjeta(cliente, "Superville", 67367373, medio1); 
				Tarjeta tarjetaDebito1 = new Tarjeta(cliente, "BBVA", 6736783, medio2); //Funciona con dos tarjetas
				cliente.agregarTarjeta(tarjetaDebito);
				cliente.agregarTarjeta(tarjetaDebito1);
				
				OrdenDeCompra orden = cliente.generarOrdenDeCompra();
							
				orden.setPromo(promoMedio);
				
				//OPERATORIA
				double resultado = orden.calcularPrecioFinal();


				//POSTCONDICION
				assertNotEquals(1110, resultado); 
		
	}
	
}
