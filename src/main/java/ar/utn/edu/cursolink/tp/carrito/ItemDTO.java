package ar.utn.edu.cursolink.tp.carrito;

import ar.utn.edu.cursolink.tp.producto.Producto;

public interface ItemDTO {
	
	Producto getProducto();
	int getCantidad();
}
