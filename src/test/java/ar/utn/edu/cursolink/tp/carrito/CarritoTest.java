package ar.utn.edu.cursolink.tp.carrito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import ar.utn.edu.cursolink.tp.producto.Producto;
import ar.utn.edu.cursolink.tp.usuario.cliente.Cliente;


public class CarritoTest {
	
	@Test
	public void testAgregarItemACarritoOk() throws Exception{
		
		//PRECONDICION
		
		//creo el carrito y lo inicializo con dos items
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		carrito.agregarItem(new ItemCarrito(new Producto("Lapicera",70),6));
		carrito.agregarItem(new ItemCarrito(new Producto("Goma",30),6));	
		ItemCarrito itemNuevo = new ItemCarrito(new Producto("Lapiz",50),6);
		
		//OPERATORIA
		carrito.agregarItem(itemNuevo);
		
		//POSCONDICION
		assertTrue(carrito.tieneItem(itemNuevo));
			
		}
	
	@Test
	public void testAgregarItemRepetidoACarritoOk() throws Exception{
				
		//PRECONDICION
		
		//creo el carrito y lo inicializo y lo seteo con el carrito del cliente
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30);
		Producto producto2 = new Producto("Lapiz",50);
		
		
		//OPERATORIA
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
		cliente.agregarAlCarrito(producto0, 5);
		cliente.agregarAlCarrito(producto1, 6);
		cliente.agregarAlCarrito(producto2, 7);
		cliente.agregarAlCarrito(producto0, 4);
		
		//POSCONDICION
		assertEquals(3, carrito.getItems().size()); //tiene 3 items, el 4 no se agrego, solo se seteo su cantidad
	}

	@Test
	public void testCalcularPrecioTotalCarritoOk() {
		Cliente cliente = new Cliente("Daniela");
		Collection<ItemCarrito> items = new ArrayList<ItemCarrito>();
		Carrito carrito = new Carrito(items);
		cliente.setCarrito(carrito);
		
		//creamos los productos
		Producto producto0 = new Producto("Lapicera",70);
		Producto producto1 = new Producto("Goma",30.5);
		Producto producto2 = new Producto("Lapiz",50);
		
		//agregamos los items, como el producto0 es un repetido, solo se le va a sumar la cantidad
				cliente.agregarAlCarrito(producto0, 5);
				cliente.agregarAlCarrito(producto1, 5);
				cliente.agregarAlCarrito(producto2, 7);
				cliente.agregarAlCarrito(producto0, 4);
		
		//OPERATORIA
		double resultado;
		resultado = carrito.calcularPrecioTotal();
		
		
		//POSCONDICION
		assertEquals(1132.5, resultado);
	}
	
}
