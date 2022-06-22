package ar.utn.edu.cursolink.tp.ordendecompra;

import org.springframework.beans.factory.annotation.Value;

import ar.utn.edu.cursolink.tp.carrito.CarritoDTO;

public interface OrdenDeCompraDTO {
	String getCodigo(); 
	
	@Value("#{target.cliente.nombre}")
	String getNombreCliente();
	
	@Value("#{target.cliente.carrito}")
	CarritoDTO getCarrito();
	
	//me faltaria que muestre el precio final
}
