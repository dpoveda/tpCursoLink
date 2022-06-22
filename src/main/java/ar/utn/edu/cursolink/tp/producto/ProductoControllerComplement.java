package ar.utn.edu.cursolink.tp.producto;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.utn.edu.cursolink.tp.proveedor.Proveedor;
import ar.utn.edu.cursolink.tp.proveedor.RepoProveedor;

@RepositoryRestController
public class ProductoControllerComplement {
	 
	@Autowired
	RepoProducto repoProducto;
	
	@Autowired
	RepoProveedor repoProveedor;
	

	//Con este metodo, agrego productos a mi proveedor, si el producto no tenia proveedor, al agregar el producto al proveedor, el nombre del proveedor se setea tambien en el producto
	@Transactional
	@RequestMapping(method = RequestMethod.POST,value="/productos/{productoId}/agregarEnProveedor")
	public @ResponseBody String agregarEnProveedor(@PathVariable("productoId") Integer productoId, @RequestBody Integer proveedorId) {
		Optional<Producto> opcionalProducto = repoProducto.findById(productoId);
		if(opcionalProducto.isEmpty()) {
			return "producto no encontrado";
		}
		Producto producto = opcionalProducto.get();
		
		
		Optional<Proveedor> opcionalProveedor = repoProveedor.findById(proveedorId);
		if(opcionalProveedor.isEmpty()) {
			return "proveedor no encontrado";
		}
		Proveedor proveedor = opcionalProveedor.get();
		
		
		try {
			if(producto.getProveedor() == null) {
				producto.setProveedor(proveedor);
			}
			producto.agregarEnProveedor(proveedor);
		} catch (AgregarProductoException e) {
			return "el producto ya esta agregado";
		}
		
		return "ok";
	}
}
