package ar.utn.edu.cursolink.tp.carrito;

import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.utn.edu.cursolink.tp.exception.TieneItemRepetidoException;


@RepositoryRestController
public class CarritoControllerComplement {
	 
	@Autowired
	RepoCarrito repoCarrito;
	
	@Autowired
	RepoItemCarrito repoItem;
	

	
	@Transactional
	@RequestMapping(method = RequestMethod.POST,value="/items/{itemId}/agregarItem")
	public @ResponseBody String agregarItem(@PathVariable("itemId") Integer itemId, @RequestBody Integer carritoId) {
		Optional<ItemCarrito> opcionalItem = repoItem.findById(itemId);
		if(opcionalItem.isEmpty()) {
			return "item no encontrado";
		}
		ItemCarrito item = opcionalItem.get();
		
		
		Optional<Carrito> opcionalCarrito = repoCarrito.findById(carritoId);
		if(opcionalCarrito.isEmpty()) {
			return "carrito no encontrado";
		}
		Carrito carrito = opcionalCarrito.get();
		
		
		try {
			carrito.agregarItem(item);
		} catch (TieneItemRepetidoException e) {
			return "el item ya esta agregado";
		}
		
		return "ok";
	}
	
}
