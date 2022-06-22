package ar.utn.edu.cursolink.tp.usuario.cliente;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import ar.utn.edu.cursolink.tp.carrito.Carrito;
import ar.utn.edu.cursolink.tp.carrito.ItemCarrito;
import ar.utn.edu.cursolink.tp.mediodepago.MedioDePago;
import ar.utn.edu.cursolink.tp.ordendecompra.OrdenDeCompra;
import ar.utn.edu.cursolink.tp.producto.Producto;
import ar.utn.edu.cursolink.tp.usuario.proveedor.Proveedor;

public class ClienteTest {
	
	@Test
	public void testGenerarOrdenDeCompraOk() throws Exception{
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
				
				
				//el cliente elije el medioDePago
				MedioDePago medio = MedioDePago.BILLETERAELECTRONICA;
				cliente.elegirMedioDePago(medio);
				
				//OPERATORIA
				OrdenDeCompra orden = cliente.generarOrdenDeCompra();
				
				//POSTCONDICION
				assertTrue(cliente.tieneOrden(orden));
				
	}
}
