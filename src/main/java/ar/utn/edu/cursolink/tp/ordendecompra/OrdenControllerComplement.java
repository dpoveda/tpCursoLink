package ar.utn.edu.cursolink.tp.ordendecompra;

import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RepositoryRestController
public class OrdenControllerComplement {
	 
	@Autowired
	RepoOrdenDeCompra repoOrden;

	

	@Transactional
	@RequestMapping(method = RequestMethod.GET,value="/ordenes/{ordenId}/generarCompra")
	public String calcularPrecio(@PathVariable("ordenId") Integer ordenId) {
		double precioFinal = 0;
		
		Optional<OrdenDeCompra> opcionalOrden = repoOrden.findById(ordenId);
		if(opcionalOrden.isEmpty()) {
			return "orden no encontrada";
		}
		OrdenDeCompra orden = opcionalOrden.get();
		
		precioFinal = orden.calcularPrecioFinal();

		return "El precio final de la orden es: "+ precioFinal + ".";
	}
	
}
