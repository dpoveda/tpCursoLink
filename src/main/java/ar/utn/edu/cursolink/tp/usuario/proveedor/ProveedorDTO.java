package ar.utn.edu.cursolink.tp.usuario.proveedor;

import java.util.List;

import ar.utn.edu.cursolink.tp.producto.Producto;

public interface ProveedorDTO {
	String getNombre(); 
	
	List<Producto> getProductos();
	
}
